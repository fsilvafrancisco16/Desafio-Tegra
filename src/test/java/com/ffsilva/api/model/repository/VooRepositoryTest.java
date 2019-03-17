package com.ffsilva.api.model.repository;

import com.ffsilva.api.model.entity.Voo;
import com.ffsilva.api.model.util.impl.TimeUtilImpl;
import java.util.List;
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
public class VooRepositoryTest {

    @Autowired
    private TimeUtilImpl timeUtil;

    @Autowired
    private VooRepository vooRepository;

    @Test
    public void testFindAllVoosOperadoraUberair() {
        List<Voo> voos = this.vooRepository.findAllVoosOperadoraUberair();

        assertTrue(!voos.isEmpty());
    }

    @Test
    public void testFindAllVoosOperadora99Planes() {
        List<Voo> voos = this.vooRepository.findAllVoosOperadora99Planes();

        assertTrue(!voos.isEmpty());
    }

    @Test
    public void testFindAll() {
        List<Voo> voos = this.vooRepository.findAll();

        assertTrue(!voos.isEmpty());
    }

    @Test
    public void testFindByVoosDiretos() {
        List<Voo> voos = this.vooRepository.findByVoosDiretos("BSB", "VCP", this.timeUtil.toLocalDate("2019-02-10"));

        assertTrue(!voos.isEmpty());
    }
}
