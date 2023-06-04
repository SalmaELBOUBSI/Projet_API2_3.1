package mvp.model;

import Entreprise.Employe;
import Entreprise.Message;

import java.util.List;

public interface EmployeSpecial {
    public List<Message> message_envoye(Employe employe);
    public List<Message> message_recu(Employe employe);
    public List<Message> message_non_lu(Employe employe);
}
