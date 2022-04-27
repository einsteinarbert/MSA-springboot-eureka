package jp.co.mindshift.ayakashi.api.config;


import jp.co.mindshift.ayakashi.api.model.dto.ActionUserDTO;
import org.springframework.core.NamedThreadLocal;

public class ActionUserHolder {

    private static final ThreadLocal<ActionUserDTO> userHolder = new NamedThreadLocal<>("ActionUserHolder");

    public static void setActionUser(ActionUserDTO actionUser) {
        ActionUserHolder.userHolder.set(actionUser);
    }

    public static ActionUserDTO getActionUser() {
        return userHolder.get();
    }

    public static void reset(){
        userHolder.remove();
    }
}
