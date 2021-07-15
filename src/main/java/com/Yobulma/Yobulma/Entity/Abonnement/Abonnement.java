package com.Yobulma.Yobulma.Entity.Abonnement;

import com.Yobulma.Yobulma.Entity.PaiementAbonnement.PaiementAbonnement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;
@Entity

public class Abonnement {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String intitule;
    private double prix;

   @OneToMany(mappedBy = "abonnement",cascade = {CascadeType.MERGE,CascadeType.PERSIST})

   private Collection<PaiementAbonnement> paiementAbonnements ;




@JsonIgnore
   public Collection<PaiementAbonnement> getPaiementAbonnements() {
        return paiementAbonnements;
    }
@JsonIgnore
    public void setPaiementAbonnements(Collection<PaiementAbonnement> paiementAbonnements) {
        this.paiementAbonnements = paiementAbonnements;
    }



    public Abonnement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long idAbonnement) {
        this.id = idAbonnement;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
