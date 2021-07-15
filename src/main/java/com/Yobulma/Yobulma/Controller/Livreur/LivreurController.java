package com.Yobulma.Yobulma.Controller.Livreur;

import com.Yobulma.Yobulma.Entity.Abonne.Abonne;
import com.Yobulma.Yobulma.Entity.Besoin.Besoin;
import com.Yobulma.Yobulma.Entity.Livraison.Livraison;
import com.Yobulma.Yobulma.Entity.Livreur.Livreur;
import com.Yobulma.Yobulma.Repository.Livreur.LivreurRepository;
import com.Yobulma.Yobulma.Service.Livreur.LivreurService;
import com.Yobulma.Yobulma.Twilio.SmsRequest;
import com.Yobulma.Yobulma.Twilio.SmsSenderTwilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/yobulma")
public class LivreurController {
    @Autowired
    private final SmsSenderTwilio smsSenderTwilio;
@Autowired
private LivreurRepository livreurRepository;
    @Autowired
    LivreurService livreurService;


    public LivreurController(SmsSenderTwilio smsSenderTwilio) {
        this.smsSenderTwilio = smsSenderTwilio;
    }

  /*  @PostMapping("/enregistrerLivreur"  )
    public ResponseEntity createLivreur(@RequestBody Livreur livreur){
        String message = "   Merci d'avoir choisir Yobulma  Votre platforme de livraison connectez vous et vous aurez la liste des livraisons à effectuer ";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        try {
            livreur.setPassword(encoder.encode(livreur.getPassword()));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        SmsRequest sms = new SmsRequest(
                livreur.getTelephone(), message
        );
        smsSenderTwilio.sendSms(sms);



        Livreur userExists = livreurRepository.findByEmail(livreur.getEmail());
        if (userExists != null) {
            throw new BadCredentialsException("User with username: " + livreur.getEmail() + " already exists");
        }
        livreurService.enregistrerLivreur(livreur);
        Map<Object, Object> model = new HashMap<>();
        model.put("message", "livreur registered successfully");
        return ResponseEntity.ok(model);
    }*/


    @GetMapping("/allLivreur")
    public List<Livreur> getAllLivreur(){
        return livreurRepository.findAll();

    }
    @GetMapping("/getLivreurBybesoin/{idbesoin}")
    public Livreur getAbonneByBesoin(@PathVariable Long idbesoin){

        List<Livreur> livreurs = livreurRepository.findAll();
        if(idbesoin != null && livreurs != null && livreurs.size() > 0) {
            for(Livreur user : livreurs) {
                if(user.getLivraisons()!=null&&user.getLivraisons().size()>0){
                    for(Livraison livraison :user.getLivraisons()) {
                        if(livraison.getBesoin()!=null){
                            if(livraison.getBesoin().getId().equals(idbesoin)){
                                return user;
                            }
                        }
                    }

                }

            }

        }
return null;

    }

    @GetMapping("livreur/{id}")
    public Optional<Livreur> findLivreurById(@PathVariable  Long id){
        return  livreurRepository.findById(id);

    }
    @DeleteMapping("/deletelivreur/{id}")
    public String  supprimerLivreur(@ PathVariable  Long id){
        String message = null;
        Optional<Livreur> livreur = livreurRepository.findById(id);
        if(!livreur.isPresent()){
            message ="l'utilisateur n'existe pas dans la base de donnes ";
            return message;
        }

        try{
            livreurRepository.deleteById(id);
            message = "user delete successfuly";
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return  message;
    }







}
