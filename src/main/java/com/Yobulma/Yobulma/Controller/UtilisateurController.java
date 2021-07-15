package com.Yobulma.Yobulma.Controller;


import com.Yobulma.Yobulma.Configuration.JwtTokenProvider;
import com.Yobulma.Yobulma.Dto.ClientsDto;
import com.Yobulma.Yobulma.Dto.NouveauAbonneDto;
import com.Yobulma.Yobulma.Dto.UserDto;
import com.Yobulma.Yobulma.Entity.Abonne.Abonne;
import com.Yobulma.Yobulma.Entity.Livreur.Livreur;
import com.Yobulma.Yobulma.Entity.User.User;
import com.Yobulma.Yobulma.Entity.Utilisateur;

import com.Yobulma.Yobulma.Repository.Abonne.AbonneRepository;
import com.Yobulma.Yobulma.Repository.Livreur.LivreurRepository;
import com.Yobulma.Yobulma.Repository.UtilisateurRepository;
import com.Yobulma.Yobulma.Service.CustomUserDetailService;
import com.Yobulma.Yobulma.Service.NouveauAbonne;
import com.Yobulma.Yobulma.Service.RoleService;
import com.Yobulma.Yobulma.Service.UtilisateurService;
import com.Yobulma.Yobulma.Twilio.SmsRequest;
import com.Yobulma.Yobulma.Twilio.SmsSenderTwilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/yobulma")
public class UtilisateurController {


@Autowired
    private final SmsSenderTwilio smsSenderTwilio;

   private SmsRequest smsRequest;
    @Autowired
    private  UtilisateurService clientService;
    @Autowired
    private UtilisateurRepository clientRepository;
    @Autowired
    private LivreurRepository livreurRepository;
    @Autowired
    private AbonneRepository abonneRepository;
    @Autowired
    CustomUserDetailService customUserDetailService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    NouveauAbonne nouveauAbonne;


   /* @Autowired
    CustomUserDetailsService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
*/

/*
    private RoleService roleService;
*/
 /*   @Autowired
    private RoleRepository roleRepository;*/

    public UtilisateurController(SmsSenderTwilio smsSenderTwilio) {
        this.smsSenderTwilio = smsSenderTwilio;
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Utilisateur user) {


      UserDto userExists = customUserDetailService.findClientByEmail(user.getEmail(),user.getProfile());
           System.out.println(userExists);
            if (userExists != null) {
                throw new BadCredentialsException("User with username: " + user.getEmail() + " already exists");
            }
            System.out.println("est ce que le methode est appele");
        customUserDetailService.saveUser(user,user.getProfile());
           System.out.println("on ne cei pas");


            Map<Object, Object> model = new HashMap<>();
            model.put("message", "User registered successfully");
            return ResponseEntity.ok(model);




    }


