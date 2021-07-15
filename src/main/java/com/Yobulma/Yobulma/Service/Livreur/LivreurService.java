package com.Yobulma.Yobulma.Service.Livreur;

import com.Yobulma.Yobulma.Entity.Abonne.Abonne;
import com.Yobulma.Yobulma.Entity.Livreur.Livreur;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface  LivreurService {
    //enregistrer livreur
    public Livreur enregistrerLivreur(Livreur livreur);
    //recuperer la liste des abonnes
    public List<Livreur> getAllLivreur();
    //Suprimer un abonne
    public void supprimerLivreur(Livreur livreur);

    public Optional<Livreur> findLivreurById(Long id);
}
