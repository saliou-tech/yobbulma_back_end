package com.Yobulma.Yobulma.Service.Abonne;

import com.Yobulma.Yobulma.Entity.Abonne.Abonne;
import com.Yobulma.Yobulma.Repository.Abonne.AbonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Component
@Transactional
public class AbonneServiceImpl  implements  AbonneService{
    @Autowired
    private  AbonneRepository abonneRepository;


    @Override
    public Abonne enregistrerAbonne(Abonne abonne) {

        return abonneRepository.save(abonne);
    }

    @Override
    public List<Abonne> getAllAbonne() {

        return abonneRepository.findAll();
    }

    @Override
    public void supprimerAbonne(Long id) {

        abonneRepository.deleteById(id);
    }

    @Override
    public Optional<Abonne> findAbonneById(Long id) {

        return abonneRepository.findById(id);

    }

    @Override
    public Abonne findByEmail(String email) throws Exception {
        Abonne client=abonneRepository.findByEmail(email);
        if(client!=null){
            return client;
        }else {
            throw new Exception("Client not found");
        }
    }


}
