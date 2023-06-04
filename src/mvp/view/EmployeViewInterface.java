package mvp.view;

import Entreprise.Employe;
import Entreprise.Message;
import mvp.presenter.EmployePresenter;

import java.util.List;

public interface EmployeViewInterface {
    public void setPresenter(EmployePresenter presenter);

    public void setListDatas(List<Employe> employes);

    public void affMsg(String msg);

    Employe selectionner(List<Employe> lemp);

    void affList(List infos);
}
