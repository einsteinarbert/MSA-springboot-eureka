package io.github.eureka.api.common;

import lombok.Data;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/04/2022<br/>
 * Time: 13:34<br/>
 */
public interface Constant {
    enum Platform {
        ALL(0), ANDROID(1), IOS(2);
        int type;

        Platform(int i) {
            this.type = i;
        }

        public int getType() {
            return type;
        }
    }
}
