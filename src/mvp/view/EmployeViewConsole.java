package mvp.view;

import Entreprise.Bureau;
import Entreprise.Employe;
import mvp.presenter.EmployePresenter;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class EmployeViewConsole implements EmployeViewInterface{
    private EmployePresenter presenter;
    private List<Employe> lemp;
    private Scanner sc = new Scanner(System.in);

    public EmployeViewConsole(){

    }

    @Override
    public void setPresenter(EmployePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setListDatas(List<Employe> employes) {
        this.lemp= employes;
        affList(lemp);
        menu();

    }

    @Override
    public void affMsg(String msg) {
        System.out.println("information:" + msg);
    }

    @Override
    public Employe selectionner(List<Employe> lemp) {
        int ne = choixListe(lemp);
        Employe employe = lemp.get(ne-1);
        return employe;
    }

    @Override
    public void affList(List infos) {
        affListe(infos);
    }

    public void menu(){
        do {

            int ch = choixListe(Arrays.asList("ajout", "retrait", "rechercher", "modifier", "special", "fin"));
            switch (ch) {
                case 1:
                    ajouter();
                    break;
                case 2:
                    retirer();
                    break;
                case 3:
                    rechercher();
                    break;
                case 4:
                    modifier();
                    break;
                case 5:
                    special();
                    break;
                case 6:
                    return;
            }
        } while (true);
    }

    private void special() {
        affList(lemp);
        System.out.println("ID : ");
        int el = choixElt(lemp)-1;
        Employe employe = lemp.get(el);
        employe = presenter.search(employe.getId());
        do {
            int ch = choixListe(Arrays.asList("Message envoyé", "Message reçu", "Message non lu","menu principal"));

            switch (ch) {
                case 1:
                    presenter.message_envoye(employe);
                    break;
                case 2:
                    presenter.message_recu(employe);
                    break;
                case 3:
                    presenter.message_non_lu(employe);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("choix invalide recommencez ");

            }
        } while (true);
    }

    private void modifier() {
        affList(lemp);
        int ne =choixElt(lemp)-1;
        Employe employe= lemp.get(ne);
        String mail = modifyIfNotBlank("Mail : ",employe.getMail());
        String nom = modifyIfNotBlank("nom : ",employe.getNom());
        String prenom = modifyIfNotBlank("prenon : ",employe.getPrenom());
        //int id_bureau = Integer.parseInt("Bureau id : ",employe.getBureau().getId());
        presenter.update(new Employe(employe.getId(),mail,nom,prenom));
        lemp =presenter.getAll();
        affList(lemp);
    }

    private void rechercher() {
        System.out.println("id employé : ");
        int id_employe = sc.nextInt();
        presenter.search(id_employe);
        affList(lemp);
    }

    private void retirer() {
        affList(lemp);
        int ne = choixElt(lemp)-1;
        Employe employe = lemp.get(ne);
        presenter.removeEmploye(employe);
        lemp=presenter.getAll();
        affList(lemp);
    }

    private void ajouter() {

        System.out.println("Mail : ");
        String mail = sc.nextLine();
        System.out.println("Nom : ");
        String nom = sc.nextLine();
        System.out.println("Prénom : ");
        String prenom = sc.nextLine();
        System.out.println("Bureau ID : ");
        int bureau_id = sc.nextInt();

        presenter.addEmploye(new Employe(0,mail,nom,prenom,new Bureau(bureau_id)));
        lemp = presenter.getAll();
        affList(lemp);
    }


}
