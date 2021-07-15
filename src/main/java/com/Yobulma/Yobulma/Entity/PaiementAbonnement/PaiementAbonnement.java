package com.Yobulma.Yobulma.Entity.PaiementAbonnement;

import com.Yobulma.Yobulma.Entity.Abonne.Abonne;
import com.Yobulma.Yobulma.Entity.Abonnement.Abonnement;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

@Entity

public class PaiementAbonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dateDebut;
    private String dateFin;
    private String ModDePaiment;

    public String getModDePaiment() {
        return ModDePaiment;
    }

    public void setModDePaiment(String modDePaiment) {
        ModDePaiment = modDePaiment;
    }

    //liaison avec la table  abonne
@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
@JoinColumn(nullable = false, updatable = false)
@JsonBackReference
private Abonne abonnePaiement;
//liaison avec la table paiement abonnement
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(nullable = false, updatable = false)

  private Abonnement abonnement;




@JsonIgnore
    public Abonnement getAbonnement() {
        return abonnement;
    }

    public Abonne getAbonnePaiement() {
        return abonnePaiement;
    }

    public void setAbonnePaiement(Abonne abonne) {
        this.abonnePaiement = abonne;
    }
@JsonIgnore
    public void setAbonnement(Abonnement abonnement) {
        this.abonnement = abonnement;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long idPabonnement) {
        this.id = idPabonnement;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }
}
