package Entreprise;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bureau {
    private int id;
    private String sigle;
    private String tel;
    private List<Employe> employes = new ArrayList<>();

    public Bureau(int id, String sigle, String tel) {
        this.id = id;
        this.sigle = sigle;
        this.tel = tel;
    }

    public Bureau(int id, String sigle, String tel, List<Employe> employes) {
        this.id = id;
        this.sigle = sigle;
        this.tel = tel;
        this.employes = employes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bureau bureau)) return false;
        return Objects.equals(sigle, bureau.sigle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sigle);
    }
}
