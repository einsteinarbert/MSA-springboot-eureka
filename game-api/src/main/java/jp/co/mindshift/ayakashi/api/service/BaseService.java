package jp.co.mindshift.ayakashi.api.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public abstract class BaseService {

	@Autowired
	private ModelMapper modelMapper;

	protected <T, E> T map(E entity, Class<T> clazz) {
		return modelMapper.map(entity, clazz);
	}

	protected <T, E> List<T> mapList(List<E> inputData, Class<T> clazz) {
		return CollectionUtils.isEmpty(inputData) ?
				Collections.emptyList() :
				inputData.stream()
						.map(i -> modelMapper.map(i, clazz))
						.collect(Collectors.toList());
	}


}
