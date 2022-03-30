package com.bunbusoft.ayakashi.service;

import com.bunbusoft.ayakashi.service.dto.object.FilterDTO;
import com.bunbusoft.ayakashi.service.dto.object.FilterWrapperDTO;
import com.bunbusoft.ayakashi.service.dto.object.SearchFormDTO;
import com.bunbusoft.ayakashi.service.dto.paged.PageResultDTO;
import com.bunbusoft.ayakashi.utils.JpaUtil;
import lombok.AllArgsConstructor;
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

    public <T> PageResultDTO<T> createNativeSql(final String query, final String countQuery, SearchFormDTO data, Class<T> mapping) {
        StringBuilder sql = new StringBuilder(query);
        StringBuilder count = new StringBuilder(StringUtils.hasLength(countQuery) ? countQuery : "");
        HashMap<String, Object> params = new HashMap<>();
        var lst = data.getFilter();
        for (FilterDTO filter : lst) {
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
            pageable = PageRequest.of(
                    data.getCurrentPage(), data.getPageSize(),
                    StringUtils.hasLength(data.getSortField()) ?
                            Sort.by(
                                    data.isASC() ? Sort.Direction.ASC : Sort.Direction.DESC, data.getSortField())
                            : Sort.unsorted()
            );
            select = select.setFirstResult((data.getCurrentPage() - 1) * data.getPageSize()).setMaxResults(data.getPageSize());
        }
        List<T> result = select.getResultList();
        long totalPage = total % pageable.getPageSize() > 0 ? total / pageable.getPageSize() + 1 : total / pageable.getPageSize();
        return new PageResultDTO<T>(pageable, totalPage, total, result);
    }

    public String createWhlClause(StringBuilder query, final FilterWrapperDTO searchForm, HashMap<String, Object> params){
        var lst = searchForm.getFilterList();
        for (FilterDTO filter : lst) {
            query.append(String.format(" AND (%s %s :%s) ", filter.getField(), filter.getCondition(), filter.getField()));
            params.put(filter.getField(), filter.getKeyword());
        }
        return query.toString();
    }
}
