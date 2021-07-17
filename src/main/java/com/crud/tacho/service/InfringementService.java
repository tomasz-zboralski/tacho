package com.crud.tacho.service;

import com.crud.tacho.domain.Assignment;
import com.crud.tacho.domain.Infringement;
import com.crud.tacho.repository.InfringementRepository;
import com.crud.tacho.service.infringement_calc.InfringementCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class InfringementService {

    private final InfringementRepository infringementRepository;
    private final InfringementCalculator infringementCalculator;

    public void isDailyDrivingTimeExceeded(Assignment assignment) {
        Optional<Infringement> infringementOptional = infringementCalculator.isDailyDrivingTimeExceeded(assignment);
        infringementOptional.ifPresent(infringementRepository::save);

    }

    public void isDrivingWithoutBreakInfringement(Assignment assignment) {
        Optional<Infringement> infringementOptional = infringementCalculator.isDrivingWithoutBreakInfringement(assignment);
        infringementOptional.ifPresent(infringementRepository::save);

    }

    public List<Infringement> getAllValidInfringements() {
        return infringementRepository.retrieveValidInfringements();
    }

    public void deleteAllNotValidInfringements() {
        List<Infringement> infringements = infringementRepository.retrieveNotValidInfringements();
        infringements.stream()
                .mapToLong(Infringement::getInfringementId)
                .forEach(infringementRepository::deleteById);
    }

    public List<Infringement> getAllInfringements() {
        return infringementRepository.findAll();
    }
}
