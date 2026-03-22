package br.com.fiap.produtos_ms.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "USUARIO")
public class Usuario {

    @Id
    private String login;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "login"))
    @Column(name = "role")
    private Set<String> roles;

    public Usuario() {
    }

    public Usuario(String login) {
        this.login = login;
        this.roles = new HashSet<>();

        roles.add("ROLE_PRODUTO");
    }

    public String getLogin() {
        return login;
    }

    public Set<String> getRoles() {
        return roles;
    }
}
