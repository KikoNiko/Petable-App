package finalproject.petable.service;

import finalproject.petable.model.entity.BaseUser;
import finalproject.petable.model.entity.Image;
import finalproject.petable.repository.ImageRepository;
import finalproject.petable.repository.UserRepository;
import finalproject.petable.service.exception.UserNotFoundException;
import finalproject.petable.service.impl.ImageServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ImageServiceImplTests {

    @Mock
    private ImageRepository mockImageRepository;
    @Mock
    private UserRepository mockUserRepository;
    @Captor
    private ArgumentCaptor<Image> imageCaptor;
    private ImageServiceImpl toTest;
    private Image testImage;

    @BeforeEach
    void setUp() {
        toTest = new ImageServiceImpl(mockImageRepository, mockUserRepository);
        testImage = new Image();
        testImage.setId(1L);
        testImage.setName("test_image");
        testImage.setUrl("https://testimageurl.com");
    }

    @Test
    void testSave() {
        toTest.save(testImage);
        verify(mockImageRepository).save(imageCaptor.capture());
        Image savedImage = imageCaptor.getValue();

        Assertions.assertNotNull(savedImage);
        Assertions.assertEquals(savedImage.getName(), testImage.getName());
        Assertions.assertEquals(savedImage.getUrl(), testImage.getUrl());
    }

    @Test
    void testAssignImageToUser() {
        BaseUser testUser = new BaseUser("testUser", "test@mail.com", "test123");
        testUser.setId(2L);
        when(mockUserRepository.findByUsername(testUser.getUsername())).thenReturn(Optional.of(testUser));

        toTest.assignImageToUser(testImage.getUrl(), testUser.getUsername());
        Assertions.assertNotNull(testUser.getProfileImageUrl());
        Assertions.assertEquals(testUser.getProfileImageUrl(), testImage.getUrl());
    }

    @Test
    void testAssignImageToUser_ShouldThrow() {
        Assertions.assertThrows(UserNotFoundException.class,
                () -> toTest.assignImageToUser(testImage.getUrl(),"balbla"));
    }

    @Test
    void testGetByName() {
        when(mockImageRepository.findByName(testImage.getName())).thenReturn(Optional.of(testImage));
        Image byName = toTest.getByName(testImage.getName());
        Assertions.assertNotNull(byName);
        Assertions.assertEquals(byName.getUrl(), testImage.getUrl());
    }
}
