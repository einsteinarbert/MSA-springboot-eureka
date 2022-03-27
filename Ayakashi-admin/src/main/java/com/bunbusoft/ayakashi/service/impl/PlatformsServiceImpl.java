package com.bunbusoft.ayakashi.service.impl;

import com.bunbusoft.ayakashi.commons.Constants;
import com.bunbusoft.ayakashi.domain.Platforms;
import com.bunbusoft.ayakashi.repository.PlatformsRepository;
import com.bunbusoft.ayakashi.service.PlatformsService;
import com.bunbusoft.ayakashi.service.dto.object.*;
import com.bunbusoft.ayakashi.service.dto.paged.PageableCustom;
import com.bunbusoft.ayakashi.utils.DataUtil;
import com.bunbusoft.ayakashi.utils.JpaUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class PlatformsServiceImpl implements PlatformsService {
    private PlatformsRepository platformsRepository;

    public PlatformsServiceImpl(PlatformsRepository platformsRepository) {
        this.platformsRepository = platformsRepository;
    }

    @Autowired
    ModelMapper modelMapper;

    @PersistenceContext
    EntityManager em;

    @Override
    public Page<ClientssDTO> searchPlatform(FilterDTOWrapper searchForm, int pageNumber) {
        PageableCustom pageable = new PageableCustom(pageNumber, Constants.numberPerPage, null);
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder("SELECT id, platform_token, name, platform_type, IF(platform_type = 0, 'ios', 'android') platform_type_name, " +
                                                "required_version FROM platforms WHERE 1=1 ");
//        if(!DataUtil.isNullOrEmpty(searchForm.getPlatformToken())){
//            sql.append("AND platform_token = :platformToken ");
//            params.put("platformToken", searchForm.getPlatformToken());
//        }
//
//        if(!DataUtil.isNullOrEmpty(searchForm.getName())){
//            sql.append("AND name = :name ");
//            params.put("name", searchForm.getName());
//        }
//
//        if(!DataUtil.isNullOrEmpty(searchForm.getName())){
//            sql.append("AND IF(platform_type = 0, 'ios', 'android') = :platformType ");
//            params.put("platformType", searchForm.getPlatformTypeName());
//        }
        String sqlCount = "SELECT COUNT(id) FROM ( " + sql + " ) T";
        Query queryCount = em.createNativeQuery(sqlCount);
        JpaUtil.setQueryParams(queryCount, params);
        Query query = em.createNativeQuery(sql.toString(), ClientssDTO.class).setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize());;
        JpaUtil.setQueryParams(query, params);
        List<ClientssDTO> result = query.getResultList();
        Long count = ((BigInteger) queryCount.getSingleResult()).longValue();
        return new PageImpl<>(result, pageable, count);
    }

    @Override
    public String createOrUpdatePlatform(NewPlatform form, Model model) throws IOException, SQLException {
        Platforms newClient;
        //Check tokenExists;
        Platforms existClient = platformsRepository.findByPlatFormToken(form.getPlatformToken());
        if(!DataUtil.isNullOrEmpty(existClient)){
            if(DataUtil.isNullOrEmpty(form.getId())) {
                model.addAttribute("error", "クライアントIDが存在します");
                return "pages/platform-manager/add-platform";
            }else{
                if(!existClient.getId().equals(form.getId())){
                    model.addAttribute("error", "クライアントIDが存在します");
                    return "redirect:/pages/platform-manager/edit-platform?id="+form.getId();
                }
            }
        }
        if(form.getId()  == null){
            newClient = modelMapper.map(form, Platforms.class);
            newClient.setGooglePlayJsonFile(form.getGooglePlayJsonFile().getBytes());
            newClient.setCreatedAt(new Date());
            newClient.setUpdateAt(new Date());
            platformsRepository.save(newClient);
            return ":redirect/pages/platform-manager/search-platform?add-success";
        }else{
            Optional<Platforms>  oldClients = platformsRepository.findById(form.getId());
            if(oldClients.isPresent()) {
                newClient = oldClients.get();
                newClient.setPlatformType(form.getPlatformType());
                newClient.setPlatFormToken(form.getPlatformToken());
                newClient.setName(form.getName());
                newClient.setRequiredVersion(form.getRequiredVersion());
                newClient.setGooglePlayJsonFile(form.getGooglePlayJsonFile().getBytes());
                newClient.setItSecretKey(form.getItSecretKey());
                newClient.setUpdateAt(new Date());
                platformsRepository.save(newClient);
            }
            return ":redirect/pages/platform-manager/search-form?edit-success";
        }
    }
}
