package designpatterns.builder;

import java.time.LocalDate;

public class Infos {
        /**
         * id_employe recepteur du message
         */
        protected Employe recepteur;

        /**
         * date de lecture du message
         */
        protected LocalDate dateLecture;

        /**
         * constructeur par defaut
         */
        public Infos() {
        }

        /**
         * constrcuteur parametre complet
         *
         * @param recepteur   recepteur du message
         * @param dateLecture date de lecture du message
         */
        public Infos(Employe recepteur, LocalDate dateLecture) {
            this.recepteur = recepteur;
            this.dateLecture = dateLecture;
        }

        /**
         * getter dateLecture
         *
         * @return dateLecture d'un message
         */
        public LocalDate getDateLecture() {
            return dateLecture;
        }

        /**
         * setter dateLecture
         *
         * @param dateLecture dateLecture d'un message
         */
        public void setDateLecture(LocalDate dateLecture) {
            this.dateLecture = dateLecture;
        }

        /**
         * getter  recepteur
         *
         * @return recepteur recepteur du message
         */
        public Employe getRecepteur() {
            return recepteur;
        }

        /**
         * setter recepteur recpteur
         *
         * @param recepteur recepteur du message
         */
        public void setRecepteur(Employe recepteur) {
            this.recepteur = recepteur;
        }

        /**
         * methode toString
         *
         * @return information completes
         */
        @Override
        public String toString() {
            return "Infos{" +
                    "recepteur=" + recepteur +
                    ", dateLecture=" + dateLecture +
                    '}';
        }
}
