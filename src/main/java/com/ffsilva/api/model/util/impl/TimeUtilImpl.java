package com.ffsilva.api.model.util.impl;

import com.ffsilva.api.model.util.TimeUtil;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

/**
 *
 * @author francisco
 */
@Service
public class TimeUtilImpl implements TimeUtil {

    // Formatos de atributos de tempo ultilizados
    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    //Definindo o fuso horario para o Brasil
    private final ZoneId TIME_ZONE_BR = ZoneId.of("America/Sao_Paulo");
    private ZonedDateTime timeBr;

    @Override
    public LocalTime toLocalTime(String hora) {
        return LocalTime.parse(hora, TIME_FORMAT);
    }

    @Override
    public LocalDate toLocalDate(String date) {
        return LocalDate.parse(date, DATE_FORMAT);
    }

    @Override
    public boolean isValid(String data) {
        try {
            LocalDate.parse(data, DATE_FORMAT);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }
}
