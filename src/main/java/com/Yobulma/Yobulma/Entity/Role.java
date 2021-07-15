package com.Yobulma.Yobulma.Entity;

import javax.persistence.*;
import java.util.Collection;
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Role() {
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }




/*
    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private Collection<Utilisateur> utilisateurs;*/
}
