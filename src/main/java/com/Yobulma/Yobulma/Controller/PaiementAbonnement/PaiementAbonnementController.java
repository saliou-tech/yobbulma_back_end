package com.Yobulma.Yobulma.Controller.PaiementAbonnement;

import com.Yobulma.Yobulma.Entity.Livreur.Livreur;
import com.Yobulma.Yobulma.Entity.PaiementAbonnement.PaiementAbonnement;
import com.Yobulma.Yobulma.Repository.PaiementAbonnement.PaiementAbonnementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/yobulma")
public class PaiementAbonnementController {
    @Autowired
    PaiementAbonnementRepository paiementAbonnementRepository;

    @PostMapping("/addpaiementAbonnement")
    public PaiementAbonnement enregisterPaiementAbonnement(@RequestBody PaiementAbonnement paiementAbonnement){
        System.out.println(paiementAbonnement.toString());

         return  paiementAbonnementRepository.save(paiementAbonnement);

    }
    @GetMapping("/allpaiementabonnement")
    public List<PaiementAbonnement> getAllPaiementAbonnement(){
        return paiementAbonnementRepository.findAll();

    }
    @GetMapping("paiementabonnement/{id}")
    public Optional<PaiementAbonnement> findPaiementAbonnementById(@PathVariable  Long id){
        return  paiementAbonnementRepository.findById(id);

    }

    @DeleteMapping("/deletepaiementabonnement/{id}")
    public String  supprimerPaiementAbonnemen(@ PathVariable  Long id){
        String message = null;
        Optional<PaiementAbonnement> paiementAbonnement = paiementAbonnementRepository.findById(id);
        if(!paiementAbonnement.isPresent()){
            message ="cet abonnement n'existe pas encore ";
            return message;
        }

        try{
            paiementAbonnementRepository.deleteById(id);
            message = "abonnement suppre";
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return  message;
    }

}
