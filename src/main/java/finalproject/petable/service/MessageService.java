package finalproject.petable.service;

import finalproject.petable.model.dto.ReplyMessageDTO;
import finalproject.petable.model.dto.SendMessageDTO;
import finalproject.petable.model.dto.ShowMessageDTO;
import finalproject.petable.model.entity.Message;

import java.util.List;

public interface MessageService {

    void sendMessage(SendMessageDTO messageData);

    List<ShowMessageDTO> getAllMessages(Long userId);
    void deleteMessage(Long id);

    Message getMessageById(Long id);

    void replyMessage(Long messageId, ReplyMessageDTO message);
}
