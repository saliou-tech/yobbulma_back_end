package com.Yobulma.Yobulma.Controller.Livraison;


import com.Yobulma.Yobulma.Entity.Livraison.Livraison;
import com.Yobulma.Yobulma.Repository.Livraison.LivraisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/yobulma")
public class LivraisonController {
    @Autowired

   private LivraisonRepository livraisonRepository;

    @PostMapping("/addlivraison")
    public Livraison enregisterBesoin(@RequestBody Livraison livraison){
        System.out.println(livraison);

        return   livraisonRepository.save(livraison);

    }
    @GetMapping("/livraisonbylivreur/{idLivreur}")
    public List<Livraison> getLivraisonByLivreur(@PathVariable Long idLivreur){
        return livraisonRepository.findByLivreurId(idLivreur);
    }

    @GetMapping("/livraison/{id}")
    public Optional<Livraison> getLivraisonById(@PathVariable Long id){
        return livraisonRepository.findById(id);
    }

    @GetMapping("livraison/all")
    List<Livraison> getAllLivraison(){
        return livraisonRepository.findAll();
    }






}
