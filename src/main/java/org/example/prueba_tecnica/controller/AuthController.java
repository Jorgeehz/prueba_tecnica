package org.example.prueba_tecnica.controller;

import lombok.RequiredArgsConstructor;
import org.example.prueba_tecnica.entity.Rol;
import org.example.prueba_tecnica.entity.Usuario;
import org.example.prueba_tecnica.repository.RolRepository;
import org.example.prueba_tecnica.repository.UsuarioRepository;
import org.example.prueba_tecnica.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(Map.of("error", "El email ya está registrado"));
        }

        Rol rol = rolRepository.findByNombre("USER").orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        request.setRol(rol);
        usuarioRepository.save(request);

        // Cargar el usuario como UserDetails
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(Map.of("token", token));
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        UserDetails userDetails = userDetailsService.loadUserByUsername(email); // Cargar usuario
        String token = jwtService.generateToken(userDetails); // Generar token con UserDetails

        return ResponseEntity.ok(Map.of("token", token));
    }
}
