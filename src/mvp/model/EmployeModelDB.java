package mvp.model;

import Entreprise.Bureau;
import Entreprise.Employe;
import Entreprise.Message;
import myconnections.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeModelDB implements DAOEmploye,EmployeSpecial {
    //private static final Logger logger = LogManager.getLogger(EmployeModelDB.class);
    private Connection dbConnect;

    public EmployeModelDB(){
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.out.println("erreur de connexion");
            //logger.error("erreur de connexion");
            System.exit(1);
        }
        System.out.println("connexion etablie");
        //logger.info("connexion établie");
    }

    @Override
    public Employe addEmploye(Employe employe) {
       String query3 ="select * from API_BUREAU_PJR";

        try(PreparedStatement pstmBureau = dbConnect.prepareStatement(query3)){
            ResultSet rsBureau = pstmBureau.executeQuery();
            while (rsBureau.next()) {
                int idBureau = rsBureau.getInt("id_bureau");
                String sigle = rsBureau.getString("sigle");
            }

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
            return null;
        }


        String query1 = "insert into API_EMPLOYE_PJR(mail,nom,prenom,id_bureau) values (?,?,?,?)";
        String query2 = "select id_employe from API_EMPLOYE_PJR where mail=? and nom =? and prenom=? and id_bureau=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setString(1, employe.getMail());
            pstm1.setString(2, employe.getNom());
            pstm1.setString(3, employe.getPrenom());
            pstm1.setInt(4, employe.getBureau().getId());
            int n = pstm1.executeUpdate();
            if (n == 1) {
                pstm2.setString(1, employe.getMail());
                pstm2.setString(2, employe.getNom());
                pstm2.setString(3, employe.getPrenom());
                pstm2.setInt(4, employe.getBureau().getId());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    employe.setId(id);
                    return employe;


                } else {
                    System.out.println("record introuvable");
                    //logger.error("record introuvable");
                    return null;
                }
            } else return null;

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
            // logger.error("erreur sql :"+e);
            return null;
        }
    }

    @Override
    public boolean removeEmploye(Employe employe) {
        String query = "delete from API_EMPLOYE_PJR where id_employe = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, employe.getId());
            int n = pstm.executeUpdate();
            if (n != 0) return true;
            else return false;

        } catch (SQLException e) {
            System.out.println("erreur d'effacement : "+e);
            // logger.error("erreur d'effacement : "+e);
            return false;
        }
    }

    @Override
    public Employe updateEmploye(Employe employe) {

        String query = "update API_EMPLOYE_PJR set mail=?,nom=?,prenom=? where id_employe=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, employe.getMail());
            pstm.setString(2, employe.getNom());
            pstm.setString(3, employe.getPrenom());
            //pstm.setInt(4, employe.getBureau().getId());
            pstm.setInt(4, employe.getId());

            int n = pstm.executeUpdate();
            if (n != 0) return readEmploye(employe.getId());
            else return null;
        } catch (SQLException e) {
            System.out.println("erreur d'update : "+e);
            // logger.error("erreur d'update : "+e);
            return null;
        }
    }

    @Override
    public Employe readEmploye(int id_employe) {
        String query = "select * from API_EMPLOYE_PJR where id_employe = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, id_employe);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String mail = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                int id_bureau = rs.getInt(5);
                Employe emp = new Employe(id_employe, mail, nom, prenom, new Bureau(id_bureau));
                return emp;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
            //logger.error("erreur SQL : "+e);
            return null;
        }
    }

    @Override
    public List<Employe> getEmployes() {
        List<Employe> le = new ArrayList<>();
        String query="select * from API_EMPLOYE_PJR";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int id_employe = rs.getInt(1);
                String mail =rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                int id_bureau = rs.getInt(5);
                Employe emp = new Employe(id_employe,mail,nom,prenom,new Bureau(id_bureau));
                le.add(emp);
            }
            return le;

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
            //logger.error("erreur SQL : "+e);
            return null;
        }
    }

    @Override
    public List<Message> message_envoye(Employe employe) {

        String query = "select * from API_MESSAGE_PJR where id_employe =?";
        return rechercheMessage(employe,query);
    }

    @Override
    public List<Message> message_recu(Employe employe) {

        String query ="SELECT distinct m.id_message,m.objet,m.contenu,m.dateenvoi" +
                " FROM API_MESSAGE_PJR m\n" +
                "JOIN API_INFO_PJR i ON m.id_message=i.id_message\n" +
                "JOIN API_EMPLOYE_PJR e ON i.id_employe = ?";
       return rechercheMessage(employe,query);
    }

    @Override
    public List<Message> message_non_lu(Employe employe) {

        String query = "SELECT distinct m.id_message,m.objet,m.contenu,m.dateenvoi" +
                " FROM API_MESSAGE_PJR m\n" +
                "JOIN API_INFO_PJR i ON m.id_message=i.id_message\n" +
                "JOIN API_EMPLOYE_PJR e ON i.id_employe = ?\n" +
                "WHERE i.datelecture is NULL";
        return rechercheMessage(employe,query);
    }

    private List<Message> rechercheMessage(Employe employe, String query) {
        List<Message> lmsg= new ArrayList<>();
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
                msg.setDateEnvoi(dateEnvoie);
                lmsg.add(msg);
            }
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
            return null;
        }
        return lmsg;

    }
}
