package jp.co.mindshift.ayakashi.api.common;

import lombok.Getter;

import javax.persistence.criteria.CriteriaBuilder;

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
        final int type;

        PlatformType(int i) {
            this.type = i;
        }
    }
    @Getter
    enum UserHistoryType {
        BUY(0), USE(1), BONUS(2), GIFT(3), RETURN(4);
        final int type;
        UserHistoryType(int i) {
            type = i;
        }
    }
    @Getter
    enum WalletType {
        JEWEL(0), COIN(1);
        final int type;
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
        final int type;
        JewelType(int i) {
            type = i;
        }
    }

    @Getter
    enum walletHistoryType {
        BUY(0), USE(1), BONUS(2), GIFT(3), RETURN(4);
        final int type;
        walletHistoryType(int i) {
            type = i;
        }
    }

    @Getter
    enum PaymentMethodType {
        JEWEL(0), COIN(1);
        final int type;
        PaymentMethodType(int i) {
            type = i;
        }
    }

    @Getter
    enum PayStateAndroid {
        PAYMENT_PENDING("payment.pending", 0),
        PAYMENT_RECEIVED("payment.received", 1),
        FREE_TRIAL("free.trial", 2),
        PENDING_DEFER("pending.deferred.up.downgrade", 3);
        final String text; final int value;
        PayStateAndroid(String text, int value) {
            this.text = text;
            this.value = value;
        }
    }
    interface STATUS {
        Integer ANONYMOUS = 0;
        Integer REGITERED = 1;
        Integer DEACTIVE = 2;
    }
    
    interface ITEMTYPE {
        String GACHA = "GACHA";
        String CHARACTER = "CHARACTER";
        String HEART = "HEART";
        String HEART30 = "HEART30";
        String HEART60 = "HEART60";
        String MEDAL = "MEDAL";
        String PREMIUM_MEDAL = "PREMIUM_MEDAL";
        String BACKGROUND = "BACKGROUND";
    }
    
    interface SPECIAL_ITEM_TYPE{
        Integer MEDAL = 6;
    }
    
    interface CHARACTER_DEFAULT{
        String MALE = "ch00101000";
        String FEMALE = "ch00201000";
    }
    
    interface SPIN_GACHA_PAYMENT{
        Integer TICKET = 2;
        Integer JEWELORCOIN = 1;
    }
    
    interface TICKET_PAYMENT_METHOD{
        Integer NORMAL_TICKET = 0;
        Integer PREMIUM_TICKET = 1;
        Integer PICKUP_TICKET = 2;
    }
    
    String BACKGROUND_DEFAULT = "bg0100";

    @Getter
    enum UserQuestStatus {
        CANCEL(0), AVAILABLE(1), PLAYING(2), CLEARED(3);
        final int type;
        UserQuestStatus(int i) {
            type = i;
        }
    }
    int MAX_QUEST = 5;
    int MAX_OLD_DAY_QUEST = -7;
    int MAX_DAY_REFRESH_QUEST = -2; // cancel quest after two days for get new quests
    int MAX_UPDATE_POSITION_GAP_IN_SECS = -60;
    int PEOPLE_COUNTER_STEP = 10;
}
