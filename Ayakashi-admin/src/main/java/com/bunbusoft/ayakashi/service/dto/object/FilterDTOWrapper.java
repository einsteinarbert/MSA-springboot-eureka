package com.bunbusoft.ayakashi.service.dto.object;

import java.util.ArrayList;

public class FilterDTOWrapper {
    private ArrayList<FilterPlatformDTO> clientList;

    public ArrayList<FilterPlatformDTO> getClientList() {
        return clientList;
    }
    public void setClientList(ArrayList<FilterPlatformDTO> clients) {
        this.clientList = clients;
    }
}
