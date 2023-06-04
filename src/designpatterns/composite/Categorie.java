package designpatterns.composite;

import java.util.HashSet;
import java.util.Set;

public class Categorie extends Element{

    private String nom;
    private Set<Element> elts = new HashSet<>();

    public Categorie(int id,String nom){
        super(id);
        this.nom=nom;
    }

    public Set<Element> getElts() {
        return elts;
    }

    @Override
    public int empTot(){
        int somme=0;
        for (Element pc:elts){
            somme+=pc.empTot();
        }

        return somme;
    }

    @Override
    public String toString() {
        StringBuilder aff= new StringBuilder(getId()+" "+nom+"\n");

        for(Element e:elts){
            aff.append(e+"\n");
        }
        return aff+nom +" ->Total employé :  "+empTot()+"\n";
    }
}
