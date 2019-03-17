package com.ffsilva.api.model.service;

import com.ffsilva.api.model.entity.Aeroporto;
import com.ffsilva.api.model.response.Response;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author francisco
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AeroportoServiceTest {

    @Autowired
    private AeroportoService aeroportoService;

    @Test
    public void testFindAll() {
        ResponseEntity<Response<List<Aeroporto>>> responseEntity = this.aeroportoService.findAll();

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
