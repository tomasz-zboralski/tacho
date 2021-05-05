package com.crud.tacho.repository;

import com.crud.tacho.domain.Duty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface DutyRepository extends CrudRepository<Duty, Long> {

    @Override
    List<Duty> findAll();

    @Override
    Optional<Duty> findById(Long id);

    @Override
    Duty save(Duty duty);

    @Override
    void deleteById(Long id);
}
