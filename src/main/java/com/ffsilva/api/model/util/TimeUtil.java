package com.ffsilva.api.model.util;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author francisco
 */
public interface TimeUtil {

    /**
     * Converte uma hora String para LocalTime
     *
     * @param hora String - hora no formato HH:mm
     * @return LocalTime
     */
    LocalTime toLocalTime(String hora);

    /**
     * Converte uma data String para LocalTime
     *
     * @param hora String - data no formato YYYY-MM-dd
     * @return LocalDate
     */
    LocalDate toLocalDate(String hora);

    /**
     * Testa se a string informada possue uma data valida.
     *
     * @param data String - data no formato yyyy-MM-dd
     * @return boolean
     */
    boolean isValid(String data);
}
