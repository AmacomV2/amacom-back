package com.amacom.amacom.util;


import java.text.SimpleDateFormat;
import java.util.Date;


public interface ITools {

    static boolean isFechaAMayorIgualQueFechaB(Date aFechaA, Date aFechaB, String aTipoComparacion) {
        boolean ok = false;
        if (aFechaA != null && aFechaB != null) {
            if (aTipoComparacion.equals(">")) {
                if (getStringFecha(aFechaA).compareTo(getStringFecha(aFechaB)) > 0) {
                    ok = true;
                }
            } else if (aTipoComparacion.equals("=")) {
                if (getStringFecha(aFechaA).compareTo(getStringFecha(aFechaB)) == 0) {
                    ok = true;
                }
            } else if (getStringFecha(aFechaA).compareTo(getStringFecha(aFechaB)) >= 0) {
                ok = true;
            }
        }

        return ok;
    }

    static boolean isFechaAMenorIgualQueFechaB(Date aFechaA, Date aFechaB, String aTipoComparacion) {
        boolean ok = false;
        if (aFechaA != null && aFechaB != null) {
            if (aTipoComparacion.equals("<")) {
                if (getStringFecha(aFechaA).compareTo(getStringFecha(aFechaB)) < 0) {
                    ok = true;
                }
            } else if (aTipoComparacion.equals("=")) {
                if (getStringFecha(aFechaA).compareTo(getStringFecha(aFechaB)) == 0) {
                    ok = true;
                }
            } else if (getStringFecha(aFechaA).compareTo(getStringFecha(aFechaB)) <= 0) {
                ok = true;
            }
        }

        return ok;
    }

    static String getStringFecha(Date aFecha) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        return aFecha != null ? formato.format(aFecha) : "";
    }

    String PATTERN_DATE = "yyyy-MM-dd";
    String PATTERN_DATE_FECHA_HORA_MINUTO = "yyyy-MM-dd HH:mm";
    String PATTERN_DATE_FECHA_HORA_MINUTO_SEGUNDO = "yyyy-MM-dd HH:mm:ss";
    String ZONA_HORARIA_BOGOTA= "America/Bogota";
}
