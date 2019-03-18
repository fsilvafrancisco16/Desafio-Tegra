package com.ffsilva.api.model.service.impl;

import com.ffsilva.api.model.dto.RotaDto;
import com.ffsilva.api.model.dto.VooDto;
import com.ffsilva.api.model.dto.VooRequestDto;
import com.ffsilva.api.model.entity.Voo;
import com.ffsilva.api.model.repository.impl.AeroportoRepositoryImpl;
import com.ffsilva.api.model.repository.impl.VooRepositoryImpl;
import com.ffsilva.api.model.response.Response;
import com.ffsilva.api.model.service.VooService;
import com.ffsilva.api.model.util.impl.TimeUtilImpl;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 *
 * @author francisco
 */
@Service
public class VooServiceImpl implements VooService {

    @Autowired
    private VooRepositoryImpl vooRepository;
    @Autowired
    private AeroportoRepositoryImpl aeroportoRepository;

    @Autowired
    private TimeUtilImpl timeUtil;

    @Override
    public ResponseEntity<Response<List<RotaDto>>> buscarRotasDeVoos(VooRequestDto dto, BindingResult result) {
        Response<List<RotaDto>> response = new Response<>();

        this.verificarErrosVooRequestDto(dto, result);
        if (!result.getAllErrors().isEmpty()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        List<RotaDto> rotas = this.buscarRotasDeVoos(dto);
        if (rotas.isEmpty()) {
            response.getErrors().add("Nenhum voo disponível.");
            return ResponseEntity.badRequest().body(response);
        }

        response.setData(rotas);
        return ResponseEntity.ok(response);
    }

    @Override
    public List<RotaDto> buscarRotasDeVoos(VooRequestDto dto) {
        List<RotaDto> rotas = new ArrayList<>();

        String origem = dto.getDe();
        String destino = dto.getPara();
        LocalDate data = this.timeUtil.toLocalDate(dto.getDate());

        this.separaRotas(this.vooRepository.findByOrigemData(origem, data), this.vooRepository.findByDestinoData(destino, data))
                .forEach(rotasList -> {
                    List<Voo> voos = new ArrayList<>();
                    rotasList.forEach(voo -> voos.add(voo));

                    rotas.add(this.vooParaRotaDto(voos, dto));
                });

        return rotas;
    }

    /**
     * Separa e organiza as rotas de voos.
     *
     * @param origens List<Voo>
     * @param destinos List<Voo>
     * @return List<List<Voo>>
     */
    private List<List<Voo>> separaRotas(List<Voo> origens, List<Voo> destinos) {
        List<List<Voo>> listVoos = new ArrayList<>();

        this.separaRotasSemEscalas(origens, destinos)
                .forEach(voos -> listVoos.add(voos));

        this.separaRotasComEscalas(origens, destinos)
                .forEach(voos -> listVoos.add(voos));

        return listVoos;
    }

    /**
     * Separa as rotas de voos sem escala.
     *
     * @param origens List<Voo>
     * @param destinos List<Voo>
     * @return List<List<Voo>>
     */
    private List<List<Voo>> separaRotasSemEscalas(List<Voo> origens, List<Voo> destinos) {
        List<List<Voo>> listVoos = new ArrayList<>();

        origens.forEach(origem -> {
            destinos.forEach(destino -> {
                if (origem.equals(destino)) {
                    List<Voo> rota = new ArrayList<>();
                    rota.add(origem);
                    listVoos.add(rota);
                }
            });
        });

        return listVoos;
    }

    /**
     * Separa as rotas de voos com escalas de dois voos, sendo o tempo de espera entre os voos não sejá maior que 12 horas.
     *
     * @param origens List<Voo>
     * @param destinos List<Voo>
     * @return List<List<Voo>>
     */
    private List<List<Voo>> separaRotasComEscalas(List<Voo> origens, List<Voo> destinos) {
        List<List<Voo>> listVoos = new ArrayList<>();

        origens.forEach(origem -> {
            destinos.forEach(destino -> {
                if ((origem.getAeroportoDestino().equalsIgnoreCase(destino.getAeroportoOrigem())) && (this.tempoDeEsperaValido(origem, destino))) {
                    List<Voo> rota = new ArrayList<>();
                    rota.add(origem);
                    rota.add(destino);
                    listVoos.add(rota);
                }
            });
        });

        return listVoos;
    }

    /**
     * Retorna TRUE o tempo de espera entre a chagada do primeiro voo e a saida se segundo voo for menor que 12 horas e maior que
     * 5 minutos.
     *
     * @param origem Voo
     * @param destino Voo
     * @return Boolean
     */
    private Boolean tempoDeEsperaValido(Voo origem, Voo destino) {
        LocalDateTime origemChegada = origem.getData().atTime(origem.getHorarioChegada());
        LocalDateTime destinoSaida = destino.getData().atTime(destino.getHorarioSaida());

        return !(this.tempoDeEsperaCurto(origemChegada, destinoSaida) && tempoDeEsperaLongo(origemChegada, destinoSaida));
    }

    /**
     * Retorna TRUE o tempo de espera entre a chagada do primeiro e a saido se segundo voo for maior que 12 horas.
     *
     * @param origemChegada LocalDateTime - Chegada do primeiro voo.
     * @param destinoSaida LocalDateTime - Saida do segundo voo.
     * @return Boolean
     */
    private Boolean tempoDeEsperaLongo(LocalDateTime origemChegada, LocalDateTime destinoSaida) {
        return origemChegada.plusHours(Long.valueOf(12)).isAfter(destinoSaida);
    }

    /**
     * Retorna TRUE se o primeiro voo não chegar com pelo menos 5 minutos de antecedência da saida do segundo voo.
     *
     * @param origemChegada LocalDateTime - Chegada do primeiro voo.
     * @param destinoSaida LocalDateTime - Saida do segundo voo.
     * @return Boolean
     */
    private Boolean tempoDeEsperaCurto(LocalDateTime origemChegada, LocalDateTime destinoSaida) {
        return origemChegada.plusMinutes(Long.valueOf(5)).isAfter(destinoSaida);
    }

    /**
     * Testa os dados informados para a busca dos voos, setá uma mensagem de erro para cada validação.
     *
     * @param requestDto VooRequestDto - com os dados da requisão
     * @param result BindingResult - para validação dos dados
     */
    private void verificarErrosVooRequestDto(VooRequestDto requestDto, BindingResult result) {
        if (!this.timeUtil.isValid(requestDto.getDate())) {
            result.addError(new ObjectError("data", "Data inválida."));
        }

        if (requestDto.getDe().equalsIgnoreCase(requestDto.getPara())) {
            result.addError(new ObjectError("para", "Origem e destino são iguais."));
        }

        if (!this.aeroportoRepository.findByAeroporto(requestDto.getDe()).isPresent()) {
            result.addError(new ObjectError("de", "Origem inválida."));
        }

        if (!this.aeroportoRepository.findByAeroporto(requestDto.getPara()).isPresent()) {
            result.addError(new ObjectError("para", "Destino inválido."));
        }
    }

    /**
     * Converte e popupa os dados passado para pesquisa de voos e lista de voos, para gerar uma roto.
     *
     * @param voos
     * @param dto
     * @return
     */
    private RotaDto vooParaRotaDto(List<Voo> voos, VooRequestDto dto) {
        RotaDto rota = new RotaDto();

        rota.setOrigem(this.aeroportoRepository.findByAeroporto(dto.getDe()).get());
        rota.setDestino(this.aeroportoRepository.findByAeroporto(dto.getPara()).get());
        rota.setData(dto.getDate());
        voos.forEach(voo -> rota.getVoos().add(this.vooParaVooDto(voo)));

        return rota;
    }

    /**
     * Popula um VooDto com os dados de um voo.
     *
     * @param voo Voo
     * @return VooDto
     */
    private VooDto vooParaVooDto(Voo voo) {
        VooDto dto = new VooDto();

        dto.setVoo(voo.getVoo());
        dto.setOrigem(this.aeroportoRepository.findByAeroporto(voo.getAeroportoOrigem()).get());
        dto.setDestino(this.aeroportoRepository.findByAeroporto(voo.getAeroportoDestino()).get());
        dto.setSaida(voo.getData().atTime(voo.getHorarioSaida()));
        dto.setChegada(voo.getData().atTime(voo.getHorarioChegada()));
        dto.setOperadora(voo.getOperadora());
        dto.setPreco(voo.getPreco());

        return dto;
    }
}
