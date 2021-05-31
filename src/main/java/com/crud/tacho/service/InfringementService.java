package com.crud.tacho.service;

import com.crud.tacho.domain.Assignment;
import com.crud.tacho.domain.Infringement;
import com.crud.tacho.repository.InfringementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@RequiredArgsConstructor
@Service
public class InfringementService {

    private final InfringementRepository infringementRepository;

    public void calculateInfringement(Assignment assignment) {
        if (assignment.getDuration().compareTo(Duration.ofHours(13)) > 0) {
            Infringement infringement = new Infringement(
                    "Daily work time exceeded",
                    assignment.getStartTime(),
                    assignment.getEndTime(),
                    assignment
            );
            infringementRepository.save(infringement);
        }

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
