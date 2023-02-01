package Entreprise;

import java.util.ArrayList;
import java.util.List;

public class Message {

    private int id;
    private String objet;
    private String contenu;
    private String dateEnvoi;
    private List<Infos> infos=new ArrayList<>();

    public Message(int id, String objet, String contenu, String dateEnvoi) {
        this.id = id;
        this.objet = objet;
        this.contenu = contenu;
        this.dateEnvoi = dateEnvoi;
    }

    public Message(int id, String objet, String contenu, String dateEnvoi, List<Infos> infos) {
        this.id = id;
        this.objet = objet;
        this.contenu = contenu;
        this.dateEnvoi = dateEnvoi;
        this.infos = infos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(String dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public List<Infos> getInfos() {
        return infos;
    }

    public void setInfos(List<Infos> infos) {
        this.infos = infos;
    }
}
