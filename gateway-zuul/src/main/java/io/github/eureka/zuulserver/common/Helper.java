package io.github.eureka.zuulserver.common;

import java.util.Date;
import java.util.Random;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 19/04/2022<br/>
 */
public class Helper {
    public static String generateUserName() {
        Date now = new Date();
        Random r = new Random();
        int code = Integer.parseInt(String.valueOf(now.getTime()).substring(3))/100 * (r.nextInt(100) + 1);
        return encodeString() + "_" +  code;
    }

    private static String encodeString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            output.append((char)(new Random().nextInt(25) + 97));
        }
        return output.toString();
    }
}
