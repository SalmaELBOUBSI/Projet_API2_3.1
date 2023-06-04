package designpatterns.builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Message {
        /**
         * id unique du message
         */
        protected int id;
        /**
         * objet du message
         */
        protected String objet;
        /**
         * contenu du message
         */
        protected String contenu;
        /**
         * dateEnvoi du message
         */
        protected LocalDate dateEnvoi;


        /**
         * emetteur des message
         */
        protected Employe emetteur;
        /**
         * listInfos des message
         */
        protected List<Infos> infos=new ArrayList<>();

        private Message(MessageBuilder cb){
            this.id = cb.id;
            this.objet = cb.objet;
            this.contenu = cb.contenu;
            this.dateEnvoi = cb.dateEnvoi;
            this.emetteur = cb.emetteur;
            this.infos = cb.infos;
        }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message message)) return false;
        return id == message.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class MessageBuilder{
        /**
         * id unique du message
         */
        protected int id;
        /**
         * objet du message
         */
        protected String objet;
        /**
         * contenu du message
         */
        protected String contenu;
        /**
         * dateEnvoi du message
         */
        protected LocalDate dateEnvoi;


        /**
         * emetteur des message
         */
        protected Employe emetteur;
        /**
         * listInfos des message
         */
        protected List<Infos> infos=new ArrayList<>();


        public MessageBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public MessageBuilder  setObjet(String objet) {
            this.objet = objet;
            return this;
        }

        public MessageBuilder setContenu(String contenu) {
            this.contenu = contenu;
            return this;
        }

        public MessageBuilder setDateEnvoi(LocalDate dateEnvoi) {
            this.dateEnvoi = dateEnvoi;
            return this;
        }

        public MessageBuilder  setEmetteur(Employe emetteur) {
            this.emetteur = emetteur;
            return this;
        }

        public MessageBuilder  setInfos(List<Infos> infos) {
            this.infos = infos;
            return this;
        }

        public MessageBuilder() throws Exception{
            if(id<=0 || objet==null || contenu==null || dateEnvoi==null || emetteur==null || infos==null ) throw new Exception("Information incomplete");
        }

        public Message build() throws Exception{
            return new Message(this);
        }
    }
}
