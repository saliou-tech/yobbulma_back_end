package com.Yobulma.Yobulma.Repository.Livreur;

        import com.Yobulma.Yobulma.Entity.Livreur.Livreur;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

        import java.util.Optional;
 @Repository
public interface LivreurRepository extends JpaRepository<Livreur,Long> {
    Livreur findByEmail(String email);

}
