package finalproject.petable.service.impl;

import finalproject.petable.model.dto.ReplyMessageDTO;
import finalproject.petable.model.dto.SendMessageDTO;
import finalproject.petable.model.dto.ShowMessageDTO;
import finalproject.petable.model.entity.BaseUser;
import finalproject.petable.model.entity.Message;
import finalproject.petable.repository.MessageRepository;
import finalproject.petable.repository.UserRepository;
import finalproject.petable.service.MessageService;
import finalproject.petable.service.exception.MessageNotFoundException;
import finalproject.petable.service.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void sendMessage(SendMessageDTO messageData) {
        Message message = new Message();
        Optional<BaseUser> optionalSender = userRepository.findByUsername(messageData.getSenderUsername());
        if (optionalSender.isEmpty()) {
            throw new UserNotFoundException("User not found", 0L);
        }
        Optional<BaseUser> optionalReceiver = userRepository.findByUsername(messageData.getReceiverUsername());
        if (optionalReceiver.isEmpty()) {
            throw new UserNotFoundException("User not found", 0L);
        }
        BaseUser sender = optionalSender.get();
        BaseUser receiver = optionalReceiver.get();
        message.setSenderId(sender.getId());
        message.setReceiverId(receiver.getId());
        message.setBody(messageData.getBody());
        message.setSubject(messageData.getPetName() + " " + messageData.getPetType().toString());
        message.setDate(LocalDate.now());
        messageRepository.save(message);
        receiver.getMessages().add(message);
        userRepository.save(receiver);
    }

    @Override
    public List<ShowMessageDTO> getAllMessages(Long userId) {
        BaseUser user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User not found!", userId);
        }
        return user.getMessages()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteMessage(Long id) {
        Message message = messageRepository.findById(id).orElse(null);
        userRepository.findAll()
                .stream()
                .map(BaseUser::getMessages)
                .filter(messages -> messages.contains(message))
                .forEach(messages -> messages.remove(message));
        messageRepository.deleteById(id);
    }

    @Override
    public Message getMessageById(Long id) {
        return messageRepository.findById(id).orElseThrow(
                () -> new MessageNotFoundException("Message with id " + id + " not found.", id)
        );
    }

    @Override
    public void replyMessage(Long messageId, ReplyMessageDTO message) {
        Message originalMessage = getMessageById(messageId);
        Message reply = new Message();
        reply.setSubject(originalMessage.getSubject());
        reply.setSenderId(originalMessage.getReceiverId());
        reply.setReceiverId(originalMessage.getSenderId());
        reply.setDate(LocalDate.now());
        reply.setBody(message.getBody());
        messageRepository.save(reply);
        BaseUser user = userRepository.findById(reply.getReceiverId()).get();
        user.getMessages().add(reply);
        userRepository.save(user);
    }

    private ShowMessageDTO map(Message message) {
        ShowMessageDTO showMessageData = new ShowMessageDTO();
        BaseUser user = userRepository.findById(message.getSenderId()).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User not found!", message.getSenderId());
        }
        showMessageData.setId(message.getId());
        showMessageData.setSenderUsername(user.getUsername());
        showMessageData.setSubject(message.getSubject());
        showMessageData.setDate(message.getDate());
        showMessageData.setBody(message.getBody());
        return showMessageData;
    }
}
