package com.bunbusoft.ayakashi.service.impl;

import com.bunbusoft.ayakashi.domain.entity.JewelResultEntity;
import com.bunbusoft.ayakashi.service.BaseService;
import com.bunbusoft.ayakashi.service.ProductManagerService;
import com.bunbusoft.ayakashi.service.dto.object.SearchFormDTO;
import com.bunbusoft.ayakashi.service.dto.paged.PageResultDTO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class ProductManagerServiceImpl extends BaseService implements ProductManagerService {

    public ProductManagerServiceImpl(EntityManager em) {
        super(em);
    }

    @Override
    public PageResultDTO<JewelResultEntity> searchPagination(SearchFormDTO filter) {
        return createNativeSql(SEARCH_SQL, COUNT_SEARCH_SQL, filter, JewelResultEntity.class);
    }

    private static final String SEARCH_SQL = "select jp.id as id,\n" +
            "       client_id,\n" +
            "       jp.product_code,\n" +
            "       w.id as wallet_id,\n" +
            "       w.display_name as wallet_name,\n" +
            "       jp.number as number,\n" +
            "       jp.bonus_wallet_id as bonus_wallet_id,\n" +
            "       bonus.display_name as bonus_wallet_name,\n" +
            "       jp.bonus_number as bonus_number,\n" +
            "       'false' as is_bought\n" +
            "\n" +
            "from jewel_products jp,\n" +
            "     wallets w,\n" +
            "     wallets bonus\n" +
            "where jp.wallet_id = w.id\n" +
            "and jp.bonus_wallet_id = bonus.id ";

    private static final String COUNT_SEARCH_SQL = "select count(jp.id)\n" +
            "from jewel_products jp,\n" +
            "     wallets w,\n" +
            "     wallets bonus\n" +
            "where jp.wallet_id = w.id\n" +
            "  and jp.bonus_wallet_id = bonus.id ";
}
