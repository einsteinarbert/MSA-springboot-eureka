package com.bunbusoft.ayakashi.utils;

import com.bunbusoft.ayakashi.view.Option;
import com.bunbusoft.ayakashi.view.RowFilter;
import com.bunbusoft.ayakashi.view.Selector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ViewUtils {
    public static <T> List<RowFilter> getFormCondition(Map<String, String> fields) {
        List<RowFilter> list = new ArrayList<>();
        List<Option> lst = new ArrayList<>();
        for(var op: fields.entrySet()) {
            lst.add(Option.builder()
                    .value(op.getKey())
                    .text(op.getValue())
                    .build());
        }
        Selector field = Selector.builder()
                .showLabel(false)
                .showToolTip(true)
                .toolTip("フィールド")
                .options(lst)
                .build();
        RowFilter row = RowFilter.builder()
                .isDefault(true)
                .selectors(new ArrayList<>())
                .build();
        row.getSelectors().add(field);
        List<Option> operators = Arrays.asList(
                new Option("<", 1), // 0 (zero) value is default value and should not be set here
                new Option("<=", 2),
                new Option("=", 3),
                new Option(">=", 4),
                new Option(">", 5),
                new Option("LIKE", 6),
                new Option("NOT LIKE", 7),
                new Option("IN", 8)
        );
        var conditions = Selector.builder()
                .showLabel(false)
                .showToolTip(true)
                .toolTip("条件")
                .options(operators)
                .build();
        row.getSelectors().add(conditions);

        var keywords = Selector.builder()
                .showLabel(false)
                .showToolTip(true)
                .toolTip("キーワード")
                .build();
        row.getSelectors().add(keywords);
        list.add(row);
        return list;
    }
}
