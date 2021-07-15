package com.Yobulma.Yobulma.Repository.Besoin;

import com.Yobulma.Yobulma.Entity.Besoin.Besoin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BesoinRepository extends JpaRepository<Besoin,Long> {

    List<Besoin> findByAbonneId(Long id);
    Optional<Besoin> findById(Long id);
}
