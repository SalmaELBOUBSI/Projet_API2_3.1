package mvp.presenter;

import Entreprise.Message;
import mvp.model.DAOMessage;
import mvp.view.MessageViewInterface;

import java.util.List;


public class MessagePresenter {

    private DAOMessage model;
    private MessageViewInterface view;

    //private static final Logger logger = LogManager.getLogger(MessagePresenter.class);

    public MessagePresenter(DAOMessage model, MessageViewInterface view){
        this.model = model;
        this.view = view;
        this.view.setMessage(this);
    }

    public void start() {
        view.setListDatas(getAll());
    }
    public List<Message> getAll(){
        return model.getMessages();
    }

    public void addMessage(Message message) {
        Message msg = model.addMessage(message);
        if(msg!=null) view.affMsg("création de :"+msg);
        else view.affMsg("erreur de création");
    }

    public void removeMessage(Message msg) {
        boolean ok = model.removeMessage(msg);
        if(ok) view.affMsg("message effacé");
        else view.affMsg("message non effacé");
    }

    public Message selectionner() {
        System.out.println("appel de sélection");
        //logger.info("appel de sélection");
        Message msg = view.selectionner(model.getMessages());
        return msg;
    }

    public void update(Message message) {
        Message msg =model.updateMessage(message);
        if(msg==null) view.affMsg("mise à jour infructueuse");
        else view.affMsg("mise à jour effectuée : "+msg);
    }

    public Message search(int id_message) {
        Message msg = model.readMessage(id_message);
        if(msg==null) view.affMsg("recherche infructueuse");
        else view.affMsg(msg.toString());
        return msg;
    }

}
