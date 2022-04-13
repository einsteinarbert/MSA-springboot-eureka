package io.github.eureka.api.common;

import lombok.Getter;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/04/2022<br/>
 * Time: 13:34<br/>
 */
public interface Constant {
    String PLAY_STORE_TRANS_URL = "https://androidpublisher.googleapis.com/androidpublisher/v3/applications/{packageName}/purchases/subscriptions/{subscriptionId}/tokens/{token}";

    @Getter
    enum PlatformType {
        ALL(0), ANDROID(1), IOS(2);
        int type;

        PlatformType(int i) {
            this.type = i;
        }
    }
    @Getter
    enum UserHistoryType {
        BUY(0), USE(1), BONUS(2), GIFT(3), RETURN(4);
        int type;
        UserHistoryType(int i) {
            type = i;
        }
    }
    @Getter
    enum WalletType {
        JEWEL(0), COIN(1);
        int type;
        WalletType(int i) {
            type = i;
        }
        public static String getName(int i) {
            for(WalletType e : WalletType.values()){
                if(e.type == i) return e.name();
            }
            return null;
        }
    }
    @Getter
    enum JewelType {
        BUY(0), BONUS(1);
        int type;
        JewelType(int i) {
            type = i;
        }
    }

    @Getter
    enum PayStateAndroid {
        PAYMENT_PENDING("payment.pending", 0),
        PAYMENT_RECEIVED("payment.received", 1),
        FREE_TRIAL("free.trial", 2),
        PENDING_DEFER("pending.deferred.up.downgrade", 3);
        String text; int value;
        PayStateAndroid(String text, int value) {
            this.text = text;
            this.value = value;
        }
    }

    // ISO 4217
    @Getter
    enum CurrencyCode {
        JPT(0), USD(1), VND(2);
        int value;
        CurrencyCode(int i) {
            value = i;
        }
        public static int getValue(String code) {
            for(CurrencyCode e : CurrencyCode.values()){
                if(e.name().equals(code)) return e.getValue();
            }
            return -1;
        }
    }
    @Getter
    enum TransactionType {
        WALLET_LOG(0), USER_ITEM_LOG(1);
        int value;
        TransactionType(int i) {
            value = i;
        }
        public static int getValue(String code) {
            for(CurrencyCode e : CurrencyCode.values()){
                if(e.name().equals(code)) return e.getValue();
            }
            return -1;
        }
    }


    interface STATUS {
        Integer ANONYMOUS = 0;
        Integer REGITERED = 1;
        Integer DEACTIVE = 2;
    }
}
