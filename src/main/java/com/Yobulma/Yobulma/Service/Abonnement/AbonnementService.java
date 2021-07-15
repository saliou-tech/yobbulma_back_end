package com.Yobulma.Yobulma.Service.Abonnement;

import com.Yobulma.Yobulma.Dto.Abonnement.AbonnementDto;
import com.Yobulma.Yobulma.Entity.Abonnement.Abonnement;
import com.Yobulma.Yobulma.Repository.Abonnement.AbonnementRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbonnementService {
    @Autowired
    AbonnementRepository abonnementRepository;


    public void saveAbonnement(AbonnementDto abonnementDto){
        Abonnement abonnement = new Abonnement();
        BeanUtils.copyProperties(abonnementDto,abonnement);
         abonnementRepository.save(abonnement);
    }
}
