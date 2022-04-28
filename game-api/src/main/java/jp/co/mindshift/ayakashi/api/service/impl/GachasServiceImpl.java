package jp.co.mindshift.ayakashi.api.service.impl;

import jp.co.mindshift.ayakashi.api.common.Constant;
import jp.co.mindshift.ayakashi.api.common.DataUtil;
import jp.co.mindshift.ayakashi.api.common.MsgUtil;
import jp.co.mindshift.ayakashi.api.model.Characters;
import jp.co.mindshift.ayakashi.api.model.GachaCharacters;
import jp.co.mindshift.ayakashi.api.model.Gachas;
import jp.co.mindshift.ayakashi.api.model.GrowthTypes;
import jp.co.mindshift.ayakashi.api.model.Items;
import jp.co.mindshift.ayakashi.api.model.PaymentMethods;
import jp.co.mindshift.ayakashi.api.model.SpecialItems;
import jp.co.mindshift.ayakashi.api.model.UserItems;
import jp.co.mindshift.ayakashi.api.model.Users;
import jp.co.mindshift.ayakashi.api.model.dto.GachaCharacterDTO;
import jp.co.mindshift.ayakashi.api.model.dto.GachaResultDTO;
import jp.co.mindshift.ayakashi.api.model.form.GachasForm;
import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.api.model.form.UserItemsForm;
import jp.co.mindshift.ayakashi.api.model.form.SpinGachaForm;
import jp.co.mindshift.ayakashi.api.repo.CharactersRepository;
import jp.co.mindshift.ayakashi.api.repo.GachaCharactersRepository;
import jp.co.mindshift.ayakashi.api.repo.GachasRepository;
import jp.co.mindshift.ayakashi.api.repo.GrowthTypesRepository;
import jp.co.mindshift.ayakashi.api.repo.ItemsRepository;
import jp.co.mindshift.ayakashi.api.repo.PaymentMethodsRepository;
import jp.co.mindshift.ayakashi.api.repo.SpecialItemsRepository;
import jp.co.mindshift.ayakashi.api.repo.UserItemsRepository;
import jp.co.mindshift.ayakashi.api.repo.UsersRepository;
import jp.co.mindshift.ayakashi.api.service.BaseService;
import jp.co.mindshift.ayakashi.api.service.CommonService;
import jp.co.mindshift.ayakashi.api.service.GachasService;
import lombok.AllArgsConstructor;
import org.modelmapper.internal.util.Assert;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@AllArgsConstructor
@Service
public class GachasServiceImpl extends BaseService implements GachasService {
	private final GachasRepository gachasRepository;
	private final CharactersRepository charactersRepository;
	private final UserItemsRepository userItemsRepository;
	private final ItemsRepository itemsRepository;
	private final UsersRepository usersRepository;
	private final GrowthTypesRepository growthTypesRepository;
	private final SpecialItemsRepository specialItemsRepository;
	private final GachaCharactersRepository gachaCharactersRepository;
	private final CommonService commonService;
	private final PaymentMethodsRepository paymentMethodsRepository;

	@Override
	public List<GachasForm> getAllGachaForSpin() {
		List<GachasForm> result = super.mapList(gachasRepository.getListGacha(), GachasForm.class);
		if(!DataUtil.isNullOrEmpty(result)){
			for(GachasForm gachasForm : result){
				List<GachaCharacterDTO> gachaCharacterDTOList = super.mapList(gachaCharactersRepository.findAllByGachaId(gachasForm.getId()), GachaCharacterDTO.class);
				for(GachaCharacterDTO gachaCharacterDTO : gachaCharacterDTOList){
					Characters characterDetail = charactersRepository.getById(gachaCharacterDTO.getCharacterId());
					gachaCharacterDTO.setCharactersDetail(characterDetail);
				}
				gachasForm.setGachaCharacterDTOList(gachaCharacterDTOList);
			}
		}
		return result;
	}

	@Override
	public GachasForm getGachaTicketById(Long id) {
		Gachas gachaExists = gachasRepository.getById(id);
		if (DataUtil.isNullOrEmpty(gachaExists)){
			throw new IllegalArgumentException(MsgUtil.getMessage("gacha.notfound"));
		}
		return super.map(gachaExists, GachasForm.class);
	}

