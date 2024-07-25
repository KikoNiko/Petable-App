package finalproject.petable.service;

import finalproject.petable.model.AppUserDetails;
import finalproject.petable.model.entity.BaseUser;
import finalproject.petable.model.entity.UserRole;
import finalproject.petable.model.entity.enums.UserRolesEnum;
import finalproject.petable.repository.UserRepository;
import finalproject.petable.service.impl.AppUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppUserDetailsServiceTests {
    private static final String TEST_USERNAME = "test_username";
    private static final String NOT_EXISTENT_USERNAME = "fake_username";
    private static final String TEST_PASSWORD = "test_password123";
    private AppUserDetailsService toTest;
    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        toTest = new AppUserDetailsService(mockUserRepository);
    }

    @Test
    void testLoadUserByUsername_UserNotFound_ShouldThrow() {
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername(NOT_EXISTENT_USERNAME)
        );
    }

    @Test
    void testLoadUserByUsername_UserFound() {
        // Arrange
        BaseUser testUser = new BaseUser();
        testUser.setUsername(TEST_USERNAME);
        testUser.setPassword(TEST_PASSWORD);
        UserRole roleAdmin = new UserRole();
        roleAdmin.setRole(UserRolesEnum.ADMIN);
        UserRole roleClient = new UserRole();
        roleClient.setRole(UserRolesEnum.CLIENT);
        UserRole roleShelter = new UserRole();
        roleShelter.setRole(UserRolesEnum.SHELTER);
        testUser.setRoles(List.of(roleAdmin, roleClient, roleShelter));

        when(mockUserRepository.findByUsername(TEST_USERNAME))
                .thenReturn(Optional.of(testUser));

        // Act
        UserDetails userDetails = toTest.loadUserByUsername(TEST_USERNAME);

        // Assert
        Assertions.assertInstanceOf(AppUserDetails.class, userDetails);

        Assertions.assertEquals(TEST_USERNAME, userDetails.getUsername());
        Assertions.assertEquals(testUser.getPassword(), userDetails.getPassword());

        var expectedRoles = testUser.getRoles().stream().map(UserRole::getRole).map(r -> "ROLE_" + r).toList();
        var actualRoles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        Assertions.assertEquals(expectedRoles, actualRoles);
    }
}
