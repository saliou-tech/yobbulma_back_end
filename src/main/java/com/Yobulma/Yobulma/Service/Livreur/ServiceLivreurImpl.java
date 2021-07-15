package com.Yobulma.Yobulma.Service.Livreur;

import com.Yobulma.Yobulma.Entity.Abonne.Abonne;
import com.Yobulma.Yobulma.Entity.Livreur.Livreur;
import com.Yobulma.Yobulma.Repository.Abonne.AbonneRepository;
import com.Yobulma.Yobulma.Repository.Livreur.LivreurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Component
@Transactional
public class ServiceLivreurImpl implements LivreurService {
@Autowired
    private LivreurRepository livreurRepository;
    @Override
    public Livreur enregistrerLivreur(Livreur livreur) {
        return livreurRepository.save(livreur);
    }

    @Override
    public List<Livreur> getAllLivreur() {
        return livreurRepository.findAll();
    }

    @Override
    public void supprimerLivreur(Livreur livreur) {
        livreurRepository.delete(livreur);

    }

    @Override
    public Optional<Livreur> findLivreurById(Long id) {
        return livreurRepository.findById(id);
    }
}
