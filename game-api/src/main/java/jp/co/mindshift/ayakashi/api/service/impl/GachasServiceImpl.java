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
import jp.co.mindshift.ayakashi.api.model.dto.GachasDTO;
import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.api.model.dto.UserItemsDTO;
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
	public List<GachasDTO> getAllGachaForSpin() {
		List<GachasDTO> result = super.mapList(gachasRepository.getListGacha(), GachasDTO.class);
		if(!DataUtil.isNullOrEmpty(result)){
			for(GachasDTO gachasDTO : result){
				List<GachaCharacterDTO> gachaCharacterDTOList = super.mapList(gachaCharactersRepository.findAllByGachaId(gachasDTO.getId()), GachaCharacterDTO.class);
				for(GachaCharacterDTO gachaCharacterDTO : gachaCharacterDTOList){
					Characters characterDetail = charactersRepository.getById(gachaCharacterDTO.getCharacterId());
					gachaCharacterDTO.setCharactersDetail(characterDetail);
				}
				gachasDTO.setGachaCharacterDTOList(gachaCharacterDTOList);
			}
		}
		return result;
	}

	@Override
	public GachasDTO getGachaTicketById(Long id) {
		Gachas gachaExists = gachasRepository.getById(id);
		if (DataUtil.isNullOrEmpty(gachaExists)){
			throw new IllegalArgumentException(MsgUtil.getMessage("gacha.notfound"));
		}
		return super.map(gachaExists, GachasDTO.class);
	}

	@Override
	public ResponseDTO<?> spinGacha(SpinGachaForm spinGachaForm) {
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

		List<GachaCharacters> lstGacha = gachasRepository.listGachaById(gachas.getId());
		GachaResultDTO gachaResultDTO;
		GachaCharacters resultSpin = this.randomGacha(lstGacha);
		gachaResultDTO = super.map(resultSpin, GachaResultDTO.class);
		Characters resultGachaCharacter = charactersRepository.findById(gachaResultDTO.getCharacterId()).get();
		//Check level cua userItems 
		UserItems userCharacterItem = userItemsRepository.findUserItemsByUserIdAndItemId(spinGachaForm.getUserId(), resultGachaCharacter.getItemId());
		GrowthTypes growthTypes = growthTypesRepository.getById(resultGachaCharacter.getGrowthTypeId());
		Integer maxCharacterToUp = growthTypes.getLevel2() + growthTypes.getLevel3() + growthTypes.getLevel4() + growthTypes.getLevel5() + growthTypes.getLevel6();
		if(!DataUtil.isNullOrEmpty(userCharacterItem)) {
			if (growthTypes.getLevelMax() == userCharacterItem.getLevel() && userCharacterItem.getNumber() == Long.valueOf(maxCharacterToUp)) {
				SpecialItems specialItems = specialItemsRepository.getSpecialItemsBySpecialItemType("MEDAL");
				gachaResultDTO.setSpecialItems(specialItems);
			}
		}
		gachaResultDTO.setCharacters(resultGachaCharacter);
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

	@Override
	public Boolean saveBonusGacha(UserItemsDTO userItemsDTO) throws IllegalAccessException {
		Optional<Users> existingUser = usersRepository.findById(userItemsDTO.getUserId());
		Assert.isTrue(existingUser.isPresent(), MsgUtil.getMessage("user.info.null"));
		Optional<Items> existingItemOp = itemsRepository.findById(userItemsDTO.getItemId());
		Assert.isTrue(existingItemOp.isPresent(), MsgUtil.getMessage("item.notexist"));
		var existingItem = existingItemOp.get();
		boolean isNextLevel = false;
		if(Constant.ITEMTYPE.MEDAL.equals(existingItem.getItemType()) || Constant.ITEMTYPE.PREMIUM_MEDAL.equals(existingItem.getItemType())){
			UserItems existingUserItem = userItemsRepository.findUserItemsByUserIdAndItemId(userItemsDTO.getUserId(), userItemsDTO.getItemId());
			if (DataUtil.isNullOrEmpty(existingUserItem)){
				UserItems newItem = new UserItems();
				newItem.setUserId(userItemsDTO.getUserId());
				newItem.setNumber(1L);
				newItem.setItemType(existingItem.getItemType());
				newItem.setItemId(userItemsDTO.getItemId());
				newItem.setCreatedAt(new Timestamp(System.currentTimeMillis()));
				newItem.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
				userItemsRepository.save(newItem);
				return false;
			} else {
				existingUserItem.setNumber(existingUserItem.getNumber() + 1L);
				userItemsRepository.save(existingUserItem);
				return false;
			}
		} else if (Constant.ITEMTYPE.CHARACTER.equals(existingItem.getItemType())){
			UserItems existing = userItemsRepository.findUserItemsByUserIdAndItemId(userItemsDTO.getUserId(), userItemsDTO.getItemId());
			if (DataUtil.isNullOrEmpty(existing)){
				UserItems newItem = new UserItems();
				newItem.setUserId(userItemsDTO.getUserId());
				newItem.setItemId(userItemsDTO.getItemId());
				newItem.setItemType(existingItem.getItemType());
				newItem.setNumber(1L);
				newItem.setLevel(1);
				newItem.setCreatedAt(new Timestamp(System.currentTimeMillis()));
				newItem.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
				userItemsRepository.save(newItem);
				return false;
			} else {
				Characters characters = charactersRepository.getCharactersByItemId(existing.getItemId());
				GrowthTypes nextLevel = growthTypesRepository.findById(characters.getGrowthTypeId()).orElseThrow(); // Độ: TODO exception message here
				/*if((existing.getLevel() == 1 && (existing.getNumber() + 1L == (long) nextLevel.getLevel2()))
				|| (existing.getLevel() == 2 && (existing.getNumber() + 1L == (long) nextLevel.getLevel3()))
				|| (existing.getLevel() == 3 && (existing.getNumber() + 1L == (long) nextLevel.getLevel4()))
				|| (existing.getLevel() == 4 && (existing.getNumber() + 1L == (long) nextLevel.getLevel5()))
				|| (existing.getLevel() == 5 && (existing.getNumber() + 1L == (long) nextLevel.getLevel6()))){
					existing.setLevel(existing.getLevel() + 1);
					isNextLevel = true;
				}*/
				Integer level = existing.getLevel();
				Field[] fields = GrowthTypes.class.getDeclaredFields();
				for (var f: fields) {
					if (f.getName().contains("level")) {
						f.setAccessible(true);
						String tail = f.getName().split("level")[1];
						int nextLv = f.getInt(nextLevel);
						if (!tail.equals("_max")) { // TODO with max level? @Độ
							if (Integer.parseInt(tail) - 1 == level && existing.getNumber() + 1L == nextLv) {
								existing.setLevel(existing.getLevel() + 1);
								isNextLevel = true;
							}
						}
					}
				}
				existing.setNumber(existing.getNumber() + 1L);
				userItemsRepository.save(existing);
				return isNextLevel;
			}
		} else {
			throw new IllegalArgumentException(MsgUtil.getMessage("item.type.savegacha"));
		}
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
