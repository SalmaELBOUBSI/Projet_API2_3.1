package designpatterns.composite;

public class Entreprise {

    public static void main(String[] args){
        Employe e1 = new Employe(1,"louis@API1.com","Louis","Marc");
        Employe e2 = new Employe(2,"Jean@API1.com","Jean","Patrick");
        Employe e3 = new Employe(3,"Marie@API1.com","Marie","Louise");
        Employe e4 = new Employe(4,"Marc@API1.com","Marc","Clyde");
        Categorie c1 = new Categorie(1,"JAVA");
        Categorie c2 = new Categorie(2,"WEB");
        Categorie c3 = new Categorie(3,"C#");

        c1.getElts().add(e1);
        c1.getElts().add(e2);
        c2.getElts().add(e3);
        c3.getElts().add(e4);

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
    }
}
