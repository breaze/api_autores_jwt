package com.breaze.PrimeraApiCasa.security;

import com.breaze.PrimeraApiCasa.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementación de {@link UserDetailsService} de Spring Security.
 *
 * Se encarga de cargar los datos de un usuario desde la base de datos
 * durante el proceso de autenticación. Spring Security llama automáticamente
 * a {@code loadUserByUsername} cuando se intenta iniciar sesión.
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    // Repositorio para consultar usuarios en la base de datos
    private final UsuarioRepository usuarioRepository;

    /**
     * Busca un usuario por su nombre de usuario y construye el objeto
     * {@link UserDetails} que Spring Security necesita para autenticar y autorizar.
     *
     * @param username nombre de usuario recibido en el login
     * @return {@link UserDetails} con credenciales y roles del usuario
     * @throws UsernameNotFoundException si no existe un usuario con ese nombre
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca el usuario junto con sus roles; lanza excepción si no existe
        var usuario = usuarioRepository.findByUsernameWithRoles(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Convierte los roles del usuario al formato que entiende Spring Security (ROLE_<nombre>)
        List<SimpleGrantedAuthority> authorities = usuario.getUsuariosRoles().stream()
                .map(ur -> new SimpleGrantedAuthority("ROLE_" + ur.getRol().getNombre()))
                .toList();

        // Construye y retorna el UserDetails con usuario, contraseña, estado activo y permisos
        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getActivo(),   // habilitado solo si el usuario está activo
                true, true, true,      // cuenta no expirada, credenciales no expiradas, no bloqueada
                authorities
        );
    }
}
