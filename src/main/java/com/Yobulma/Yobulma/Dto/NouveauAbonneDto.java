package com.Yobulma.Yobulma.Dto;

import com.Yobulma.Yobulma.Entity.Abonne.Abonne;
import com.Yobulma.Yobulma.Entity.Abonnement.Abonnement;

public class NouveauAbonneDto {
    private Abonne abonne;
    private Abonnement abonnement;


    public Abonne getAbonne() {
        return abonne;
    }

    public void setAbonne(Abonne abonne) {
        this.abonne = abonne;
    }

    public Abonnement getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(Abonnement abonnement) {
        this.abonnement = abonnement;
    }

}


