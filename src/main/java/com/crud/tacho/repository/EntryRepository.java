package com.crud.tacho.repository;

import com.crud.tacho.domain.Entry;
import com.crud.tacho.domain.EntryType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
//@Transactional
public interface EntryRepository extends CrudRepository<Entry, Long> {

    @Override
    List<Entry> findAll();

    List<Entry> findAllByType(EntryType type);
    //List<Entry> findAllByType(En);

    List<Entry> findAllByAssignment_AssignmentId(Long id);

    @Override
    Optional<Entry> findById(Long id);

    @Override
    Entry save(Entry entry);

    @Override
    void deleteById(Long id);
}
