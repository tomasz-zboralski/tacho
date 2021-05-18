package com.crud.tacho.repository;

import com.crud.tacho.domain.Infringement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface InfringementRepository extends CrudRepository<Infringement, Long> {

    @Override
    List<Infringement> findAll();

    @Override
    Optional<Infringement> findById(Long id);

    @Override
    Infringement save(Infringement infringement);

    @Override
    void deleteById(Long id);

    @Query
    List<Infringement> retrieveValidInfringements();

    @Query
    List<Infringement> retrieveNotValidInfringements();
}
