package io.github.eureka.api.service.impl;

import io.github.eureka.api.common.DataUtil;
import io.github.eureka.api.common.MsgUtil;
import io.github.eureka.api.model.GachaCharacters;
import io.github.eureka.api.model.Gachas;
import io.github.eureka.api.model.UserItems;
import io.github.eureka.api.model.dto.GachaCharactersDTO;
import io.github.eureka.api.model.dto.GachasDTO;
import io.github.eureka.api.model.dto.SpinGachaDTO;
import io.github.eureka.api.repo.GachasRepository;
import io.github.eureka.api.service.BaseService;
import io.github.eureka.api.service.GachasService;
import lombok.AllArgsConstructor;
import org.modelmapper.internal.util.Assert;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Service
public class GachasServiceImpl extends BaseService implements GachasService {
	private final GachasRepository gachasRepository;
	
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
	public GachaCharactersDTO spinGacha(SpinGachaDTO spinGachaDTO) {
		UserItems userItems = gachasRepository.getGachaByUserItem(spinGachaDTO.getUserId(), spinGachaDTO.getUserItemId());
		Assert.notNull(userItems, MsgUtil.getMessage("item.gacha.notexist"));
		List<GachaCharacters> lstGacha = gachasRepository.listGachaById(spinGachaDTO.getUserItemId());
		GachaCharacters resultSpin = this.randomGacha(lstGacha);
		return super.map(resultSpin, GachaCharactersDTO.class);
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
			if(rnd < lstGacha.get(i).getProbability())
				resultSpin =  lstGacha.get(i);
			rnd -= lstGacha.get(i).getProbability();
		}
		return resultSpin;
	}
}
