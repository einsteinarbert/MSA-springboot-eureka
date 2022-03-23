package com.bunbusoft.ayakashi.service.impl;

import com.bunbusoft.ayakashi.domain.Clients;
import com.bunbusoft.ayakashi.repository.ClientsRepository;
import com.bunbusoft.ayakashi.service.ClientsService;
import com.bunbusoft.ayakashi.service.dto.ClientsDTO;
import com.bunbusoft.ayakashi.service.dto.ClientssDTO;
import com.bunbusoft.ayakashi.service.dto.NewClientForm;
import com.bunbusoft.ayakashi.utils.DataUtil;
import com.bunbusoft.ayakashi.utils.JpaUtil;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ClientsServiceImpl implements ClientsService {
    private ClientsRepository clientsRepository;

    public ClientsServiceImpl(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    @Autowired
    ModelMapper modelMapper;

    @PersistenceContext
    EntityManager em;

    @Override
    public List<ClientssDTO> searchClients(ClientssDTO searchForm) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder("SELECT id, client_token, name, client_type, IF(client_type = 0, 'ios', 'android') client_type_name, " +
                                                "required_version FROM clients WHERE 1=1 ");
        if(DataUtil.isNullOrEmpty(searchForm.getClientToken())){
            sql.append("AND client_token = :clientToken");
            params.put("clientToken", searchForm.getClientToken());
        }

        if(DataUtil.isNullOrEmpty(searchForm.getName())){
            sql.append("AND name = :name");
            params.put("name", searchForm.getName());
        }

        if(DataUtil.isNullOrEmpty(searchForm.getName())){
            sql.append("AND IF(client_type = 0, 'ios', 'android') = :clientType");
            params.put("clientType", searchForm.getClientTypeName());
        }
        Query query = em.createNativeQuery(sql.toString(), ClientssDTO.class);
        JpaUtil.setQueryParams(query, params);
        return query.getResultList();
    }

    @Override
    public String createOrUpdateClient(NewClientForm form, Model model) throws IOException, SQLException {
        Clients newClient;
        //Check tokenExists;
        Clients existClient = clientsRepository.findByClientToken(form.getClientToken());
        if(!DataUtil.isNullOrEmpty(existClient)){
            if(DataUtil.isNullOrEmpty(form.getId())) {
                model.addAttribute("error", "クライアントIDが存在します");
                return "pages/client-manager/add-form";
            }else{
                if(!existClient.getId().equals(form.getId())){
                    model.addAttribute("error", "クライアントIDが存在します");
                    return "redirect:/pages/client-manager/edit-client?id="+form.getId();
                }
            }
        }
        if(form.getId()  == null){
            newClient = modelMapper.map(form, Clients.class);
            newClient.setGooglePlayJsonFile(form.getGooglePlayJsonFile().getBytes());
            newClient.setCreatedAt(new Date());
            newClient.setUpdateAt(new Date());
            clientsRepository.save(newClient);
            return ":redirect/pages/client-manager/search-form?add-success";
        }else{
            Optional<Clients>  oldClients = clientsRepository.findById(form.getId());
            if(oldClients.isPresent()) {
                newClient = oldClients.get();
                newClient.setClientType(form.getClientType());
                newClient.setClientToken(form.getClientToken());
                newClient.setName(form.getName());
                newClient.setRequiredVersion(form.getRequiredVersion());
                newClient.setGooglePlayJsonFile(form.getGooglePlayJsonFile().getBytes());
                newClient.setItSecretKey(form.getItSecretKey());
                newClient.setUpdateAt(new Date());
                clientsRepository.save(newClient);
            }
            return ":redirect/pages/client-manager/search-form?edit-success";
        }
    }
}
