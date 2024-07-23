package finalproject.petable.service.impl;

import finalproject.petable.model.dto.SendMessageDTO;
import finalproject.petable.model.dto.ShowMessageDTO;
import finalproject.petable.model.entity.Client;
import finalproject.petable.model.entity.Message;
import finalproject.petable.model.entity.Shelter;
import finalproject.petable.repository.ClientRepository;
import finalproject.petable.repository.MessageRepository;
import finalproject.petable.repository.ShelterRepository;
import finalproject.petable.service.MessageService;
import finalproject.petable.service.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final ClientRepository clientRepository;
    private final ShelterRepository shelterRepository;

    public MessageServiceImpl(MessageRepository messageRepository, ClientRepository clientRepository, ShelterRepository shelterRepository) {
        this.messageRepository = messageRepository;
        this.clientRepository = clientRepository;
        this.shelterRepository = shelterRepository;
    }

    @Override
    public void sendMessage(SendMessageDTO messageData) {
        Message message = new Message();
        Optional<Client> optionalClient = clientRepository.findByFirstNameAndLastName(messageData.getSenderFirstName(), messageData.getSenderLastName());
        if (optionalClient.isEmpty()) {
            throw new UserNotFoundException("Client not found", message.getSenderId());
        }
        Optional<Shelter> optionalShelter = shelterRepository.findByName(messageData.getReceiverName());
        if (optionalShelter.isEmpty()) {
            throw new UserNotFoundException("Shelter not found", message.getReceiverId());
        }
        Client client = optionalClient.get();
        Shelter shelter = optionalShelter.get();
        message.setSenderId(client.getId());
        message.setReceiverId(shelter.getId());
        message.setBody(messageData.getBody());
        message.setSubject(messageData.getPetName() + " " + messageData.getPetType().toString());
        message.setDate(LocalDate.now());
        messageRepository.save(message);
        shelter.getMessages().add(message);
        shelterRepository.save(shelter);
    }

    @Override
    public List<ShowMessageDTO> getAllMessages(Long userId) {
        Shelter shelter = shelterRepository.findById(userId).orElseThrow();
        return shelter.getMessages()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    private ShowMessageDTO map(Message message) {
        ShowMessageDTO showMessageData = new ShowMessageDTO();
        Client client = clientRepository.findById(message.getSenderId()).orElseThrow();
        showMessageData.setSenderFullName(client.getFullName());
        showMessageData.setSubject(message.getSubject());
        showMessageData.setDate(message.getDate());
        showMessageData.setBody(message.getBody());
        return showMessageData;
    }
}
