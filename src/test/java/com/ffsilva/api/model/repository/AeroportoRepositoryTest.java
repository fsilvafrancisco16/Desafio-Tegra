package com.ffsilva.api.model.repository;

import com.ffsilva.api.model.entity.Aeroporto;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author francisco
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AeroportoRepositoryTest {

    @Autowired
    private AeroportoRepository aeroportoRepository;

    @Test
    public void testFindAll() {
        List<Aeroporto> aeroportos = this.aeroportoRepository.findAll();

        assertTrue(!aeroportos.isEmpty());
    }

    @Test
    public void testFindByAeroportoExistente() {
        Optional<Aeroporto> optional = this.aeroportoRepository.findByAeroporto("bSb");

        assertTrue(optional.isPresent());
    }

    @Test
    public void testFindByAeroportoNaoExitente() {
        Optional<Aeroporto> optional = this.aeroportoRepository.findByAeroporto("OOO");

        assertTrue(!optional.isPresent());
    }
}
