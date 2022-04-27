package jp.co.mindshift.ayakashi.api.common;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 04/04/2022<br/>
 * Time: 21:58<br/>
 */
public class HelpUtil {
    public static String getTransNumber(Long userId) {
        return userId.toString() + "_" + System.nanoTime();
    }
}
