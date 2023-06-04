package mvp.view;

import Entreprise.Employe;
import Entreprise.Message;
import mvp.presenter.MessagePresenter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class MessageViewConsole implements MessageViewInterface{
    private MessagePresenter presenter;
    private List<Message> lmsg;

    private Scanner sc= new Scanner(System.in);

    public MessageViewConsole(){

    }
    @Override
    public void setMessage(MessagePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setListDatas(List<Message> messages) {
        this.lmsg=messages;
        affListe(lmsg);
        menu();
    }

    @Override
    public void affMsg(String msg) {
        System.out.println("information:"+msg);
    }

    @Override
    public Message selectionner(List<Message> lmsg) {
        int nl =  choixListe(lmsg);
        Message msg = lmsg.get(nl-1);
        return msg;
    }

    public void menu(){
        do{
            int ch = choixListe(Arrays.asList("ajout", "retrait", "rechercher", "modifier", "fin"));

            switch(ch){
                case 1: ajouter();
                    break;
                case 2 : retirer();
                    break;
                case 3: rechercher();
                    break;
                case 4 : modifier();
                    break;
                case 5 : return;
            }
        }while(true);
    }

    private void modifier() {
        affListe(lmsg);
        int nl = choixElt(lmsg);

        Message msg = lmsg.get(nl-1);
        String objet = modifyIfNotBlank("objet : ",msg.getObjet());
        String contenu = modifyIfNotBlank("contenu : ",msg.getContenu());
        //LocalDate dateenvoi = LocalDate.parse(modifyIfNotBlank("date envoi : ",""));
        //String dateEnvoi = modifyIfNotBlank("date envoi : ", "");
        /*LocalDate dateenvoi = null;
        if (!dateEnvoi.isEmpty()) {
            dateenvoi = LocalDate.parse(dateEnvoi);
        }
         */
        int emetteur = Integer.parseInt(modifyIfNotBlank("emetteur : ", String.valueOf(msg.getEmetteur().getId())));
        presenter.update(new Message(msg.getId(),objet,contenu,new Employe(emetteur)));
        lmsg=presenter.getAll();
        affListe(lmsg);
    }

    private void rechercher() {
        System.out.println("id_message : ");
        int id_message= sc.nextInt();
        presenter.search(id_message);
    }

    private void retirer() {
        affListe(lmsg);
        int nl = choixElt(lmsg);
        Message msg =lmsg.get(nl-1);
        presenter.removeMessage(msg);
        lmsg=presenter.getAll();
        affListe(lmsg);
    }

    private void ajouter() {
        System.out.println("Objet : ");
        String objet = sc.nextLine();
        System.out.println("Contenu : ");
        String contenu = sc.nextLine();
        LocalDate datenevoi = LocalDate.parse(modifyIfNotBlank("date : ",""));
        System.out.println("Emeteur : ");
        int emmeteur = sc.nextInt();

        presenter.addMessage(new Message(0,objet,contenu,datenevoi,new Employe(emmeteur)));
        lmsg=presenter.getAll();
        affListe(lmsg);

    }


}
