package designpatterns.builder;

import java.time.LocalDate;
import java.util.List;

public class Entreprise {
    public static void main(String[] args)  {

        Employe destinataire1 = new Employe(2, "Jean", "Dupont");
        Employe destinataire2 = new Employe(3, "Sophie", "Martin");

        Infos infos1 = new Infos(destinataire1, LocalDate.parse("2022-10-11"));
        Infos infos2 = new Infos(destinataire2, LocalDate.parse("2022-10-12"));
        try{
            Message msg1 = new Message.MessageBuilder().
                    setId(1).
                    setObjet("Cours").
                    setContenu("Commencement des cours 10h00").
                    setDateEnvoi(LocalDate.parse("2022-10-10")).
                    setInfos((List<Infos>) infos1).
                    setInfos((List<Infos>) infos2).
                    build();
        }catch (Exception e){
            System.out.println("Erreur: " + e);
        }

        try{
            Message msg2 = new Message.MessageBuilder().
                    setId(1).
                    setObjet("Cours").
                    setContenu("Commencement des cours 10h00").
                    build();
        }catch (Exception e){
            System.out.println("Erreur: " + e);
        }
    }

}
