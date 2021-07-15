package com.Yobulma.Yobulma.Service.Abonne;

import com.Yobulma.Yobulma.Entity.Abonne.Abonne;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface AbonneService {

    //enregistrer abonne
   public  Abonne enregistrerAbonne(Abonne abonne);
   //recuperer la liste des abonnes
    public List<Abonne> getAllAbonne();
    //Suprimer un abonne
    public void supprimerAbonne(Long id );

    public Optional<Abonne> findAbonneById(Long id);

    Abonne findByEmail(String email) throws Exception;


}
