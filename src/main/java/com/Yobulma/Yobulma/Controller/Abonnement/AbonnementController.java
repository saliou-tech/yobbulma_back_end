package com.Yobulma.Yobulma.Controller.Abonnement;


import com.Yobulma.Yobulma.Dto.Abonnement.AbonnementDto;
import com.Yobulma.Yobulma.Entity.Abonnement.Abonnement;
import com.Yobulma.Yobulma.Entity.Livreur.Livreur;
import com.Yobulma.Yobulma.Repository.Abonnement.AbonnementRepository;
import com.Yobulma.Yobulma.Service.Abonnement.AbonnementService;
import com.Yobulma.Yobulma.Service.Paydunya.PaydunyaConfiguration;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/yobulma")
public class AbonnementController {

    @Autowired
    AbonnementRepository abonnementRepository;
    @Autowired
    AbonnementService abonnementService;
    @Autowired
    PaydunyaConfiguration paydunyaConfiguration;


    @PostMapping("/addAbonnement")
    public String  enregisterAbonnement(@RequestBody Abonnement abonnement){
        System.out.println(abonnement.toString());
        AbonnementDto abonnementDto = new AbonnementDto();
        BeanUtils.copyProperties(abonnement,abonnementDto);
        abonnementService.saveAbonnement(abonnementDto);
        return "abonnement enregistre avec succes ";

    }
    @PostMapping("/paiement")
    public String  PaiementParPaydunya(){
      return paydunyaConfiguration.paydunyaIntegration();


    }

    @GetMapping("abonnement/all")
    List<Abonnement> getAllLivraison(){
        return abonnementRepository.findAll();
    }

    @GetMapping("/abonnement/{id}")
    public Optional<Abonnement> getAbonnementById(@PathVariable Long id){
        return abonnementRepository.findById(id);
    }
    @DeleteMapping("/deleteabonnement/{id}")
    public String  supprimerLivreur(@ PathVariable  Long id){
        String message = null;
        Optional<Abonnement> abonnement = abonnementRepository.findById(id);
        if(!abonnement.isPresent()){
            message ="cet abonnement n'existe pas dans la base ";
            return message;
        }

        try{
            abonnementRepository.deleteById(id);
            message = "abonnement  delete successfuly";
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return  message;
    }
}
