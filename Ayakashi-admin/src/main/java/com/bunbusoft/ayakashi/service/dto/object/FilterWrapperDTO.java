package com.bunbusoft.ayakashi.service.dto.object;

import java.util.ArrayList;

public class FilterWrapperDTO {
    private ArrayList<FilterDTO> filterList;

    public ArrayList<FilterDTO> getFilterList() {
        return filterList;
    }
    public void setFilterList(ArrayList<FilterDTO> clients) {
        this.filterList = clients;
    }

    public FilterWrapperDTO() {
    }

    public FilterWrapperDTO(ArrayList<FilterDTO> filterList) {
        this.filterList = filterList;
    }
}
