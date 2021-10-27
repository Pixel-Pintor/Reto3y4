package co.usa.reto3.service;

import co.usa.reto3.model.Message;
import co.usa.reto3.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll() {
        return messageRepository.getAll();
    }

    public Optional<Message> getMessage(int id) {
        return messageRepository.getMessage(id);
    }

    public Message save(Message message) {
        if (message.getIdMessage() == null) {
            return messageRepository.save(message);
        } else {
            Optional<Message> msgAux = messageRepository.getMessage(message.getIdMessage());
            if (msgAux.isEmpty()) {
                return messageRepository.save(message);
            } else {
                return message;
            }
        }
    }

    public boolean deleteMessage(int messageId) {
        System.out.println("Voy a borrar el mensaje con el id: " + messageId);
        return getMessage(messageId).map(message -> {
            messageRepository.delete(message);
            return true;
        }).orElse(false);
    }

    public Message update(Message message) {
        if (message.getIdMessage() != null) {
            Optional<Message> element = messageRepository.getMessage(message.getIdMessage());
            if (element.isPresent()) {
                if (message.getIdMessage() != null) {
                    element.get().setMessageText(message.getMessageText());
                }
                messageRepository.save(element.get());
                return element.get();
            }
        }
        return message;
    }
}