	@Override
	public ResponseDTO<?> spinGacha(SpinGachaForm spinGachaForm) throws IllegalAccessException {
		//Validate SL item hoac coin - jewel
		Optional<Gachas> gachasOptional = gachasRepository.findById(spinGachaForm.getGachaId());
		Assert.isTrue(gachasOptional.isPresent(), MsgUtil.getMessage("gacha.notexits"));
		Gachas gachas = gachasOptional.get();
		Optional<PaymentMethods> paymentMethodsOptional = paymentMethodsRepository.findById(gachas.getPaymentMethodId());
		Assert.isTrue(gachasOptional.isPresent(), MsgUtil.getMessage("payment.notexits"));
		PaymentMethods paymentMethods = paymentMethodsOptional.get();
		if(Constant.SPIN_GACHA_PAYMENT.JEWELORCOIN.equals(spinGachaForm.getPaymentMethod())){
			if(!commonService.checkBalanceEnought(spinGachaForm.getUserId(), "IOS", paymentMethods.getPaymentType(), gachas.getPrice()))
				return ResponseDTO.success(gachas);
		}else{
			String itemType;
			switch (gachas.getPaymentMethodId2().intValue()){
				case 0:
					itemType = "NORMAL_TICKET";
					break;
				case 1:
					itemType = "PREMIUM_TICKET";
					break;
				case 2:
					itemType = "PICKUP_TICKET";
					break;
				default:
					itemType = "NORMAL_TICKET";
			}
			UserItems checkItem = userItemsRepository.findUserItemsByUserIdAndItemType(spinGachaForm.getUserId(), itemType);
			Assert.notNull(checkItem, MsgUtil.getMessage("ticket.not.enough"));
		}
		List<GachaCharacters> lstGachaCharacter = gachasRepository.listGachaById(gachas.getId());
		GachaResultDTO gachaResultDTO;
		GachaCharacters resultSpin = this.randomGacha(lstGachaCharacter);
		gachaResultDTO = super.map(resultSpin, GachaResultDTO.class);
		Characters resultGachaCharacter = charactersRepository.findById(gachaResultDTO.getCharacterId()).get();
		//Check level cua userItems 
		UserItems userCharacterItem = userItemsRepository.findUserItemsByUserIdAndItemId(spinGachaForm.getUserId(), resultGachaCharacter.getItemId());
		GrowthTypes growthTypes = growthTypesRepository.findById(resultGachaCharacter.getGrowthTypeId()).orElseThrow(()-> new IllegalArgumentException(MsgUtil.getMessage("growthType.not.exists")));
		Integer maxCharacterToUp = 0;
		Field[] fields = GrowthTypes.class.getDeclaredFields();
		for (var f : fields) {
			if (f.getName().contains("level")) {
				f.setAccessible(true);
				String tail = f.getName().split("level")[1];
				int nextLv = f.getInt(growthTypes.getLevelMax());
				if (tail.equals(nextLv)) {
					maxCharacterToUp = f.getInt(growthTypes);
				}
			}
		}
		
		if(!DataUtil.isNullOrEmpty(userCharacterItem)) {
		//TH ton tai character max level
			if (growthTypes.getLevelMax() == userCharacterItem.getLevel() && userCharacterItem.getNumber() == Long.valueOf(maxCharacterToUp)) {
				SpecialItems specialItems = specialItemsRepository.getSpecialItemsBySpecialItemType("MEDAL");
				gachaResultDTO.setSpecialItems(specialItems);
				UserItems existingUserItem = userItemsRepository.findUserItemsByUserIdAndItemId(spinGachaForm.getUserId(), specialItems.getItemId());
				if (DataUtil.isNullOrEmpty(existingUserItem)){
					UserItems newItem = new UserItems();
					newItem.setUserId(spinGachaForm.getUserId());
					newItem.setNumber(1L);
					newItem.setItemType("MEDAL");
					newItem.setItemId(specialItems.getItemId());
					newItem.setCreatedAt(new Timestamp(System.currentTimeMillis()));
					newItem.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
					userItemsRepository.save(newItem);
				} else {
					existingUserItem.setNumber(existingUserItem.getNumber() + 1L);
					userItemsRepository.save(existingUserItem);
				}
			}else{
				Characters characters = charactersRepository.getCharactersByItemId(userCharacterItem.getItemId());
				GrowthTypes nextLevel = growthTypesRepository.findById(characters.getGrowthTypeId()).orElseThrow(); // Độ: TODO exception message here
				Integer level = userCharacterItem.getLevel();
				Field[] fieldMax = GrowthTypes.class.getDeclaredFields();
				for (var f : fields) {
					if (f.getName().contains("level")) {
						f.setAccessible(true);
						String tail = f.getName().split("level")[1];
						int nextLv = f.getInt(nextLevel);
						if (!tail.equals("Max")) { 
							if (Integer.parseInt(tail) - 1 == level && userCharacterItem.getNumber() + 1L == nextLv) {
								userCharacterItem.setLevel(userCharacterItem.getLevel() + 1);
							}
						}
					}
				}
				userCharacterItem.setNumber(userCharacterItem.getNumber() + 1L);
				userItemsRepository.save(userCharacterItem);
			}
		}else {
			gachaResultDTO.setCharacters(resultGachaCharacter);
			UserItems newItem = new UserItems();
			newItem.setUserId(spinGachaForm.getUserId());
			newItem.setItemId(resultGachaCharacter.getItemId());
			newItem.setItemType("CHARACTERS");
			newItem.setNumber(1L);
			newItem.setLevel(1);
			newItem.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			newItem.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
			userItemsRepository.save(newItem);
		}
		//Tru SL theo gia
		if(Constant.SPIN_GACHA_PAYMENT.JEWELORCOIN.equals(spinGachaForm.getPaymentMethod())){
			commonService.changeBalanceProgress(spinGachaForm.getUserId(), paymentMethods.getPaymentType(), gachas.getPrice());
		}else{
			String itemType;
			switch (gachas.getPaymentMethodId2().intValue()){
				case 0:
					itemType = "NORMAL_TICKET";
					break;
				case 1:
					itemType = "PREMIUM_TICKET";
					break;
				case 2:
					itemType = "PICKUP_TICKET";
					break;
				default:
					itemType = "NORMAL_TICKET";
			}
			UserItems checkItem = userItemsRepository.findUserItemsByUserIdAndItemType(spinGachaForm.getUserId(), itemType);
			checkItem.setNumber(checkItem.getNumber() - 1L);
			userItemsRepository.save(checkItem);
		}
		return ResponseDTO.success(gachaResultDTO);
	}

	private GachaCharacters randomGacha(List<GachaCharacters> lstGacha) {
		GachaCharacters resultSpin = new GachaCharacters();
		Double sumOfWeigh = 0D;
		Random random = new Random();
		for(int i=0; i< lstGacha.size(); i++){
			sumOfWeigh += lstGacha.get(i).getProbability();
		}
		Double rnd = (random.nextDouble() * (sumOfWeigh));
		for(int i=0; i<lstGacha.size(); i++){
			if(rnd <= lstGacha.get(i).getProbability())
				return lstGacha.get(i);
			rnd -= lstGacha.get(i).getProbability();
		}
		return resultSpin;
	}
}
