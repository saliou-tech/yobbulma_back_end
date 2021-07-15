package com.Yobulma.Yobulma.Controller.Abonne;

import com.Yobulma.Yobulma.Entity.Abonne.Abonne;
import com.Yobulma.Yobulma.Entity.Besoin.Besoin;
import com.Yobulma.Yobulma.Repository.Abonne.AbonneRepository;
import com.Yobulma.Yobulma.Service.Abonne.AbonneService;
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

public class AbonneController {
    @Autowired
    private final SmsSenderTwilio smsSenderTwilio;
@Autowired
    private  AbonneService abonneService;
    @Autowired
    private AbonneRepository abonneRepository;

    public AbonneController(SmsSenderTwilio smsSenderTwilio) {
        this.smsSenderTwilio = smsSenderTwilio;
    }

    @PostMapping(value="/enregistrerAbonne" )

    public ResponseEntity Abonne(@RequestBody Abonne abonne) throws Exception {
        String message = "   Merci d'avoir choisir Yobulma  Votre platforme de livraison fiable Connectez vous vite et vous aurez votre livreur  ";
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        try {
            abonne.setPassword(encoder.encode(abonne.getPassword()));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if(abonne.getProfile() ==null){
            abonne.setProfile("abonne");
        }
        SmsRequest sms = new SmsRequest(
                abonne.getTelephone(), message
        );
        smsSenderTwilio.sendSms(sms);



            Abonne userExists = abonneRepository.findByEmail(abonne.getEmail());
            if (userExists != null) {
                throw new BadCredentialsException("User with username: " + abonne.getEmail() + " already exists");
            }
            abonneService.enregistrerAbonne(abonne);
            Map<Object, Object> model = new HashMap<>();
            model.put("message", "User registered successfully");
            return ResponseEntity.ok(model);


      //  return  abonneRepository.save(abonne);

    }
@GetMapping("/allAbonne")
    public List<Abonne> getAllAbonne(){
        return abonneRepository.findAll();


    }

   @GetMapping("/{id}")
   public Optional<Abonne> findAbonneById(@PathVariable  Long id){
        return  abonneRepository.findById(id);

   }

   @GetMapping("/getAbonneBybesoin/{idbesoin}")
   public Abonne getAbonneByBesoin(@PathVariable Long idbesoin){
       List<Abonne> abonnes = abonneRepository.findAll();
       if(idbesoin != null && abonnes != null && abonnes.size() > 0) {
           for(Abonne user : abonnes) {
               if(user.getBesoins()!=null&&user.getBesoins().size()>0){
                   for(Besoin besoin :user.getBesoins()) {

                       if(besoin.getId().equals(idbesoin)){

                           return user;
                       }

                   }
               }


           }
       }
       return null;
   }
@DeleteMapping("/delete/{id}")
    public String  supprimerAbonne(@ PathVariable  Long id){
        String message = null;
    Optional<Abonne> abonne = abonneRepository.findById(id);
    if(!abonne.isPresent()){
        message ="l'utilisateur n'existe pas dans la base de donnes ";
        return message;
    }

        try{
            abonneRepository.deleteById(id);
            message = "user delete successfuly";
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return  message;
    }




    }
