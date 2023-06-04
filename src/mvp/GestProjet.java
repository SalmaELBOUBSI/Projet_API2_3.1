package mvp;

import mvp.model.*;
import mvp.presenter.BureauPresenter;
import mvp.presenter.EmployePresenter;
import mvp.presenter.MessagePresenter;
import mvp.view.*;
import utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;

public class GestProjet {
    public static void main(String[] args) {
        GestProjet gm = new GestProjet();
        gm.gestion();
    }

    private DAOEmploye dm;
    private DAOBureau db;
    private DAOMessage dam;

    private EmployePresenter ep;
    private BureauPresenter bp;
    private MessagePresenter mp;

    private EmployeViewInterface ei;
    private BureauViewInterface bi;
    private MessageViewInterface mi;


    public void gestion(){
        dm = new EmployeModelDB();
        //dm= new EmployeModelHyb();
        db = new BureauModelDB();
        dam = new MessageModelDB();

        ei = new EmployeViewConsole();
        bi = new BureauViewConsole();
        mi = new MessageViewConsole();

        ep = new EmployePresenter(dm,ei);
        bp = new BureauPresenter(db,bi);
        mp = new MessagePresenter(dam,mi);

        bp.setEmployePresenter(ep);

        List<String> loptions = Arrays.asList("Employes","Bureaux","Message","Fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch){
                case 1: ep.start();
                    break;
                case 2 : bp.start();
                    break;
                case 3: mp.start();
                    break;
                case 4: System.exit(0);
            }
        }while(true);
    }

}


