package io.github.eureka.api.service.impl;

import io.eventuate.common.json.mapper.JSonMapper;
import io.github.eureka.api.common.Constant;
import io.github.eureka.api.common.MsgUtil;
import io.github.eureka.api.model.Users;
import io.github.eureka.api.model.dto.ActionUserDTO;
import io.github.eureka.api.model.dto.PurchaseDTO;
import io.github.eureka.api.model.dto.google.SubscriptionPurchaseDTO;
import io.github.eureka.api.repo.UsersRepository;
import io.github.eureka.api.service.CommonService;
import lombok.AllArgsConstructor;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Arrays;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 07/04/2022<br/>
 * Time: 17:04<br/>
 */
@AllArgsConstructor
 @Service
public class CommonServiceImpl implements CommonService {
    private final OkHttpClient client = new OkHttpClient();
    private final UsersRepository usersRepository;
    @Override
    public Object verifyInAppPurchase(PurchaseDTO transInfo) throws IllegalAccessException, IOException, JSONException {
        if (Constant.PlatformType.ANDROID.getType() == transInfo.getPlatformType()) {
            String url = Constant.PLAY_STORE_TRANS_URL;
            url = url.replace("{packageName}", transInfo.getPackageName());
            url = url.replace("{subscriptionId}", transInfo.getSubscriptionId());
            url = url.replace("{token}", transInfo.getTokenProduct());
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            Call call = client.newCall(request);
            Response response = call.execute();
            assert response.body() != null;
            return JSonMapper.fromJson(new JSONObject(response.body().toString()).getString("resource"),
                    SubscriptionPurchaseDTO.class);

        } else if (Constant.PlatformType.IOS.getType() == transInfo.getPlatformType()) {
            // TODO
        }
        throw new IllegalAccessException(MsgUtil.getMessage("base.service.wrong.platform"));
    }

    public Users validateUser(ActionUserDTO userDTO) {
        Assert.notNull(userDTO, MsgUtil.getMessage("user.info.null"));
        Users user = usersRepository.findByUsernameAndStatusIn(userDTO.getSub(), Arrays.asList(0, 1)).orElseThrow (
                () -> new IllegalArgumentException(MsgUtil.getMessage("user.info.null"))
        );
        Assert.isTrue(user.getUsername().equals(userDTO.getSub()),
                MsgUtil.getMessage("user.invalid", user.getUsername()));
        return user;
    }
}