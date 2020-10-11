package com.weimer.barManager.repository;

import com.weimer.barManager.jpa.BarEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BarRepository extends CrudRepository<BarEntity, String> {
    Optional<BarEntity> findByCuit(int cuit);
}
