package infosys.backend.service;

import infosys.backend.model.Message;
import infosys.backend.model.User;
import infosys.backend.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    // Save a message
    public Message saveMessage(Message message) {
        if (message.getSentAt() == null) {
    message.setSentAt(LocalDateTime.now(ZoneId.of("Asia/Kolkata")));
}
return messageRepository.save(message);

    }

    // Get chat history between two users
    // Replace the existing method
public List<Message> getMessagesBetweenUsers(User user1, User user2) {
    return messageRepository.findMessagesBetweenUsersWithUsers(user1, user2);
}


    // Optional: get all messages
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}
