package com.Yobulma.Yobulma.Repository;

import com.Yobulma.Yobulma.Entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {

    Optional<Utilisateur> findByNom(String nom);
    Utilisateur  findByEmail(String email);

}
