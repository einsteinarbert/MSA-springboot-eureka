package io.github.eureka.api.model.dto.google;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Doc: https://developers.google.com/android-publisher/api-ref/rest/v3/purchases.subscriptions/get
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 07/04/2022<br/>
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscriptionPurchaseDTO {
    private String kind;
    private String startTimeMillis;
    private String expiryTimeMillis;
    private String autoResumeTimeMillis;
    private boolean autoRenewing;
    private String priceCurrencyCode;
    private String priceAmountMicros;
    private IntroductoryPriceInfoDTO introductoryPriceInfo;
    private String countryCode;
    private String developerPayload;
    private Integer paymentState;
    private Integer cancelReason;
    private String userCancellationTimeMillis;
    private SubscriptionCancelSurveyResultDTO cancelSurveyResult;
    private String orderId;
    private String linkedPurchaseToken;
    private String purchaseType;
    private SubscriptionPriceChangeDTO priceChange;
    private String profileName;
    private String emailAddress;
    private String givenName;
    private String familyName;
    private String profileId;
    private String acknowledgementState;
    private String externalAccountId;
    private String promotionType;
    private String promotionCode;
    private String obfuscatedExternalAccountId;
    private String obfuscatedExternalProfileId;
}
