package mvp.presenter;

import Entreprise.Bureau;
import mvp.model.DAOBureau;
import mvp.view.BureauViewInterface;

import java.util.List;

public class BureauPresenter {
    private DAOBureau model;
    private BureauViewInterface view;

    //private static final Logger logger = LogManager.getLogger(BureauPresenter.class);
    private EmployePresenter employepresenter;

    public BureauPresenter(DAOBureau model,BureauViewInterface view){
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void start() {
        view.setListDatas(getAll());
    }

    public List<Bureau> getAll(){
        return model.getBureaux();
    }

    public void addBureau(Bureau bureau){
        Bureau br = model.addBureau(bureau);
        if(br!=null) view.affMsg("création de :" +br);
        else view.affMsg("erreur de création");
    }

    public void removeBureau(Bureau br){
        boolean ok = model.removeBureau(br);
        if(ok) view.affMsg("bureau effacé");
        else view.affMsg("bureau non effacé");
    }

    public Bureau selectionner() {
        System.out.println("appel de sélection");
        //logger.info("appel de sélection");
        Bureau br = view.selectionner(model.getBureaux());
        return br;
    }

    public void updateBureau(Bureau bureau) {
        Bureau br =model.updateBureau(bureau);
        if(br==null) view.affMsg("mise à jour infrucueuse");
        else view.affMsg("mise à jour effectuée : " +br);
    }

    public Bureau search(int id_bureau) {
        Bureau br = model.readBureau(id_bureau);
        if(br==null) view.affMsg("recherche infructueuse");
        else view.affMsg(br.toString());
        return br;
    }

    public void setEmployePresenter(EmployePresenter employePresenter) {this.employepresenter = employePresenter;}
}
