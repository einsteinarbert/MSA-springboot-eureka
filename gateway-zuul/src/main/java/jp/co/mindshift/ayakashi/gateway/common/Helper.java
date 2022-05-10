package jp.co.mindshift.ayakashi.gateway.common;

import java.util.Date;
import java.util.Random;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 19/04/2022<br/>
 */
public class Helper {
    public static final int LENGTH = 6;
    public static String generateUserName() {
        Date now = new Date();
        Random r = new Random();
        String time = String.valueOf(now.getTime()).substring(3);
        int code = (int) (Long.parseLong(time)/100 * (r.nextInt(100) + 1));
        return encodeString(LENGTH) + "_" +  code;
    }

    private static String encodeString(int input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input; i++) {
            output.append((char)(new Random().nextInt(25) + 97));
        }
        return output.toString();
    }
}
