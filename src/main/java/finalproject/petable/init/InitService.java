package finalproject.petable.init;

import finalproject.petable.model.entity.BaseUser;
import finalproject.petable.model.entity.UserRole;
import finalproject.petable.model.entity.enums.UserRolesEnum;
import finalproject.petable.repository.UserRepository;
import finalproject.petable.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.management.relation.RoleNotFoundException;
import java.util.Arrays;
import java.util.List;

@Component
public class InitService implements CommandLineRunner {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    public InitService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRoleRepository.count() == 0) {
            Arrays.stream(UserRolesEnum.values())
                    .forEach(role -> userRoleRepository.save(new UserRole(role)));
        }
        if (userRepository.count() == 0) {
            String encodedPassword = passwordEncoder.encode(adminPassword);
            BaseUser admin = new BaseUser(adminUsername, encodedPassword);
            UserRole adminRole = userRoleRepository.findByRole(UserRolesEnum.ADMIN).orElse(null);
            if (adminRole != null) {
                admin.setRoles(List.of(adminRole));
            } else {
                throw new RoleNotFoundException("No such role found.");
            }
            userRepository.save(admin);
        }
    }

}
