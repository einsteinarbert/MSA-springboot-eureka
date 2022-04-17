package io.github.eureka.api.service;

import io.github.eureka.api.model.Characters;

import java.util.List;

public interface CharactersService {
    List<Characters> getCharacterByUser(Long userId);

    Characters getDetailCharacter(Long id);
}
