package designpatterns.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Employe extends Element {
        /**
         * identifiant de l'employe unique
         */
        private int id;
        /**
         * mail de l'employe unique
         */
        private String mail;
        /**
         * nom de l'employe
         */
        private String nom;
        /**
         * prenom de l'employe
         */
        private String prenom;
        /**
         * bureau de l'employe
         */
        private Bureau bureau;
        /**
         * liste des message de l'employe
         */
        private List<Message> messages = new ArrayList<>();

        /**
         * constructeur par defaut
         */
        public Employe() {
        }

        public Employe(int id) {
            this.id = id;
        }

        /**
         * constructeur parametre de base
         *
         * @param id identifiant de l'employe ,affecte par la bd
         * @param mail mail unique pour chaque employe
         * @param nom nom de l'employe
         * @param prenom prenom de l'employe
         */

        public Employe(int id, String mail, String nom, String prenom) {
            this.id = id;
            this.mail = mail;
            this.nom = nom;
            this.prenom = prenom;
        }


        /**
         * getter id de l'employe
         *
         * @return id de l'employe
         */
        public int getId() {
            return id;
        }

    @Override
    public int empTot() {
        return 0;
    }

    /**
         * setter
         * @param id de l'employe
         */
        public void setId(int id) {
            this.id = id;
        }

        /**
         * getter mail unique de l'employe
         * @return mail de l'employe
         */
        public String getMail() {
            return mail;
        }

        /**
         * setter mail unique de l'employe
         * @param mail mail de l'employe
         */
        public void setMail(String mail) {
            this.mail = mail;
        }

        /**
         * getter nom de l'employe
         * @return nom de l'employe
         */
        public String getNom() {
            return nom;
        }

        /**
         * setter nom de l'employe
         * @param nom nom de l'employe
         */
        public void setNom(String nom) {
            this.nom = nom;
        }

        /**
         * getter prenom de l'employe
         * @return prenom de l'employe
         */
        public String getPrenom() {
            return prenom;
        }

        /**
         * setter prenom de l'employe
         * @param prenom prenom de l'employe
         */
        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        /**
         * getter Bureau ded l'employer
         *
         * @return bureau de l'employe
         */
        public Bureau getBureau() {
            return bureau;
        }

        /**
         * setter Bureau de l'employe
         * @param bureau bureau de l'employe
         */
        public void setBureau(Bureau bureau) {
            this.bureau = bureau;
        }

        /**
         * getter Messages de l'employe
         * @return liste des message de l'employe
         */
        public List<Message> getMessages() {
            return messages;
        }

        /**
         * setter Message de l'employe
         * @param messages liste de message de l'employe
         */
        public void setMessages(List<Message> messages) {
            this.messages = messages;
        }

        /**
         * Methode d'egaliste entre deux employe base sur le mail de l'employe
         * @param o autre element
         * @return egalite ou pas
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Employe employe)) return false;
            return Objects.equals(mail, employe.mail);
        }

        /**
         * Test du hashcode de l'employe base sur le mail
         * @return la valeur du hashcode
         */
        @Override
        public int hashCode() {
            return Objects.hash(mail);
        }

        /**
         * methode toString
         * @return information completes
         */
        @Override
        public String toString() {
            return "Employe{" +
                    "id=" + id +
                    ", mail='" + mail + '\'' +
                    ", nom='" + nom + '\'' +
                    ", prenom='" + prenom + '\'' +
                    ", bureau=" + bureau +
                    ", messages=" + messages +
                    '}';
        }

        public void addMessage(Message msg){
            messages.add(msg);
            msg.setEmetteur(this);
        }

        public void supMessage(Message msg){
            messages.remove(msg);
            msg.setEmetteur(null);
        }

        public List<Message> message_envoye(){
            List<Message> lmsg = new ArrayList<>();
            for(Message msg : messages){
                if(msg.getDateEnvoi()!= null) lmsg.add(msg);
            }
            return lmsg;
        }

        public List<Message> message_recu(){
            List<Message> lmsg = new ArrayList<>();
            for(Message msg : messages){

            }
            return lmsg;
        }

        public List<Message> message_non_lu(){
            List<Message> lmsg = new ArrayList<>();
            for(Message msg : messages){
                if(msg.getInfos() == null) lmsg.add(msg);
            }
            return lmsg;
        }
}

