package mvp.model;

import Entreprise.Message;

import java.util.List;

public interface DAOMessage {
    Message addMessage(Message message);
    boolean removeMessage(Message message);
    Message updateMessage(Message message);
    Message readMessage(int id_message);
    List<Message> getMessages();
}
