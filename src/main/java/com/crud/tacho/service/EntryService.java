package com.crud.tacho.service;

import com.crud.tacho.domain.Entry;
import com.crud.tacho.domain.EntryDto;
import com.crud.tacho.exception.AssignmentNotFoundException;
import com.crud.tacho.exception.EntryNotFoundException;
import com.crud.tacho.mapper.EntryMapper;
import com.crud.tacho.repository.EntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EntryService {

    private final EntryRepository entryRepository;
    //private final EntryMapper entryMapper;

    public List<Entry> getEntries() {
        return entryRepository.findAll();
    }
    public List<Entry> getEntriesByType(String type) {
        return entryRepository.findAllByType(type);
    }

    public List<Entry> getEntriesByAssignmentId(Long assignmentId) {
        return entryRepository.findAllByAssignment_AssignmentId(assignmentId);
    }

    public Entry getEntryById(Long id) throws EntryNotFoundException {
        return entryRepository.findById(id).orElseThrow(EntryNotFoundException::new);
    }

    public Entry createEntry(Entry entry) {
        return entryRepository.save(entry);
    }

    public void deleteEntry(Long id) {
        entryRepository.deleteById(id);
    }

}
