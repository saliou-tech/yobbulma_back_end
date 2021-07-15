
package com.Yobulma.Yobulma.Entity.Livraison;

import com.Yobulma.Yobulma.Entity.Abonne.Abonne;
import com.Yobulma.Yobulma.Entity.Besoin.Besoin;
import com.Yobulma.Yobulma.Entity.Livreur.Livreur;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Livraison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
   @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName = "id")
    private Besoin besoin;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(nullable = false, updatable = false)
    @JsonBackReference
    private Livreur livreur;

   public Besoin getBesoin() {
        return besoin;
    }

    public void setBesoin(Besoin besoin) {
        this.besoin = besoin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Livreur getLivreur() {
        return livreur;
    }

    public void setLivreur(Livreur livreur) {
        this.livreur = livreur;
    }

    /*    @OneToOne(fetch = FetchType.LAZY,
                cascade =  CascadeType.ALL,
                mappedBy = "livraison")
        //@MapsId
        private Besoin besoin;*/


    public Livraison() {
    }
/*    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, updatable = false)
    @JsonBackReference
    private Livreur livreur;*/




}

