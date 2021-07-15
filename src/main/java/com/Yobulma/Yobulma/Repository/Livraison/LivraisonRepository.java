package com.Yobulma.Yobulma.Repository.Livraison;

import com.Yobulma.Yobulma.Entity.Besoin.Besoin;
import com.Yobulma.Yobulma.Entity.Livraison.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivraisonRepository extends JpaRepository<Livraison,Long > {

    List<Livraison> findByLivreurId(Long id);
}
