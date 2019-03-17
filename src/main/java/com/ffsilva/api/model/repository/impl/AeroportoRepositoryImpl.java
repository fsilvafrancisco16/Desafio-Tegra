package com.ffsilva.api.model.repository.impl;

import com.ffsilva.api.model.auxiliar.AeroportosCidadeSort;
import com.ffsilva.api.model.entity.Aeroporto;
import com.ffsilva.api.model.repository.AeroportoRepository;
import com.ffsilva.api.model.service.impl.AeroportoServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author francisco
 */
@Service
public class AeroportoRepositoryImpl implements AeroportoRepository {

    @Autowired
    private AeroportosCidadeSort aeroportosCidadeSort;

    private final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/main/resources/static/resources";
    private static final Logger LOG = LoggerFactory.getLogger(AeroportoServiceImpl.class);

    @Override
    public List<Aeroporto> findAll() {

        try {
            BufferedReader aeroportosJson = new BufferedReader(new FileReader(RESOURCES_PATH + "/aeroportos.json"));

            List<Aeroporto> aeroportos = new Gson().fromJson(aeroportosJson, new TypeToken<List<Aeroporto>>() {
            }.getType());

            aeroportos.sort(aeroportosCidadeSort);

            return aeroportos;
        } catch (FileNotFoundException e) {
            LOG.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Aeroporto> findByAeroporto(String id) {
        for (Aeroporto aeroporto : this.findAll()) {
            if (aeroporto.getAeroporto().equalsIgnoreCase(id)) {
                return Optional.of(aeroporto);
            }
        }

        return Optional.empty();
    }
}
