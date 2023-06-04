package mvp.presenter;

import Entreprise.Employe;
import Entreprise.Message;
import mvp.model.DAOEmploye;
import mvp.model.EmployeSpecial;
import mvp.view.EmployeViewInterface;

import java.util.List;


public class EmployePresenter {
    private DAOEmploye model;
    private EmployeViewInterface view;

    //private static final Logger logger = LogManager.getLogger(EmployePresenter.class);

    public EmployePresenter(DAOEmploye model,EmployeViewInterface view){
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void start(){
        view.setListDatas(getAll());
    }

    public List<Employe> getAll(){
        return model.getEmployes();
    }

    public void addEmploye(Employe employe){
        Employe emp = model.addEmploye(employe);
        if(emp!=null) view.affMsg("Creation de : " +emp);
        else view.affMsg("erreur de création");
    }

    public void removeEmploye(Employe employe) {
        boolean ok = model.removeEmploye(employe);
        if(ok) view.affMsg("employe effacé");
        else view.affMsg("employe non effacé");
    }

    public Employe selectionner() {
        System.out.println("appel de sélection");
        //logger.info("appel de sélection");
        Employe emp = view.selectionner(model.getEmployes());
        return emp;
    }

    public void update(Employe employe) {

        Employe emp =model.updateEmploye(employe);
        if(emp==null) view.affMsg("mise à jour infrucueuse");
        else view.affMsg("mise à jour effectuée : " +emp);
    }

    public Employe search(int idEmploye) {
        Employe emp = model.readEmploye(idEmploye);
        if(emp==null) view.affMsg("recherche infructueuse");
        else view.affMsg(emp.toString());
        return emp;
    }

    public void message_envoye(Employe employe){
        List<Message> lmsg = ((EmployeSpecial)model).message_envoye(employe);
        if(lmsg==null || lmsg.isEmpty()) view.affMsg("aucun message trouvé");
        else view.affList(lmsg);
    }

    public void message_recu(Employe employe){
        List<Message> lmsg = ((EmployeSpecial)model).message_recu(employe);
        if(lmsg==null || lmsg.isEmpty()) view.affMsg("aucun message trouvé");
        else view.affList(lmsg);
    }
    public void message_non_lu(Employe employe){
        List<Message> lmsg = ((EmployeSpecial)model).message_non_lu(employe);
        if(lmsg==null || lmsg.isEmpty()) view.affMsg("aucun message trouvé");
        else view.affList(lmsg);
    }

}
