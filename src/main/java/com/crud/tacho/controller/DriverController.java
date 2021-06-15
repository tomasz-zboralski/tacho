package com.crud.tacho.controller;

import com.crud.tacho.domain.Driver;
import com.crud.tacho.domain.DriverDto;
import com.crud.tacho.exception.AssignmentNotFoundException;
import com.crud.tacho.exception.DriverNotFoundException;
import com.crud.tacho.mapper.DriverMapper;
import com.crud.tacho.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class DriverController {

    private final DriverService driverService;
    private final DriverMapper driverMapper;

    @PostMapping(value = "/drivers", consumes = APPLICATION_JSON_VALUE)
    public DriverDto createDriver(@RequestBody DriverDto driverDto) {
        Driver driver = driverMapper.mapToDriver(driverDto);
        return driverMapper.mapToDriverDto(driverService.createDriver(driver));
    }

    @GetMapping(value="/drivers")
    public Set<DriverDto> getDrivers() {
        return driverMapper.mapToDriverDtoSet(driverService.getDrivers());
    }

    @GetMapping(value = "drivers/{driverId}")
    public DriverDto getDriver(@PathVariable Long driverId) {
        return driverMapper.mapToDriverDto(driverService.getDriverById(driverId));
    }

    @DeleteMapping(value = "drivers/{driverId}")
    public void deleteDriver(@PathVariable Long driverId) {
        driverService.deleteDriver(driverId);
    }

    @PutMapping(value = "/drivers")
    public DriverDto updateDriver(@RequestBody DriverDto driverDto) {
        Driver driver = driverMapper.mapToDriver(driverDto);
        return driverMapper.mapToDriverDto(driverService.createDriver(driver));
    }
}
