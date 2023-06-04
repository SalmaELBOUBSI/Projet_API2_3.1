package mvp.view;

import Entreprise.Message;
import mvp.presenter.MessagePresenter;

import java.util.List;

public interface MessageViewInterface {

    public void setMessage(MessagePresenter presenter);
    public void setListDatas(List<Message> messages);

     public void affMsg(String s);

    public Message selectionner(List<Message> lmsg);
}
