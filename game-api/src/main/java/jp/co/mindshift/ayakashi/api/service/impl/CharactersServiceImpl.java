package jp.co.mindshift.ayakashi.api.service.impl;

import jp.co.mindshift.ayakashi.api.common.MsgUtil;
import jp.co.mindshift.ayakashi.api.model.Characters;
import jp.co.mindshift.ayakashi.api.model.Users;
import jp.co.mindshift.ayakashi.api.repo.CharactersRepository;
import jp.co.mindshift.ayakashi.api.repo.UsersRepository;
import jp.co.mindshift.ayakashi.api.service.CharactersService;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class CharactersServiceImpl implements CharactersService {
    @PersistenceContext
    EntityManager em;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    CharactersRepository charactersRepository;
    private static final String sqlCharacter = "select c.* from users u left join user_items ui on u.id = ui.user_id and ui.item_type = 'CHARACTER' \n" +
            "left join items i on ui.item_id = i.id and i.item_type = 'CHARACTER' left join characters c on i.id = c.item_id \n" +
            "where u.id = :userId and ifnull(c.start_date, current_date) <= current_date and ifnull(c.end_date, current_date) >= current_date;";

    @Override
    public List<Characters> getCharacterByUser(Long userId) {
        Users users = usersRepository.getById(userId);
        Assert.notNull(users, MsgUtil.getMessage("user.info.null"));
    
        List<Characters> charactersList =  em.createNativeQuery(sqlCharacter , Characters.class)
                .setParameter("userId", userId)
                .getResultList();
        return charactersList;
    }

    @Override
    public Characters getDetailCharacter(Long id) {
        Characters characters = charactersRepository.getById(id);
        Assert.notNull(characters, MsgUtil.getMessage("character.notexists"));
        return characters;
    }
}