    @PostMapping("/enregistrerNouveauAbonne")
    public ResponseEntity enregistrerUnNouveauAbonne
            (@RequestBody NouveauAbonneDto nouveauAbonneDto) {
        System.out.println(nouveauAbonneDto.getAbonne());


        UserDto userExists = customUserDetailService.findClientByEmail(nouveauAbonneDto.getAbonne().getEmail(),nouveauAbonneDto.getAbonne().getProfile());
        System.out.println(userExists);
        if (userExists != null) {
            throw new BadCredentialsException("User with username: " + nouveauAbonneDto.getAbonne().getEmail() + " already exists");
        }
        System.out.println("est ce que le methode est appele");
       String message = nouveauAbonne.SaveNouveauAbonne(nouveauAbonneDto);
        System.out.println("on ne cei pas");
        Map<Object, Object> model = new HashMap<>();
        model.put("message", message);
        return ResponseEntity.ok(model);




    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Utilisateur data){
        UserDto userDto = new UserDto();
               userDto.setEmail(data.getEmail());
               userDto.setPassword(data.getPassword());
               userDto.setProfile(data.getProfile());
        try {

            String username = userDto.getEmail();
            //Client user =  users.findByUsername(username);
            UserDto user = customUserDetailService.findUserByHisEmail(username);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, userDto.getPassword()));
            String token = jwtTokenProvider.createToken(username);
            Map<Object, Object> model = new HashMap<>();
            model.put("currentUser", user);
            model.put("token", token);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied "+e);

        }

    }







    public Utilisateur createUser(@RequestBody Utilisateur client){
        System.out.println((client.toString()));
        System.out.println(client.getProfile());
        String message = "   Merci d'avoir choisir Yobulma  Votre platforme de livraison fiable Connectez vous vite et vous aurez votre livreur  ";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        try {
            client.setPassword(encoder.encode(client.getPassword()));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        if(client.getTelephone().isEmpty()){
            throw new RuntimeException("veuillez saisir votre numero de telephone");

        }
       if(client.getProfile()==null){
            client.setProfile("Abonne");
        }


           //System.out.println(client.getTelephone());
           SmsRequest sms = new SmsRequest(
                  client.getTelephone(), message
                   );

          // smsRequest.setPhoneNumber(client.getTelephone());

          // smsRequest.setMessage(message);
     /*      System.out.println(smsRequest.toString());
           System.out.println( smsRequest.getPhoneNumber() );*/
           System.out.println( sms);
           smsSenderTwilio.sendSms(sms);

           return clientRepository.save(client);






       /* roles.stream().forEach(role ->{
            if(role.getRoleName().isEmpty()){
                role.setRoleName("Abonne");


            }
        } );
        System.out.println(roles);
        Set<Role> userRoles=new HashSet<>();
        roles.stream().forEach(role -> {
            Role existingRoles = roleRepository.findByRoleName(role.getRoleName());

            if(existingRoles==null){
                existingRoles = roleRepository.save(role);
                userRoles.add(existingRoles);

            }
            existingRoles.setUtilisateurs(new HashSet<>());
            userRoles.add(existingRoles);
        });*/
//        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
      //  client.setRoles(userRoles);
      //  Utilisateur saveduser = clientService.saveClients(client);
        //client.setRoles(roles);

        //users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
      /*  if(!client.getPassword().equals(client.getRepassword()))
            throw new RuntimeException("les mots de passes ne sont pas identiques");*/

    }


    /*@PostMapping("/login")
    public ResponseEntity login(@RequestBody Utilisateur data){
        ClientsDto usersDto=new ClientsDto();
        usersDto.setEmail(data.getEmail());
        usersDto.setPassword(data.getPassword());
        usersDto.setProfile(data.getProfile());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();




        try {
            UserDto user = new UserDto();
            String username = usersDto.getEmail();

            if(usersDto.getProfile().equals("admine")){
                Abonne userabonne =abonneRepository.findByEmail(username);


            }
            if(userabonne!=null){
                user.setProfile("abonne");
                user.setEmail(userabonne.getEmail());
                user.setNom(userabonne.getNom());
                user.setTelephone(userabonne.getTelephone());
                user.setId(userabonne.getId());
            }
            Livreur userlivreur =livreurRepository.findByEmail(username);


            if(userlivreur!=null){
                user.setProfile("livreur");
                user.setEmail(userlivreur.getEmail());
                user.setNom(userlivreur.getNom());
                user.setTelephone(userlivreur.getTelephone());
                user.setId(userlivreur.getId());
            }
            Utilisateur utilisateur = clientRepository.findByEmail(username);
            if(utilisateur!=null){
                user.setProfile(utilisateur.getProfile());
                user.setEmail(utilisateur.getEmail());
                user.setNom(utilisateur.getNom());
                user.setTelephone(utilisateur.getTelephone());
                user.setId(utilisateur.getIdUser());
            }

            Map<Object, Object> model = new HashMap<>();
            model.put("currentUser", user);

            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied "+e);

        }

    }
*/
}
