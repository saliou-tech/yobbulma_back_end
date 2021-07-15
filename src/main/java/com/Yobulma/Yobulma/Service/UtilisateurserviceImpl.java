package com.Yobulma.Yobulma.Service;

import com.Yobulma.Yobulma.Entity.Utilisateur;

import com.Yobulma.Yobulma.Repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Component
@Transactional
public class UtilisateurserviceImpl implements UtilisateurService {


    private  BCryptPasswordEncoder bCryptPasswordEncoder;

  //  private RoleRepository roleRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public Utilisateur saveClients(Utilisateur user) {
        try {
            String hashPassword=bCryptPasswordEncoder.encode(user.getPassword());
            System.out.println(hashPassword);
            user.setPassword(hashPassword);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return utilisateurRepository.save(user);
    }
}
