package org.larturi.springcloud.msvc.usuarios.services;

import org.apache.catalina.User;
import org.larturi.springcloud.msvc.usuarios.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<Usuario> findAll();
    Optional<Usuario> findById(Long id);
    User save(Usuario usuario);
    void delete(Long id);
}
