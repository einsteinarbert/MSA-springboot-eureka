package jp.co.mindshift.ayakashi.gateway.common;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 24/04/2022<br/>
 */
public interface ResponseCode {
    int SUCCESS = 0;
    int FAILURE = 1;
    int TOKEN_EXPIRED = 9;
}
