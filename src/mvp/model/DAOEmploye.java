package mvp.model;

import Entreprise.Employe;

import java.util.List;

public interface DAOEmploye {
    Employe addEmploye(Employe employe);

    boolean removeEmploye(Employe employe);

    Employe updateEmploye(Employe employe);

    Employe readEmploye(int idClient);

    List<Employe> getEmployes();
}
