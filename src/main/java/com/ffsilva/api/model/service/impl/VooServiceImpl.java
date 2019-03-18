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

        List<RotaDto> rotas = new ArrayList<>();
        this.buscarRotasSemEscala(dto).forEach(rota -> rotas.add(rota));

        if (rotas.isEmpty()) {
            response.getErrors().add("Nenhum voo disponível.");
            return ResponseEntity.badRequest().body(response);
        }

        response.setData(rotas);
        return ResponseEntity.ok(response);
    }

    @Override
    public List<RotaDto> buscarRotasSemEscala(VooRequestDto dto) {
        List<RotaDto> rotas = new ArrayList<>();

        this.vooRepository.findByVoosDiretos(dto.getDe(), dto.getPara(), this.timeUtil.toLocalDate(dto.getDate()))
                .forEach(voo -> {
                    List<Voo> voos = new ArrayList<>();
                    voos.add(voo);
                    rotas.add(this.vooParaRotaDto(voos, dto));
                });

        return rotas;
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
