package mvp.view;

import Entreprise.Bureau;
import mvp.presenter.BureauPresenter;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class BureauViewConsole implements BureauViewInterface{
    private BureauPresenter presenter;
    private List<Bureau> lbr;
    private Scanner sc = new Scanner(System.in);

    public BureauViewConsole(){}

    @Override
    public void setPresenter(BureauPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setListDatas(List<Bureau> bureaux){
        this.lbr=bureaux;
        affList(lbr);
        menu();
    }

    @Override
    public void affMsg(String msg) {
        System.out.println("information:" +msg);
    }

    @Override
    public void affList(List infos) {
        affListe(infos);
    }

    public void menu(){
        do{
            int ch = choixListe(Arrays.asList("ajout", "retrait", "rechercher", "modifier", "fin"));

            switch(ch){
                case 1: ajouter();
                    break;
                case 2 : retirer();
                    break;
                case 3: rechercher();
                    break;
                case 4 : modifier();
                    break;
                case 5 : return;
            }
        }while(true);
    }

    private void modifier() {
        affList(lbr);
        int nbr = choixElt(lbr);
        Bureau br = lbr.get(nbr-1);
        String sigle = modifyIfNotBlank("Sigle du bureau ",br.getSigle());
        String tel = modifyIfNotBlank("Numero de telephone ",br.getTel());
        presenter.updateBureau(new Bureau(br.getId(),sigle,tel));
        lbr=presenter.getAll();
        affList(lbr);
    }

    private void rechercher() {
        System.out.println("id_bureau : ");
        int id_bureau = sc.nextInt();
        presenter.search(id_bureau);
        affList(lbr);
    }

    private void retirer() {
        affList(lbr);
        int nbr = choixElt(lbr);
        Bureau br = lbr.get(nbr-1);
        presenter.removeBureau(br);
        lbr=presenter.getAll();
        affListe(lbr);
    }

    private void ajouter() {
        System.out.println("Sigle : ");
        String sigle = sc.nextLine();
        System.out.println("Numero de telephone : ");
        String tel = sc.nextLine();
        presenter.addBureau(new Bureau(0,sigle,tel));
        lbr=presenter.getAll();
        affList(lbr);
    }

    @Override
    public Bureau selectionner(List<Bureau> lbr){
        int nbr =  choixListe(lbr);
        Bureau br = lbr.get(nbr-1);
        return br;
    }
}
