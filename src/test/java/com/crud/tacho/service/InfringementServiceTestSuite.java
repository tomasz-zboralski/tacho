package com.crud.tacho.service;

import com.crud.tacho.domain.Assignment;
import com.crud.tacho.domain.Infringement;
import com.crud.tacho.repository.InfringementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InfringementServiceTestSuite {

    private static final Infringement INFRINGEMENT = new Infringement();
    private static final Assignment ASSIGNMENT = new Assignment(
            LocalDateTime.now().minusHours(14), LocalDateTime.now(), null, null
    );

    @Mock
    InfringementRepository infringementRepository;

    @InjectMocks
    InfringementService infringementService;


    @Test
    void calculateInfringement() {

        //Given & When
        when(infringementRepository.save(any(Infringement.class))).thenReturn(INFRINGEMENT);

        infringementService.calculateInfringement(ASSIGNMENT);

        //Then
        verify(infringementRepository, times(1)).save(any(Infringement.class));

    }

    @Test
    void getAllValidInfringements() {

        //Given & When
        List<Infringement> infringementList = Collections.singletonList(INFRINGEMENT);
        when(infringementRepository.retrieveValidInfringements()).thenReturn(infringementList);

        List<Infringement> actualInfringementList = infringementService.getAllValidInfringements();

        //Then
        assertEquals(infringementList, actualInfringementList);

    }

    @Test
    void deleteAllNotValidInfringements() {

        //Given & When
        INFRINGEMENT.setInfringementId(1L);
        List<Infringement> infringementList = Collections.singletonList(INFRINGEMENT);

        when(infringementRepository.retrieveNotValidInfringements()).thenReturn(infringementList);
        doNothing().when(infringementRepository).deleteById(anyLong());

        infringementService.deleteAllNotValidInfringements();

        //Then
        verify(infringementRepository, times(1)).retrieveNotValidInfringements();
        verify(infringementRepository, times(1)).deleteById(anyLong());

    }
}