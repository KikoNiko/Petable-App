package finalproject.petable.service.impl;

import finalproject.petable.model.AppUserDetails;
import finalproject.petable.model.entity.BaseUser;
import finalproject.petable.model.entity.UserRole;
import finalproject.petable.model.entity.enums.UserRolesEnum;
import finalproject.petable.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .map(AppUserDetailsService::map)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User with email " + email + " not found!"));
    }

    private static UserDetails map(BaseUser user) {

        return new AppUserDetails(
                user.getEmail(),
                user.getPassword(),
                user.getRoles()
                        .stream()
                        .map(UserRole::getRole)
                        .map(AppUserDetailsService::map)
                        .toList()
        );
    }

    private static GrantedAuthority map(UserRolesEnum role) {
        return new SimpleGrantedAuthority(
                "ROLE_" + role
        );
    }
}
