package com.amacom.amacom.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

public interface ITools {

    static boolean isDateAMayorEqualThanDateB(Date aDateA, Date aDateB, String aTypeComparison) {
        boolean ok = false;
        if (aDateA != null && aDateB != null) {
            if (aTypeComparison.equals(">")) {
                if (getStringDate(aDateA).compareTo(getStringDate(aDateB)) > 0) {
                    ok = true;
                }
            } else if (aTypeComparison.equals("=")) {
                if (getStringDate(aDateA).compareTo(getStringDate(aDateB)) == 0) {
                    ok = true;
                }
            } else if (getStringDate(aDateA).compareTo(getStringDate(aDateB)) >= 0) {
                ok = true;
            }
        }

        return ok;
    }

    static boolean isDateAGreaterEqualThanDateB(Date aDateA, Date aDateB, String aTypeComparison) {
        boolean ok = false;
        if (aDateA != null && aDateB != null) {
            if (aTypeComparison.equals("<")) {
                if (getStringDate(aDateA).compareTo(getStringDate(aDateB)) < 0) {
                    ok = true;
                }
            } else if (aTypeComparison.equals("=")) {
                if (getStringDate(aDateA).compareTo(getStringDate(aDateB)) == 0) {
                    ok = true;
                }
            } else if (getStringDate(aDateA).compareTo(getStringDate(aDateB)) <= 0) {
                ok = true;
            }
        }

        return ok;
    }

    static Pageable getPageRequest(Pageable pageable, Map<String, String> keysToSort) {
        Sort sort = Sort.unsorted();

        Sort.Order s;
        for (Iterator<Order> var3 = pageable.getSort().iterator(); var3.hasNext(); sort = sort
                .and(Sort.by(s.getDirection(), new String[] { (String) keysToSort.get(s.getProperty()) }))) {
            s = (Sort.Order) var3.next();
        }

        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
    }

    static String getStringDate(Date aDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return aDate != null ? format.format(aDate) : "";
    }

    String PATTERN_DATE = "yyyy-MM-dd";
    String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm";
    String PATTERN_DATE_TIME_SECOND = "yyyy-MM-dd HH:mm:ss";
    String TIME_ZONE_BOGOTA = "America/Bogota";
}
