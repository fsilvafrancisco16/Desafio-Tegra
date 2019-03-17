package com.ffsilva.api.model.util;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author francisco
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TimeUtilTest {

    @Autowired
    private TimeUtil timeUtil;

    @Test
    public void testToLocalTime() {
        LocalTime time = this.timeUtil.toLocalTime("23:23");
        Assert.assertNotNull(time);
    }

    @Test
    public void testToLocalDate() {
        LocalDate date = this.timeUtil.toLocalDate("2019-03-15");
        Assert.assertNotNull(date);
    }

    @Test
    public void testIsValid() {
        boolean test = this.timeUtil.isValid("2019-03-15");

        assertTrue(test);
    }
}
