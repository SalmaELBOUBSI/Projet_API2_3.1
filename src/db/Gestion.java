package db;

import Entreprise.Bureau;
import Entreprise.Employe;
import Entreprise.Message;
import myconnections.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Gestion {
    //private static final Logger logger = LogManager.getLogger(Gestion.class);

    public Gestion(){
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");
            //logger.error("erreur de connexion");
            System.exit(1);
        }
        System.out.println("connexion établie");
        //logger.info("connexion établie");
    }

    public static void main(String[] args) {

        Gestion g = new Gestion();
        g.gestion();
    }
    private Scanner sc = new Scanner(System.in);
    private Connection dbConnect;

    public void gestion(){
        dbConnect = DBConnection.getConnection();
        if(dbConnect == null){
            System.out.println(1);
        }
        System.out.println("connexion établie");

        do{
            System.out.println("1.Ajout\n2.Recherche\n3.Modification\n4.Suppression\n5.Tous\n6.Quitter");
            System.out.println("Choix : ");
            int ch =sc.nextInt();
            switch (ch) {
                case 1:
                    ajout();
                    break;
                case 2:
                    recherche();
                    break;
                case 3:
                    modification();
                    break;
                case 4:
                    suppression();
                    break;
                case 5:
                    tous();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        }while (true);
    }
    public void ajout(){
        sc.nextLine();
        System.out.println("Mail : ");
        String mail = sc.nextLine();
        System.out.println("Nom : ");
        String nom = sc.nextLine();
        System.out.println("Prénom : ");
        String prenom = sc.nextLine();

       String query3 ="select * from API_BUREAU_PJR";

        try(PreparedStatement pstmBureau = dbConnect.prepareStatement(query3)){
            ResultSet rsBureau = pstmBureau.executeQuery();
            System.out.println("Liste des bureaux :");
            while (rsBureau.next()) {
                int idBureau = rsBureau.getInt("id_bureau");
                String sigle = rsBureau.getString("sigle");
                System.out.println(idBureau + "." + sigle);
            }

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }

        System.out.println("Veuillez saisir l'ID du bureau dans lequel vous souhaitez ajouter l'employé :");
        int id_bureau = sc.nextInt();


        String query1="insert into API_EMPLOYE_PJR(mail,nom,prenom,id_bureau) values (?,?,?,?)";
        String query2 ="select id_employe from API_EMPLOYE_PJR where mail=? and nom =? and prenom=? and id_bureau=?";

        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
        ){
            pstm1.setString(1,mail);
            pstm1.setString(2,nom);
            pstm1.setString(3,prenom);
            pstm1.setInt(4,id_bureau);

            int n = pstm1.executeUpdate();
            System.out.println(n+" ligne insérée");
            if(n==1){
                pstm2.setString(1,mail);
                pstm2.setString(2,nom);
                pstm2.setString(3,prenom);
                pstm2.setInt(4,id_bureau);
                ResultSet rs= pstm2.executeQuery();
                if(rs.next()){
                    int id_employe= rs.getInt(1);
                    System.out.println("id_employe = "+id_employe);
                }
                else System.out.println("Record introuvable");
            }

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
    }
    public void recherche(){
        System.out.println("id de l'employé recherché ");
        int idrech = sc.nextInt();
        String query = "select * from API_EMPLOYE_PJR where id_employe = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idrech);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String mail = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                int id_bureau = rs.getInt(5);
                Employe emp = new Employe(idrech,mail,nom,prenom, new Bureau(id_bureau));
                System.out.println("ID : " + idrech + "\nMAIL : " +mail+ "\nNOM : " +nom+ "\nPRENOM : " +prenom+ "\nBUREAU : " +id_bureau);
                opSpeciales(emp);
            }
            else System.out.println("Record introuvable");
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
    }

    public void modification(){
        System.out.println("id de l'employé : ");
        int idrech = sc.nextInt();
        sc.skip("\n");
        System.out.println("nouveau e-mail :  ");
        String nmail = sc.nextLine();
        String query = "update API_EMPLOYE_PJR set mail=? where id_employe = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1,nmail);
            pstm.setInt(2,idrech);
            int n = pstm.executeUpdate();
            if(n!=0) System.out.println(n+ " ligne mise à jour \nNouvel e-mail : " +nmail );
            else System.out.println("Record introuvable");

        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
        }
    }
    public void suppression(){
        System.out.println("id de l'employé à supprimer :  ");
        int idrech = sc.nextInt();
        String query = "delete from API_EMPLOYE_PJR where id_employe = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idrech);
            int n = pstm.executeUpdate();
            if(n!=0) System.out.println(n+ " ligne supprimée \nID EMPLOYE SUPPRIME : " +idrech);
            else System.out.println("Record introuvable");

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
    }
    public void tous(){
        String query="select * from API_EMPLOYE_PJR";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            System.out.println("ALL EMPLOYE : ");
            while(rs.next()){
                int id_employe = rs.getInt(1);
                String mail =rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                int id_bureau = rs.getInt(5);
                System.out.printf("%d.%s (%s %s => %d)\n",id_employe,mail,nom,prenom,id_bureau);
            }

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
    }

    private void opSpeciales(Employe employe) {
        do {
            System.out.println("1.Messages envoyés\n2.Messages reçus\n3.Messages non lus\n4.menu principal");
            System.out.println("choix : ");
            int ch = sc.nextInt();
            sc.skip("\n");
            switch (ch) {
                case 1:
                    message_envoye(employe);
                    break;
                case 2:
                    message_recu(employe);
                    break;
                case 3:
                    message_non_lu(employe);
                    break;
                case 4: return;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);

    }

    private void message_envoye(Employe employe){
        String query = "select * from API_MESSAGE_PJR where id_employe =?";
        rechercheMessage(employe,query);
    }

    private void message_recu(Employe employe){
        String query ="SELECT distinct m.id_message,m.objet,m.contenu,m.dateenvoi" +
                " FROM API_MESSAGE_PJR m\n" +
                "JOIN API_INFO_PJR i ON m.id_message=i.id_message\n" +
                "JOIN API_EMPLOYE_PJR e ON i.id_employe = ?";
        rechercheMessage(employe,query);
    }
    private void message_non_lu(Employe employe){
        String query = "SELECT distinct m.id_message,m.objet,m.contenu,m.dateenvoi" +
                " FROM API_MESSAGE_PJR m\n" +
                "JOIN API_INFO_PJR i ON m.id_message=i.id_message\n" +
                "JOIN API_EMPLOYE_PJR e ON i.id_employe = ?\n" +
                "WHERE i.datelecture is NULL";
        rechercheMessage(employe,query);
    }

    private void rechercheMessage(Employe employe, String query) {
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,employe.getId());
            ResultSet rs = pstm.executeQuery();
            boolean trouve= false;
            while(rs.next()){
                trouve=true;
                int id_message = rs.getInt(1);
                String objet = rs.getString(2);
                String contenu = rs.getString(3);
                LocalDate dateEnvoie = rs.getDate(4)==null? null: rs.getDate(4).toLocalDate();
                Message msg = new Message(id_message,objet,contenu,dateEnvoie,employe) ;
                System.out.println("\nMESSAGE : " +id_message+ "\nOBJET : " +objet+ "\nCONTENU : " +contenu+ "\nDATE ENVOIE : " +dateEnvoie+ "\n");
            }
            if(!trouve) System.out.println("aucune message trouvé");
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
    }

}
