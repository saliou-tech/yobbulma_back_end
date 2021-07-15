package com.Yobulma.Yobulma.Entity.Besoin;

import com.Yobulma.Yobulma.Entity.Abonne.Abonne;

import com.Yobulma.Yobulma.Entity.Livraison.Livraison;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

@Entity

public class Besoin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private boolean statut;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE )
    @JoinColumn(nullable = false, updatable = false)
    @JsonBackReference
    private Abonne abonne;
/*
    @OneToOne(mappedBy = "besoin")
    private Livraison livraison;*/




    public Besoin() {

    }



    public Long getId() {
        return id;
    }

    public void setId(Long idbesoin) {
        this.id = idbesoin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }


 /*   public Livraison getLivraison() {
        return livraison;
    }

    public void setLivraison(Livraison livraison) {
        this.livraison = livraison;
    }*/

    public Abonne getAbonne() {
        return abonne;
    }

    public void setAbonne(Abonne abonne) {
        this.abonne = abonne;
    }

    @Override
    public String toString() {
        return "Besoin{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", abonne=" + abonne +

                ", statut=" + statut +
                '}';
    }


}
