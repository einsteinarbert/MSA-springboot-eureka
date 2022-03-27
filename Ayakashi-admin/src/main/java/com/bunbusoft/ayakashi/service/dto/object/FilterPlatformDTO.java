package com.bunbusoft.ayakashi.service.dto.object;

public class FilterPlatformDTO {
    public String field;
    public String operator;
    public String value;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public FilterPlatformDTO() {
    }

    public FilterPlatformDTO(String field, String operator, String value) {

        this.field = field;
        this.operator = operator;
        this.value = value;
    }
}

