package org.larturi.springcloud.msvc.usuarios.controllers;

import org.larturi.springcloud.msvc.usuarios.models.entity.Usuario;
import org.larturi.springcloud.msvc.usuarios.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/")
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Usuario> optionalUsuario = usuarioService.findById(id);

        if(optionalUsuario.isPresent()) {
            return ResponseEntity.ok().body(optionalUsuario.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@RequestBody Usuario usuario, @PathVariable Long id) {
        Optional<Usuario> optionalUsuario = usuarioService.findById(id);

        if(optionalUsuario.isPresent()) {
            Usuario usuarioDb = optionalUsuario.get();
            usuarioDb.setNombre(usuario.getNombre());
            usuarioDb.setEmail(usuario.getEmail());
            usuarioDb.setPassword(usuario.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioDb));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Usuario> optionalUsuario = usuarioService.findById(id);

        if(optionalUsuario.isPresent()) {
            usuarioService.delete(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
