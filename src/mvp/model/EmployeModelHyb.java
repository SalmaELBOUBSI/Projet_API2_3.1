package mvp.model;

import Entreprise.Bureau;
import Entreprise.Employe;
import Entreprise.Message;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeModelHyb extends EmployeModelDB {
    //private static final Logger logger = LogManager.getLogger(EmployeModelHyb.class);
    public EmployeModelHyb(){
        super();
    }
    protected Connection dbConnect;

    @Override
    public Employe readEmploye(int id_employe){
        //TODO faire une nouvelle requete API_EMPLOYE_MESSAGE_PJR2
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
                List<Message> lmsg = new ArrayList<>();
                int numMsg = rs.getInt(6);
                if(numMsg !=0){
                    do{
                        numMsg = rs.getInt(6);
                        String objet = rs.getString(7);
                        String contenu = rs.getString(8);
                        Date date = rs.getDate(9);
                        LocalDate dateEnvoi = date!=null?date.toLocalDate():null;
                        Message msg = new Message(numMsg,objet,contenu,dateEnvoi);
                        msg.setDateEnvoi(dateEnvoi);
                        lmsg.add(msg);
                    }while(rs.next());
                }

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
    public List<Employe> getEmployes(){
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

    public List<Message> message_envoye(Employe employe) {
        return employe.message_envoye();
    }

    @Override
    public List<Message> message_recu(Employe employe) {
        return employe.message_recu();
    }

    @Override
    public List<Message> message_non_lu(Employe employe) {
        return employe.message_non_lu();
    }
}
