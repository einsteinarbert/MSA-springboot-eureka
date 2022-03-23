package com.bunbusoft.ayakashi.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Query;
import java.util.Map;
import java.util.Objects;

@Slf4j
@UtilityClass
public class JpaUtil {
    public void setQueryParams(Query query, Map<String, Object> params) {
        if (Objects.isNull(params) || params.isEmpty()) {
            return;
        }
        params.forEach(query::setParameter);
    }
}
