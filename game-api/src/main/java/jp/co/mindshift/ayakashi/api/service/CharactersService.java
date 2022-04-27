package jp.co.mindshift.ayakashi.api.service;

import jp.co.mindshift.ayakashi.api.model.Characters;

import java.util.List;

public interface CharactersService {
    List<Characters> getCharacterByUser(Long userId);

    Characters getDetailCharacter(Long id);
}
