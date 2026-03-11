package com.breaze.PrimeraApiCasa.services.impl;

import com.breaze.PrimeraApiCasa.dto.AuthResponse;
import com.breaze.PrimeraApiCasa.dto.LoginRequest;
import com.breaze.PrimeraApiCasa.dto.RegisterRequest;
import com.breaze.PrimeraApiCasa.entities.Usuario;
import com.breaze.PrimeraApiCasa.entities.UsuarioRol;
import com.breaze.PrimeraApiCasa.entities.UsuarioRolId;
import com.breaze.PrimeraApiCasa.repository.RolRepository;
import com.breaze.PrimeraApiCasa.repository.UsuarioRepository;
import com.breaze.PrimeraApiCasa.repository.UsuarioRolRepository;
import com.breaze.PrimeraApiCasa.security.JwtUtil;
import com.breaze.PrimeraApiCasa.services.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final UsuarioRolRepository usuarioRolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (usuarioRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already taken: " + request.getUsername());
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setActivo(true);
        usuarioRepository.save(usuario);

        // Assign default USER role if it exists in the rol table
        rolRepository.findByNombre("USER").ifPresent(rol -> {
            UsuarioRolId id = new UsuarioRolId(usuario.getId(), rol.getId());
            UsuarioRol usuarioRol = new UsuarioRol(id, usuario, rol);
            usuarioRolRepository.save(usuarioRol);
            usuario.getUsuariosRoles().add(usuarioRol);
        });

        UserDetails userDetails = userDetailsService.loadUserByUsername(usuario.getUsername());
        return buildResponse(userDetails);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        return buildResponse(userDetails);
    }

    private AuthResponse buildResponse(UserDetails userDetails) {
        String token = jwtUtil.generateToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setUsername(userDetails.getUsername());
        response.setRoles(roles);
        return response;
    }
}
