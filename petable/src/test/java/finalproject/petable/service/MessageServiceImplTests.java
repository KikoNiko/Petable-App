package finalproject.petable.service;

import finalproject.petable.model.dto.messages.SendMessageDTO;
import finalproject.petable.model.entity.BaseUser;
import finalproject.petable.model.entity.Message;
import finalproject.petable.model.entity.enums.PetType;
import finalproject.petable.repository.MessageRepository;
import finalproject.petable.repository.UserRepository;
import finalproject.petable.service.exception.MessageNotFoundException;
import finalproject.petable.service.impl.MessageServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MessageServiceImplTests {
    @Mock
    private MessageRepository mockMessageRepository;
    @Mock
    private UserRepository mockUserRepository;

    private MessageServiceImpl toTest;
    private Message testMessage;
    private BaseUser testSender;
    private BaseUser testReceiver;

    @BeforeEach
    void setUp() {
        toTest = new MessageServiceImpl(mockMessageRepository, mockUserRepository);
        testMessage = new Message();
        testMessage.setId(1L);
        testMessage.setBody("This is a test message!");
        testSender = new BaseUser("testSender", "sender@mail.com", "test123");
        testReceiver = new BaseUser("testReceiver", "receiver@mail.com", "test1234");
    }

    @Test
    void testSendMessage() {
        SendMessageDTO dto = new SendMessageDTO();
        dto.setPetName("Petko");
        dto.setPetType(PetType.DOG);
        dto.setBody(testMessage.getBody());
        dto.setReceiverUsername(testReceiver.getUsername());
        dto.setSenderEmail(testSender.getEmail());
        dto.setSenderUsername(testSender.getUsername());

        when(mockUserRepository.findByUsername(testSender.getUsername())).thenReturn(Optional.of(testSender));
        when(mockUserRepository.findByUsername(testReceiver.getUsername())).thenReturn(Optional.of(testReceiver));

        toTest.sendMessage(dto);

        Assertions.assertNotNull(testReceiver.getMessages());
    }

    @Test
    void testGetMessageById() {
        when(mockMessageRepository.findById(testMessage.getId())).thenReturn(Optional.of(testMessage));
        Message byId = toTest.getMessageById(testMessage.getId());
        Assertions.assertNotNull(byId);
    }

    @Test
    void testGetMessageById_ShouldThrow() {
        Assertions.assertThrows(MessageNotFoundException.class,
                () -> toTest.getMessageById(12L));
    }
}
