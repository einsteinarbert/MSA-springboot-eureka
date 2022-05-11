package jp.co.mindshift.ayakashi.api.model.dto;

import jp.co.mindshift.ayakashi.api.common.Constant;
import lombok.Data;

import java.util.Date;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 12/05/2022<br/>
 */
@Data
public class QuestDTO {
    private long id;
    private String name;
    private String description;
    private String scenarioFile;
    private Date createdAt;
    private Date updatedAt;
    private Integer status;
    private Date startDate;
    private Date endDate;
    private Integer type;
    private Integer questStatus;
}
