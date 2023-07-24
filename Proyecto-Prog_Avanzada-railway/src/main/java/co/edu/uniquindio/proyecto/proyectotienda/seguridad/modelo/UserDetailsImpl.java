package co.edu.uniquindio.proyecto.proyectotienda.seguridad.modelo;

import co.edu.uniquindio.proyecto.proyectotienda.modelo.Cuenta;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Moderador;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(Cuenta cuenta) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if(cuenta instanceof Usuario) {
            authorities.add(new SimpleGrantedAuthority("CLIENTE"));
        } else if (cuenta instanceof Moderador) {
            authorities.add(new SimpleGrantedAuthority("MODERADOR"));
            System.out.println("Es moderador...");
        }
        return new UserDetailsImpl(cuenta.getEmail(), cuenta.getContrasenia(), authorities);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
