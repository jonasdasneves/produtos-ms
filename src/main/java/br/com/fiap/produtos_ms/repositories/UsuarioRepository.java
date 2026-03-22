package br.com.fiap.produtos_ms.repositories;

import br.com.fiap.produtos_ms.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}
