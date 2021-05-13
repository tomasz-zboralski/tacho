package com.crud.tacho.mapper;

import com.crud.tacho.domain.Entry;
import com.crud.tacho.domain.EntryDto;
import com.crud.tacho.exception.AssignmentNotFoundException;
import com.crud.tacho.exception.EntryNotFoundException;
import com.crud.tacho.repository.AssignmentRepository;
import com.crud.tacho.service.AssignmentService;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EntryMapper {

    private final AssignmentService assignmentService;

    public EntryDto mapToEntryDto(Entry entry) {
        return new EntryDto(
                entry.getEntryId(),
                entry.getType(),
                entry.getStartTime(),
                entry.getEndTime(),
                entry.getDuration(),
                entry.getAssignment().getAssignmentId()
        );
    }

    public Entry mapToEntry(EntryDto entryDto) throws AssignmentNotFoundException {
        return new Entry(
                entryDto.getEntryId(),
                entryDto.getType(),
                entryDto.getStartTime(),
                entryDto.getEndTime(),
                entryDto.getDuration(),
                assignmentService.getAssignmentById(entryDto.getAssignmentId())
        );
    }

    public List<Entry> mapToEntryList(List<EntryDto> entryDtoList) {
        return entryDtoList.stream()
                .map(entryDto -> {
                    try {
                        return mapToEntry(entryDto);
                    } catch (AssignmentNotFoundException e) {
                        return new Entry();
                    }
                })
                .collect(Collectors.toList());
    }

    public List<EntryDto> mapToEntryDtoList(List<Entry> entryList) {
        return entryList.stream()
                .map(this::mapToEntryDto)
                .collect(Collectors.toList());
    }
}
