package com.Yobulma.Yobulma.Entity.Abonne;

import com.Yobulma.Yobulma.Entity.Besoin.Besoin;
import com.Yobulma.Yobulma.Entity.PaiementAbonnement.PaiementAbonnement;
import com.Yobulma.Yobulma.Entity.User.User;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Collection;
@Entity

public class Abonne extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String Zone;
    private String adresse;
    private String profile;
    @Column(nullable = false,unique = true)
    private String email;
    private String telephone;
    private String password;
    // public String role;
    boolean isAbonnementDone;

    public int getNBR_MAX_BESOIN() {
        return NBR_MAX_BESOIN;
    }

    public void setNBR_MAX_BESOIN(int NBR_MAX_BESOIN) {
        this.NBR_MAX_BESOIN = NBR_MAX_BESOIN;
    }

    private int NBR_MAX_BESOIN;

    //liaison avec la table besoin
    @OneToMany( mappedBy = "abonne", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    private Collection<Besoin> besoins;
    //liaison avec la table paiement Abonnement;
   @OneToMany(mappedBy = "abonnePaiement",cascade = CascadeType.ALL)
   //@JsonManagedReference
   @JsonManagedReference
   private Collection<PaiementAbonnement> paiementAbonnements ;

    @Override
    public String toString() {
        return "Abonne{" +
                "id=" + id +
                ", nomabonne='" + nom + '\'' +
                ", prenomabonne='" + prenom + '\'' +
                ", Zoneabonne='" + Zone + '\'' +
                ", adresseabonne='" + adresse + '\'' +
                ", profile='" + profile + '\'' +
                ", emailabonne='" + email + '\'' +
                ", telephoneabonne='" + telephone + '\'' +
                ", passwordabonne='" + password + '\'' +
                ", isAbonnementDone=" + isAbonnementDone +
                ", besoin=" + besoins +
                '}';
    }



    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
   public Collection<Besoin> getBesoins() {
        return besoins;
    }

    public void setBesoins(Collection<Besoin> besoin) {
        this.besoins = besoin;
    }
   public Collection<PaiementAbonnement> getPaiementAbonnements() {
        return paiementAbonnements;
    }

    public void setPaiementAbonnements(Collection<PaiementAbonnement> paiementAbonnements) {
        this.paiementAbonnements = paiementAbonnements;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long idabonne) {
        this.id = idabonne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nomabonne) {
        this.nom = nomabonne;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenomabonne) {
        this.prenom = prenomabonne;
    }

    public String getZoneabonne() {
        return Zone;
    }

    public void setZoneabonne(String zoneabonne) {
        Zone = zoneabonne;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresseabonne) {
        this.adresse = adresseabonne;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String emailabonne) {
        this.email = emailabonne;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephoneabonne) {
        this.telephone = telephoneabonne;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwordabonne) {
        this.password = passwordabonne;
    }



    public boolean isAbonnementDone() {
        return isAbonnementDone;
    }

    public void setAbonnementDone(boolean abonnementDone) {
        isAbonnementDone = abonnementDone;
    }

}
