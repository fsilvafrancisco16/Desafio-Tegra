package com.ffsilva.api.model.auxiliar;

import com.ffsilva.api.model.entity.Aeroporto;
import java.util.Comparator;
import org.springframework.stereotype.Service;

/**
 *
 * @author francisco
 */
@Service
public class AeroportosCidadeSort implements Comparator<Aeroporto> {

    @Override
    public int compare(Aeroporto a1, Aeroporto a2) {
        return a1.getCidade().compareToIgnoreCase(a2.getCidade());
    }
}
