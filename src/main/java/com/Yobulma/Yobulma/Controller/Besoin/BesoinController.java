package com.Yobulma.Yobulma.Controller.Besoin;

import com.Yobulma.Yobulma.Entity.Besoin.Besoin;
import com.Yobulma.Yobulma.Repository.Besoin.BesoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/yobulma")
public class BesoinController {
@Autowired
    private BesoinRepository besoinRepository;



@PostMapping("/addbesoin")
    public Besoin enregisterBesoin(@RequestBody Besoin besoin){
    System.out.println(besoin);

      return   besoinRepository.save(besoin);

    }
    @PutMapping("besoin/{id}")
    public Optional<Besoin> UpdateBesoin(@RequestBody Besoin newbesoin , @PathVariable Long id){
    return besoinRepository.findById(id).
            map(
            besoin->{
                besoin.setStatut(newbesoin.isStatut());
                return besoinRepository.save(besoin);
            }
    );

    }

    @GetMapping("/allbesoin")
    public List<Besoin> getAllBesoin(){
        return besoinRepository.findAll();

    }
    @GetMapping("/besoinbyabonne/{idAbonne}")
    public List<Besoin> getBesoinByAbonne(@PathVariable Long idAbonne){
    return besoinRepository.findByAbonneId(idAbonne);
    }
    @GetMapping("besoin/{id}")
    public Optional<Besoin> findAbonneById(@PathVariable  Long id){
        return  besoinRepository.findById(id);

    }
    @DeleteMapping("/delete/besoin/{id}")
    public String  supprimerAbonne(@ PathVariable  Long id){
        String message = null;
        Optional<Besoin> besoin = besoinRepository.findById(id);
        if(!besoin.isPresent()){
            message ="Ce besoin n'existe pas dans la base de donnes ";
            return message;
        }

        try{
            besoinRepository.deleteById(id);
            message = "user delete successfuly";
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return  message;
    }


}
