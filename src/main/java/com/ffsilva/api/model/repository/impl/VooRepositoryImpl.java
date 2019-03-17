package com.ffsilva.api.model.repository.impl;

import com.ffsilva.api.model.auxiliar.VooAuxiliarConversao;
import com.ffsilva.api.model.entity.Voo;
import com.ffsilva.api.model.repository.VooRepository;
import com.ffsilva.api.model.util.impl.TimeUtilImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author francisco
 */
@Service
public class VooRepositoryImpl implements VooRepository {

    @Autowired
    private TimeUtilImpl timeUtil;

    private final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/main/resources/static/resources";
    private static final Logger LOG = LoggerFactory.getLogger(VooRepository.class);

    @Override
    public List<Voo> findAllVoosOperadoraUberair() {
        final String operadoraUberair = "Uberair";

        List<Voo> voosDaUberair = new ArrayList<>();
        this.buscarVoosNoCSV()
                .forEach(vooAuxilar -> voosDaUberair.add(this.vooAuxiliarParaVoo(vooAuxilar, operadoraUberair)));

        return voosDaUberair;
    }

    @Override
    public List<Voo> findAllVoosOperadora99Planes() {
        final String operadora99Planes = "99Planes";

        List<Voo> voosDa99Planes = new ArrayList<>();
        this.buscarVoosNoJson()
                .forEach(vooAuxilar -> voosDa99Planes.add(this.vooAuxiliarParaVoo(vooAuxilar, operadora99Planes)));

        return voosDa99Planes;
    }

    @Override
    public List<Voo> findAll() {
        List<Voo> todosOsVoos = new ArrayList<>();

        todosOsVoos.addAll(this.findAllVoosOperadoraUberair());
        todosOsVoos.addAll(this.findAllVoosOperadoraUberair());

        return todosOsVoos;
    }

    private Voo vooAuxiliarParaVoo(VooAuxiliarConversao auxiliar, String operadora) {
        Voo voo = new Voo();

        voo.setVoo(auxiliar.getVoo());
        voo.setAeroportoOrigem(auxiliar.getOrigem());
        voo.setAeroportoDestino(auxiliar.getDestino());
        voo.setData(this.timeUtil.toLocalDate(auxiliar.getData_saida()));
        voo.setHorarioSaida(this.timeUtil.toLocalTime(auxiliar.getSaida()));
        voo.setHorarioChegada(this.timeUtil.toLocalTime(auxiliar.getChegada()));
        voo.setPreco(auxiliar.getValor());
        voo.setOperadora(operadora);

        return voo;
    }

    /**
     * Ler e convertr os dados de voos no JSon;
     *
     * @return List<VooAuxiliarConversao>
     */
    private List<VooAuxiliarConversao> buscarVoosNoJson() {
        try {
            BufferedReader voosDoJson = new BufferedReader(new FileReader(RESOURCES_PATH + "/99planes.json"));

            return new Gson().fromJson(voosDoJson, new TypeToken<List<VooAuxiliarConversao>>() {
            }.getType());
        } catch (FileNotFoundException e) {

            LOG.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Ler e convertr os dados de voos no Csv;
     *
     * @return List<VooAuxiliarConversao>
     */
    private List<VooAuxiliarConversao> buscarVoosNoCSV() {
        List<VooAuxiliarConversao> voosAuxiliar = new ArrayList<>();

        try {
            Reader reader = Files.newBufferedReader(Paths.get(RESOURCES_PATH + "/uberair.csv"));

            CSVReader voosDoArquivo = new CSVReaderBuilder(reader).withSkipLines(1).build();

            voosDoArquivo.forEach(voo -> {
                VooAuxiliarConversao auxiliar = new VooAuxiliarConversao();

                auxiliar.setVoo(voo[0]);
                auxiliar.setOrigem(voo[1]);
                auxiliar.setDestino(voo[2]);
                auxiliar.setData_saida(voo[3]);
                auxiliar.setSaida(voo[4]);
                auxiliar.setChegada(voo[5]);
                auxiliar.setValor(new BigDecimal(voo[6]));

                voosAuxiliar.add(auxiliar);
            });

            return voosAuxiliar;
        } catch (IllegalStateException | IOException e) {

            LOG.error(e.getMessage());
            return voosAuxiliar;
        }
    }
}
