package com.crud.tacho.service;

import com.crud.tacho.domain.Infringement;
import com.crud.tacho.repository.InfringementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InfringementService {

    private final InfringementRepository infringementRepository;


    public List<Infringement> getAllValidInfringements() {
        return infringementRepository.retrieveValidInfringement();
    }

    public void deleteAllNotValidInfringements() {
        List<Infringement> infringements = infringementRepository.retrieveNotValidInfringements();
        infringements.stream()
                .mapToLong(Infringement::getInfringementId)
                .forEach(infringementRepository::deleteById);
    }
}
