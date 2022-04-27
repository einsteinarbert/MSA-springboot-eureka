package jp.co.mindshift.ayakashi.api.common;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import java.util.Collection;
import java.util.Map;

@Slf4j
@UtilityClass
public class DataUtil {
//    public static String makeLikeParam(String s){
//        if(StringUtils.isEmpty(s)) return s;
//        s = s.trim().toLowerCase().replace("%", Constants.DEFAULT_ESCAPE_CHAR + "%");
//        return "%" + s + "%";
//    }

    public static boolean isNullOrEmpty(final Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNullOrEmpty(final Object obj) {
        return obj == null || obj.toString().isEmpty();
    }

    public static boolean isNullOrEmpty(final Map<?, ?> map) {
        return map == null || map.isEmpty();
    }
}
