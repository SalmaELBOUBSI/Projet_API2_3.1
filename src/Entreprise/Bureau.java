package Entreprise;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * classe metier de gestion de bureau
 * @author Salma
 * @version 1.0
 * @see Employe
 */

public class Bureau {
    /**
     * identifiant unique du bureau
     */
    private int id;
    /**
     * sigle du bureau
     */
    private String sigle;
    /**
     * Numero de telephone du bureau
     */
    private String tel;


    /**
     * constructeur par defaut
     */
    public Bureau() {
    }

    public Bureau(int id) {
        this.id = id;
    }

    /**
     * constructeur parametre de base
     *
     *
     * @param id identifiant unique du bureau,affecte par la base de donnees
     * @param sigle sigle du bureau
     * @param tel numero de telephone du bureau
     */

    public Bureau(int id, String sigle, String tel) {
        this.id = id;
        this.sigle = sigle;
        this.tel = tel;
    }


    /**
     *getter id
     * @return identifiant du bureau
     */
    public int getId() {
        return id;
    }

    /**
     * setter id
     * @param id identifiant du bureau
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *getter sigle
     * @return sigle du bureau
     */
    public String getSigle() {
        return sigle;
    }

    /**
     * setter sigle
     * @param sigle sigle du bureau
     */
    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    /**
     * getter tel
     * @return tel numero de telphone du bureau
     */
    public String getTel() {
        return tel;
    }

    /**
     * setter tel
     * @param tel numero de telephone du bureau
     */
    public void setTel(String tel) {
        this.tel = tel;
    }


    /**
     * methode d'egalite entre deux bureau base sur leur sigle
     * @param o autre element
     * @return egalite ou pas
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bureau bureau)) return false;
        return Objects.equals(sigle, bureau.sigle);
    }

    /**
     * Test du hashcode du bureau base sur le sigle
     * @return la valeur du hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(sigle);
    }

    /**
     * methode toString
     * @return information completes
     */
    @Override
    public String toString() {
        return "Bureau{" +
                "id=" + id +
                ", sigle='" + sigle + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
