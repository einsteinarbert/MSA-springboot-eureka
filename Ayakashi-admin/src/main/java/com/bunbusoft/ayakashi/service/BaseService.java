package com.bunbusoft.ayakashi.service;

import com.bunbusoft.ayakashi.service.dto.FilterJewelDTO;
import com.bunbusoft.ayakashi.service.dto.object.JewelFormDTO;
import com.bunbusoft.ayakashi.service.dto.paged.PageableCustom;
import com.bunbusoft.ayakashi.utils.JpaUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@Service
public class BaseService {
    @PersistenceContext
    private final EntityManager em;

    public <T> PageImpl<T> createNativeSql(final String query, final String countQuery, JewelFormDTO data, Class<T> mapping) {
        StringBuilder sql = new StringBuilder(query);
        StringBuilder count = new StringBuilder(StringUtils.hasLength(countQuery) ? countQuery : "");
        HashMap<String, Object> params = new HashMap<>();
        var lst = data.getFilter();
        for (FilterJewelDTO filter : lst) {
            sql.append(String.format(" AND (%s %s :%s) ", filter.getField(), filter.getCondition(), filter.getField()));
            count.append(String.format(" AND (%s %s :%s) ", filter.getField(), filter.getCondition(), filter.getField()));
            params.put(filter.getField(), filter.getKeyword());
        }

        long total = -1;
        Pageable pageable = Pageable.unpaged();
        Query select = em.createNativeQuery(sql.toString(), mapping);

        JpaUtil.setQueryParams(select, params);
        if (StringUtils.hasLength(countQuery)) {
            Query cq = em.createNativeQuery(count.toString());
            JpaUtil.setQueryParams(cq, params);
            total = ((BigInteger) cq.getSingleResult()).longValue();
/*            pageable = new PageableCustom(data.getCurrentPage(), data.getPageSize(),
                    StringUtils.hasLength(data.getSortField()) ?
                            Sort.by(
                                    data.isASC() ? Sort.Direction.ASC : Sort.Direction.DESC, data.getSortField())
                            :Sort.unsorted()
            );*/
            pageable = PageRequest.of(
                    data.getCurrentPage(), data.getPageSize(),
                    StringUtils.hasLength(data.getSortField()) ?
                            Sort.by(
                                    data.isASC() ? Sort.Direction.ASC : Sort.Direction.DESC, data.getSortField())
                            :Sort.unsorted()
            );
            select = select.setFirstResult(data.getCurrentPage()).setMaxResults(data.getPageSize());
        }
        List<T> result = select.getResultList();
        return new PageImpl<>(result, pageable, total);
    }
}
