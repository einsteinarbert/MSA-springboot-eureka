package io.github.eureka.api.common;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/04/2022<br/>
 * Time: 13:34<br/>
 */
public interface Constant {
    enum PlatformType {
        ALL(0), ANDROID(1), IOS(2);
        int type;

        PlatformType(int i) {
            this.type = i;
        }

        public int getType() {
            return type;
        }
    }
    enum UserHistoryType {
        BUY(0), USE(1), BONUS(2), GIFT(3), RETURN(4);
        int type;
        UserHistoryType(int i) {
            type = i;
        }
        public int getType() {return type;}
    }
    enum WalletType {
        JEWEL(0), COIN(1);
        int type;
        WalletType(int i) {
            type = i;
        }
        public int getType() {return type;}
        public static String getName(int i) {
            for(WalletType e : WalletType.values()){
                if(e.type == i) return e.name();
            }
            return null;
        }
    }
    enum JewelType {
        BUY(0), BONUS(1);
        int type;
        JewelType(int i) {
            type = i;
        }
        public int getType() {return type;}
    }
    interface STATUS {
        Integer ANONYMOUS = 0;
        Integer REGITERED = 1;
        Integer DEACTIVE = 2;
    }
}
