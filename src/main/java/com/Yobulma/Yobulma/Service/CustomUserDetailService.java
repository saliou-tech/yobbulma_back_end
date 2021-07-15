package com.Yobulma.Yobulma.Service;

import com.Yobulma.Yobulma.Dto.UserDto;
import com.Yobulma.Yobulma.Entity.Abonne.Abonne;
import com.Yobulma.Yobulma.Entity.Livreur.Livreur;
import com.Yobulma.Yobulma.Entity.Utilisateur;
import com.Yobulma.Yobulma.Repository.Abonne.AbonneRepository;
import com.Yobulma.Yobulma.Repository.Livreur.LivreurRepository;
import com.Yobulma.Yobulma.Repository.UtilisateurRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    AbonneRepository abonneRepository;
    @Autowired
    LivreurRepository livreurRepository;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;




    public void saveUser(Utilisateur user , String profile){
        if(profile.equals("abonne")){
            Abonne abonne  = new Abonne();
            BeanUtils.copyProperties(user,abonne);
            abonne.setPassword(bCryptPasswordEncoder.encode(abonne.getPassword()));
            abonneRepository.save(abonne);

        }
        if(profile.equals("livreur")){
            Livreur livreur  = new Livreur();
            BeanUtils.copyProperties(user,livreur);
            livreur.setPassword(bCryptPasswordEncoder.encode(livreur.getPassword()));

            livreurRepository.save(livreur);

        }
        if(profile.equals("admin")){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));


            utilisateurRepository.save(user);

        }
        if(profile.equals("ChefDeZone")){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            utilisateurRepository.save(user);

        }
    }

    public UserDto findClientByEmail(String email, String profile){
        UserDto user = new UserDto();
        if(profile.equals("abonne")){
            Abonne abonne=abonneRepository.findByEmail(email);
            if(abonne!=null){
                BeanUtils.copyProperties(abonne,user);
                return  user;
            }else{
                return null;
            }
        }
        if(profile.equals("livreur")){
            Livreur livreur=livreurRepository.findByEmail(email);
            if(livreur!=null){
                BeanUtils.copyProperties(livreur,user);
                return  user;
            }else{
                return null;
            }
        }
        if(profile.equals("admin")){
            Utilisateur utilisateur=utilisateurRepository.findByEmail(email);
            if(utilisateur!=null){
                BeanUtils.copyProperties(utilisateur,user);
                return  user;
            }else{
                return null;
            }
        }
        if(profile.equals("ChefDeZone")){
            Utilisateur utilisateur=utilisateurRepository.findByEmail(email);
            if(utilisateur!=null){
                BeanUtils.copyProperties(utilisateur,user);
                return  user;
            }else{
                return null;
            }
        }

        return user;

    }
public UserDto findUserByHisEmail( String email){
        UserDto userDto = new UserDto();
       Utilisateur user =  utilisateurRepository.findByEmail(email);
       if(user!=null){
           BeanUtils.copyProperties(user,userDto);
           return userDto;

       }else{
           Abonne abonne = abonneRepository.findByEmail(email);
           if(abonne!=null){
               BeanUtils.copyProperties(abonne,userDto);
               return  userDto;

           }else{
               Livreur livreur = livreurRepository.findByEmail(email);
               if ((livreur!=null)){
                   BeanUtils.copyProperties(livreur,userDto);

               }else{
                   return  null;
               }

           }
       }
       return  userDto;

}
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto user = this.findUserByHisEmail(email);
        if(user != null) {
            ArrayList<Object> role = new ArrayList<>();
            role.add(user);
            return  new User(user.getEmail(),user.getPassword(),new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }
    /*private String getUserAuthority(String userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            ((HashSet) roles).add(new SimpleGrantedAuthority(role.getRoleName()));
        });
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }*/

    private UserDetails buildUserForAuthentication(UserDto user) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    public UserDto getUser(String email) throws UsernameNotFoundException {
        UserDto user = this.findUserByHisEmail(email);
        if(user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }

}
