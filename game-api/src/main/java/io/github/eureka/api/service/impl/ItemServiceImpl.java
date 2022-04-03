package io.github.eureka.api.service.impl;

import io.github.eureka.api.common.MsgUtil;
import io.github.eureka.api.config.ActionUserHolder;
import io.github.eureka.api.entity.dto.ActionUserDTO;
import io.github.eureka.api.entity.dto.SaleInfoDTO;
import io.github.eureka.api.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/04/2022<br/>
 * Time: 13:41<br/>
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Override
    public boolean buyItem(SaleInfoDTO saleInfo) {
        ActionUserDTO userDTO = ActionUserHolder.getActionUser();
        Assert.notNull(userDTO, MsgUtil.getMessage("user.info.null"));
        Assert.notNull(saleInfo.getUserId(), MsgUtil.getMessage("sale.trans.info.null"));
        return false;
    }
}
