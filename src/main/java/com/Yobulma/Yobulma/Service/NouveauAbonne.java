package com.Yobulma.Yobulma.Service;

import com.Yobulma.Yobulma.Dto.NouveauAbonneDto;
import com.Yobulma.Yobulma.Entity.Abonne.Abonne;
import com.Yobulma.Yobulma.Entity.Abonnement.Abonnement;
import com.Yobulma.Yobulma.Repository.Abonne.AbonneRepository;
import com.Yobulma.Yobulma.Repository.Abonnement.AbonnementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class NouveauAbonne {

    private final AbonneRepository abonneRepository;

    private final AbonnementRepository abonnementRepository;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;


    public NouveauAbonne(AbonneRepository abonneRepository, AbonnementRepository abonnementRepository) {
        this.abonneRepository = abonneRepository;
        this.abonnementRepository = abonnementRepository;
    }


    public String SaveNouveauAbonne(NouveauAbonneDto nouveauAbonneDto){
        Abonne abonne ;
        Abonnement abonnement ;
      abonne=  nouveauAbonneDto.getAbonne();
      abonnement = nouveauAbonneDto.getAbonnement();
      abonne.setPassword(bCryptPasswordEncoder.encode(abonne.getPassword()));
      abonneRepository.save(abonne);
      abonnementRepository.save(abonnement);
      return  "un nouveau Abonne vient d'etre enregistre";



    }
}
