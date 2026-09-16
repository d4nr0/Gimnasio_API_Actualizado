package com.cibertec.gimnasio.controller;

import com.cibertec.gimnasio.dto.LoginRequest;
import com.cibertec.gimnasio.dto.LoginResponse;
import com.cibertec.gimnasio.entity.Usuario;
import com.cibertec.gimnasio.repository.UsuarioRepository;
import com.cibertec.gimnasio.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;

    // Servicio Web REST de login: valida usuario/password contra la BD
    // (el password se compara cifrado gracias a BCryptPasswordEncoder)
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {

        UserDetails userDetails = (UserDetails) authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()))
                .getPrincipal();

        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow();

        String token = jwtUtil.generateToken(userDetails, usuario.getRol());

        return ResponseEntity.ok(new LoginResponse(token, usuario.getUsername(), usuario.getRol()));
    }
}
