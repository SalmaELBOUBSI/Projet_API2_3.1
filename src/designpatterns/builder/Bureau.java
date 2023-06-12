package designpatterns.builder;

import java.util.Objects;
import java.util.regex.Pattern;

public class Bureau {
    protected int id_bureau;
    protected String sigle;
    protected String tel;

    private Bureau(BureauBuilder cb) {
        this.id_bureau = cb.id_bureau;
        this.sigle = cb.sigle;
        this.tel = cb.tel;
    }

    public int getId_bureau() {
        return id_bureau;
    }

    public String getSigle() {
        return sigle;
    }

    public String getTel() {
        return tel;
    }

    @Override
    public String toString() {
        return "Bureau{" +
                "id_bureau=" + id_bureau +
                ", sigle='" + sigle + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bureau)) return false;
        Bureau bureau = (Bureau) o;
        return id_bureau == bureau.id_bureau && sigle.equals(bureau.sigle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_bureau, sigle);
    }

    public static class BureauBuilder {
        protected int id_bureau;
        protected String sigle;
        protected String tel;
        protected Pattern telPattern;

        public BureauBuilder setId_bureau(int id_bureau) {
            this.id_bureau = id_bureau;
            return this;
        }

        public BureauBuilder setSigle(String sigle) {
            this.sigle = sigle;
            return this;
        }

        public BureauBuilder setTel(String tel, String regex) {
            if (!tel.matches(regex)) {
                throw new IllegalArgumentException("Numéro de téléphone invalide.");
            }
            this.tel = tel;
            this.telPattern = Pattern.compile(regex);
            return this;
        }

        public Bureau build() throws Exception {
            if (id_bureau <= 0 || sigle == null || tel == null ) {
                throw new Exception("Informations incomplètes pour la création du bureau.");
            }
            return new Bureau(this);
        }
    }


}
