package Entreprise;

import java.time.LocalDate;

/**
 * classe metier de gestion des informarions de message
 *
 * @author Salma
 * @version 1.0
 * @see Employe
 * @see Message
 */
public class Infos {
    /**
     * id_employe recepteur du message
     */
    private Employe id_emp;
    /**
     * id_message envoye
     */
    private Message id_msg;

    /**
     * date de lecture du message
     */
    private LocalDate dateLecture;

    /**
     * constructeur par defaut
     */
    public Infos() {
    }

    /**
     * constrcuteur parametre complet
     *
     * @param id_emp id_employe recepteur
     * @param id_msg id_message emit
     * @param dateLecture date de lecture du message
     */
    public Infos(Employe id_emp, Message id_msg, LocalDate dateLecture) {
        this.id_emp = id_emp;
        this.id_msg = id_msg;
        this.dateLecture = dateLecture;
    }

    /**
     * getter dateLecture
     * @return dateLecture d'un message
     */
    public LocalDate getDateLecture() {
        return dateLecture;
    }

    /**
     * setter dateLecture
     * @param dateLecture dateLecture d'un message
     */
    public void setDateLecture(LocalDate dateLecture) {
        this.dateLecture = dateLecture;
    }

    /**
     * getter id_emp recepteur
     * @return id_emp recepteur du message
     */
    public Employe getId_emp() {
        return id_emp;
    }

    /**
     * setter id_emp recpteur
     * @param id_emp recepteur du message
     */
    public void setId_emp(Employe id_emp) {
        this.id_emp = id_emp;
    }

    /**
     * getter id_msg emit
     * @return id_msg eemit
     */
    public Message getId_msg() {
        return id_msg;
    }

    /**
     * setter id_msg emi
     * @param id_msg emit
     */
    public void setId_msg(Message id_msg) {
        this.id_msg = id_msg;
    }

    /**
     * methode toString
     * @return information completes
     */
    @Override
    public String toString() {
        return "Infos{" +
                "id_emp=" + id_emp +
                ", id_msg=" + id_msg +
                ", dateLecture=" + dateLecture +
                '}';
    }
}
