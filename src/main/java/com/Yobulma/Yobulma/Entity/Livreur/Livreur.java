package com.Yobulma.Yobulma.Entity.Livreur;

import com.Yobulma.Yobulma.Entity.Livraison.Livraison;
import com.Yobulma.Yobulma.Entity.User.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Livreur extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String Zone;
    private String adresse;
    private String profile = "livreur";

    private String email;
    private String telephone;
    private String password;
    // public String role;
    boolean isAbonnementDone;

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }



   /* @OneToMany(mappedBy = "livraison")
    @JsonManagedReference
    private Collection<Livraison> livraison ;*/
   @OneToMany(mappedBy = "livreur")
   @JsonManagedReference
   private Collection<Livraison> livraisons;

   public Collection<Livraison> getLivraisons() {
        return livraisons;
    }

    public void setLivraisons(Collection<Livraison> livraisons) {
        this.livraisons = livraisons;
    }

    public Livreur() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long idlivreur) {
        this.id = idlivreur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nomlivreur) {
        this.nom = nomlivreur;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenomlivreur) {
        this.prenom = prenomlivreur;
    }

    public String getZone() {
        return Zone;
    }

    public void setZone(String zone) {
        Zone = zone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresselivreur) {
        this.adresse = adresselivreur;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String emaillivreur) {
        this.email = emaillivreur;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephonelivreur) {
        this.telephone = telephonelivreur;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwordlivreur) {
        this.password = passwordlivreur;
    }

    public boolean isAbonnementDone() {
        return isAbonnementDone;
    }

    public void setAbonnementDone(boolean abonnementDone) {
        isAbonnementDone = abonnementDone;
    }
}
