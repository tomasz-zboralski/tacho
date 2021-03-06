package com.crud.tacho.repository;

import com.crud.tacho.domain.Driver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Transactional
public interface DriverRepository extends CrudRepository<Driver, Long> {

    @Override
    Set<Driver> findAll();

    @Override
    Optional<Driver> findById(Long id);

    @Override
    Driver save(Driver driver);

    @Override
    void deleteById(Long id);

}
