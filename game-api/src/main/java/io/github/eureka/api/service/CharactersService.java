package io.github.eureka.api.service;

import io.github.eureka.api.model.Characters;

public interface CharactersService {
    Characters getCharacterByUser(Long userId);
}
