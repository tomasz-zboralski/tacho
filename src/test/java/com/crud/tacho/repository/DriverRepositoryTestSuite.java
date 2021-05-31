package com.crud.tacho.repository;

import com.crud.tacho.domain.Driver;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DriverRepositoryTestSuite {

    @Autowired
    DriverRepository driverRepository;

    @Test
    void testSaveDriver() {

        //Given
        Driver driver = new Driver("TestLTD", "TestName", "TestSurname");

        //When
        driverRepository.save(driver);
        Long id = driver.getDriverId();

        //Then
        assertTrue(driverRepository.findById(id).isPresent());

        //CleanUp
        driverRepository.deleteById(id);

    }

    @Test
    void testFindAllDrivers() {

        //Given
        Driver driver1 = new Driver(
                "TestLTD", "TestName", "TestSurname");
        Driver driver2 = new Driver(
                "TestLTD2", "TestName2", "TestSurname2");

        //When
        driverRepository.save(driver1);
        driverRepository.save(driver2);

        Long driver1Id = driver1.getDriverId();
        Long driver2Id = driver2.getDriverId();

        int records = driverRepository.findAll().size();
        //Then
        assertEquals(2, records);

        //CleanUp
        driverRepository.deleteById(driver1Id);
        driverRepository.deleteById(driver2Id);

    }

    @Test
    void testDeleteById() {

        //Given
        Driver driver = new Driver(
                "TestLTD", "TestName", "TestSurname");

        //When
        driverRepository.save(driver);
        Long id = driver.getDriverId();
        driverRepository.deleteById(id);

        //Then
        assertFalse(driverRepository.findById(id).isPresent());
    }

}
