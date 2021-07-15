package com.Yobulma.Yobulma.Repository.Abonne;

import com.Yobulma.Yobulma.Entity.Abonne.Abonne;
import com.Yobulma.Yobulma.Entity.Livreur.Livreur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;



public interface AbonneRepository  extends JpaRepository<Abonne,Long> {
    Abonne findByEmail(String email);



}
