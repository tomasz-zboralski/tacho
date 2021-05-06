package com.crud.tacho.repository;

import com.crud.tacho.domain.Assignment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface AssignmentRepository extends CrudRepository<Assignment, Long> {

    @Override
    List<Assignment> findAll();

    @Override
    Optional<Assignment> findById(Long id);

    @Override
    Assignment save(Assignment assignment);

    @Override
    void deleteById(Long id);
}
