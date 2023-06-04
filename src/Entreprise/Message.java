package Entreprise;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * classe methier de gestion des messages
 *
 * @author Salma
 * @version 1.0
 * @see Infos
 */
public class Message {
    /**
     * id unique du message
     */
    private int id;
    /**
     * objet du message
     */
    private String objet;
    /**
     * contenu du message
     */
    private String contenu;
    /**
     * dateEnvoi du message
     */
    private LocalDate dateEnvoi;


    /**
     * emetteur des message
     */
    private Employe emetteur;
    /**
     * listInfos des message
     */
    private List<Infos> infos=new ArrayList<>();

    /**
     * constrcuteur par defaut
     */
    public Message() {
    }

    /**
     * constrcuteur parametre de base
     *
     * @param id id unique du message
     * @param objet objet du message
     * @param contenu contenu du message
     * @param dateEnvoi dateEnvoi du message
     */


    public Message(int id, String objet, String contenu, LocalDate dateEnvoi) {
        this.id = id;
        this.objet = objet;
        this.contenu = contenu;
        this.dateEnvoi = dateEnvoi;
    }

    public Message(int id, String objet, String contenu, LocalDate dateEnvoi, Employe emetteur) {
        this.id = id;
        this.objet = objet;
        this.contenu = contenu;
        this.dateEnvoi = dateEnvoi;
        this.emetteur = emetteur;
    }

    /**
     * constrcuteur parametre complet
     *
     * @param id id unique du message
     * @param objet objet du message
     * @param contenu contenu du message
     * @param dateEnvoi dateEnvoi du message
     * @param emetteur emetteur des message
     * @param infos list info du message
     */
    public Message(int id, String objet, String contenu, LocalDate dateEnvoi, Employe emetteur, List<Infos> infos) {
        this.id = id;
        this.objet = objet;
        this.contenu = contenu;
        this.dateEnvoi = dateEnvoi;
        this.emetteur = emetteur;
        this.infos = infos;
    }



    public Message(int id, String objet, String contenu, Employe emetteur) {
        this.id = id;
        this.objet = objet;
        this.contenu = contenu;
        this.emetteur = emetteur;
    }

    /**
     * getter id
     * @return id unique du message
     */
    public int getId() {
        return id;
    }

    /**
     * setter id
     * @param id id unique du message
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter objet du message
     * @return objet du message
     */
    public String getObjet() {
        return objet;
    }

    /**
     * setter objet message
     * @param objet objet du message
     */
    public void setObjet(String objet) {
        this.objet = objet;
    }

    /**
     * getter contenu du message
     * @return contenu du message
     */
    public String getContenu() {
        return contenu;
    }

    /**
     * setter contenu du message
     * @param contenu contenu du message
     */
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    /**
     * getter dateEnvoi du message
     * @return dateEnvoi du message
     */
    public LocalDate getDateEnvoi() {
        return dateEnvoi;
    }

    /**
     * setter dateEnvoi du message
     * @param dateEnvoi dateEnvoi du message
     */
    public void setDateEnvoi(LocalDate dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    /**
     * getter emetteur de message
     * @return emetteur des message
     */
    public Employe getEmetteur() {
        return emetteur;
    }

    /**
     * setter emetteur
     * @param emetteur emetteur de message
     */
    public void setEmetteur(Employe emetteur) {
        this.emetteur = emetteur;
    }

    /**
     * getter listInfo d'un message
     * @return listInfos d'un message
     */
    public List<Infos> getInfos() {
        return infos;
    }

    /**
     * setter listInfo d'un message
     * @param infos lisInfo d'un message
     */
    public void setInfos(List<Infos> infos) {
        this.infos = infos;
    }

    /**
     * methode toString
     * @return information completes
     */
    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", objet='" + objet + '\'' +
                ", contenu='" + contenu + '\'' +
                ", dateEnvoi='" + dateEnvoi + '\'' +
                ", emetteur=" + emetteur +
                ", infos=" + infos +
                '}';
    }
}
