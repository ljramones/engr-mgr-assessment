package com.spothero.engmgr.db;

import com.spothero.engmgr.db.model.WorkedHours;
import com.spothero.engmgr.db.repository.WorkedHoursRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@DataJpaTest
@DirtiesContext
public class WorkedHoursRepositoryTest extends DatabaseTest {

    @Autowired
    private WorkedHoursRepository workedHoursRepository;

    @Test
    void getWorkedHoursForUserAndCreate() {

        try {
            var date1 = new SimpleDateFormat("yyyyMMdd").parse("20220520");
            var date2 = new SimpleDateFormat("yyyyMMdd").parse("20220521");
            var date3 = new SimpleDateFormat("yyyyMMdd").parse("20220522");
            var date4 = new SimpleDateFormat("yyyyMMdd").parse("20220523");
            var date5 = new SimpleDateFormat("yyyyMMdd").parse("20220524");

            var workedHours1 = new WorkedHours();
            workedHours1.setUserId(1);
            workedHours1.setDate(date1);
            workedHours1.setHours(2);
            workedHoursRepository.save(workedHours1);

            var workedHours2 = new WorkedHours();
            workedHours2.setUserId(1);
            workedHours2.setDate(date2);
            workedHours2.setHours(3);
            workedHoursRepository.save(workedHours2);

            var workedHours3 = new WorkedHours();
            workedHours3.setUserId(1);
            workedHours3.setDate(date3);
            workedHours3.setHours(4.5f);
            workedHoursRepository.save(workedHours3);

            var workedHours4 = new WorkedHours();
            workedHours4.setUserId(1);
            workedHours4.setDate(date4);
            workedHours4.setHours(1.7f);
            workedHoursRepository.save(workedHours4);

            var workedHours5 = new WorkedHours();
            workedHours5.setUserId(1);
            workedHours5.setDate(date5);
            workedHours5.setHours(2.4f);
            workedHoursRepository.save(workedHours5);

            List<WorkedHours> workedHoursList = workedHoursRepository.findByUserId(1);
            Assertions.assertEquals(5, workedHoursList.size());


        } catch (Exception e) {
            Assertions.fail();
        }
    }


}
