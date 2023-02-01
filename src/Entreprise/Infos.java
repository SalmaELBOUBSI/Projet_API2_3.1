package Entreprise;

public class Infos {
    private String dateLecture;
    private Employe emp;

    public Infos(String dateLecture, Employe emp) {
        this.dateLecture = dateLecture;
        this.emp = emp;
    }

    public String getDateLecture() {
        return dateLecture;
    }

    public void setDateLecture(String dateLecture) {
        this.dateLecture = dateLecture;
    }

    public Employe getEmp() {
        return emp;
    }

    public void setEmp(Employe emp) {
        this.emp = emp;
    }
}
