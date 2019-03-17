package com.ffsilva.api.model.auxiliar;

import com.ffsilva.api.model.entity.Voo;
import java.util.Comparator;
import org.springframework.stereotype.Service;

/**
 *
 * @author francisco
 */
@Service
public class VoosDataSort implements Comparator<Voo> {

    @Override
    public int compare(Voo v1, Voo v2) {
        return v1.getData().compareTo(v2.getData());
    }
}
