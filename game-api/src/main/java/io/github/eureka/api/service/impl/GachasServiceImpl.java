package io.github.eureka.api.service.impl;

import io.github.eureka.api.common.Constant;
import io.github.eureka.api.common.DataUtil;
import io.github.eureka.api.common.MsgUtil;
import io.github.eureka.api.model.*;
import io.github.eureka.api.model.dto.*;
import io.github.eureka.api.repo.*;
import io.github.eureka.api.service.BaseService;
import io.github.eureka.api.service.GachasService;
import lombok.AllArgsConstructor;
import org.modelmapper.internal.util.Assert;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
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

	@Override
	public List<GachasDTO> getAllGachaForSpin() {
		return super.mapList(gachasRepository.findAll(), GachasDTO.class);
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
	public GachaResultDTO spinGacha(SpinGachaDTO spinGachaDTO) {
		UserItems userItems = gachasRepository.getGachaByUserItem(spinGachaDTO.getUserId(), spinGachaDTO.getUserItemId());
		Assert.notNull(userItems, MsgUtil.getMessage("item.gacha.notexist"));
		List<GachaCharacters> lstGacha = gachasRepository.listGachaById(spinGachaDTO.getUserItemId());
		GachaResultDTO gachaResultDTO;
		GachaCharacters resultSpin = this.randomGacha(lstGacha);
		gachaResultDTO = super.map(resultSpin, GachaResultDTO.class);
		Characters resultGachaCharacter = charactersRepository.findById(gachaResultDTO.getCharacterId()).get();
		//Check level cua userItems 
		UserItems userCharacterItem = userItemsRepository.findUserItemsByUserIdAndItemId(spinGachaDTO.getUserId(), resultGachaCharacter.getItemId());
		GrowthTypes growthTypes = growthTypesRepository.getById(resultGachaCharacter.getGrowthTypeId());
		Integer maxCharacterToUp = growthTypes.getLevel2() + growthTypes.getLevel3() + growthTypes.getLevel4() + growthTypes.getLevel5() + growthTypes.getLevel6();
		if(growthTypes.getLevelMax() == userCharacterItem.getLevel() && userCharacterItem.getNumber() == Long.valueOf(maxCharacterToUp)){
			SpecialItems specialItems = specialItemsRepository.getSpecialItemsBySpecialItemType("MEDAL");
			gachaResultDTO.setSpecialItems(specialItems);
		}
		gachaResultDTO.setCharacters(resultGachaCharacter);
		userItems.setNumber(userItems.getNumber() - 1L);
		userItemsRepository.save(userItems);
		return gachaResultDTO;
	}

	@Override
	public Boolean saveBonusGacha(UserItemsDTO userItemsDTO) {

		Users existingUser = usersRepository.findById(userItemsDTO.getUserId()).get();
		Assert.notNull(existingUser, MsgUtil.getMessage("user.info.null"));
		
		Items existingItem = itemsRepository.findById(userItemsDTO.getItemId()).get();
		Assert.notNull(existingItem, MsgUtil.getMessage("item.notexist"));
		Boolean isNextLevel =false;
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
				return isNextLevel;
			}else{
				existingUserItem.setNumber(existingUserItem.getNumber() + 1L);
				userItemsRepository.save(existingUserItem);
				return isNextLevel;
			}
		}else if (Constant.ITEMTYPE.CHARACTER.equals(existingItem.getItemType())){
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
				return isNextLevel;
			}else{
				Characters characters = charactersRepository.getCharactersByItemId(existing.getItemId());
				GrowthTypes nextLevel = growthTypesRepository.findById(characters.getGrowthTypeId()).get();
				if((existing.getLevel() == 1 && (existing.getNumber() + 1L ==  Long.valueOf(nextLevel.getLevel2())))
				|| (existing.getLevel() == 2 && (existing.getNumber() + 1L == Long.valueOf(nextLevel.getLevel3())))
				|| (existing.getLevel() == 3 && (existing.getNumber() + 1L == Long.valueOf(nextLevel.getLevel4())))
				|| (existing.getLevel() == 4 && (existing.getNumber() + 1L == Long.valueOf(nextLevel.getLevel5())))
				|| (existing.getLevel() == 5 && (existing.getNumber() + 1L == Long.valueOf(nextLevel.getLevel6())))){
					existing.setLevel(existing.getLevel() + 1);
					isNextLevel = true;
				}
				existing.setNumber(existing.getNumber() + 1L);
				userItemsRepository.save(existing);
				return isNextLevel;
			}
		}else{
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
