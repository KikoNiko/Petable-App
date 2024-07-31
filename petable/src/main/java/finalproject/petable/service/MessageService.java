package finalproject.petable.service;

import finalproject.petable.model.dto.messages.ReplyMessageDTO;
import finalproject.petable.model.dto.messages.SendMessageDTO;
import finalproject.petable.model.dto.messages.ShowMessageDTO;
import finalproject.petable.model.entity.Message;

import java.util.List;
import java.util.Map;

public interface MessageService {

    void sendMessage(SendMessageDTO messageData);

    Map<String, List<ShowMessageDTO>> getAllMessages(Long userId);
    void deleteMessage(Long id);

    Message getMessageById(Long id);

    void replyMessage(Long messageId, ReplyMessageDTO message);
}
