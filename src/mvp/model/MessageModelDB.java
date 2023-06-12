package mvp.model;

import Entreprise.Employe;
import Entreprise.Message;
import myconnections.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MessageModelDB implements DAOMessage {
    //private static final Logger logger = LogManager.getLogger(MessageModelDB.class);
    protected Connection dbConnect;

    public MessageModelDB() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");
            //logger.error("erreur de connexion");
            System.exit(1);
        }
        System.out.println("connexion établie");
        //logger.info("connexion établie");
    }

    @Override
    public Message addMessage(Message message) {

        String query1 = "insert into API_MESSAGE_PJR(objet,contenu,dateenvoi,id_employe) values(?,?,?,?)";
        String query2 = "select id_message from API_MESSAGE_PJR where id_employe=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setString(1, message.getObjet());
            pstm1.setString(2, message.getContenu());
            pstm1.setObject(3, message.getDateEnvoi());
            pstm1.setInt(4, message.getEmetteur().getId());

            int n = pstm1.executeUpdate();
            if (n == 1) {
                pstm2.setInt(1, message.getEmetteur().getId());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int id_message = rs.getInt(1);
                    message.setId(id_message);
                    return message;
                } else {
                    //logger.error("record introuvable");
                    System.err.println("record introuvable");
                    return null;
                }
            } else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            //logger.error("erreur sql :"+e);
            return null;
        }
    }

@Override
public  boolean deleteMessageTrois(){

    String query ="delete from API_MESSAGE_PJR where dateenvois<= CURRENT_DATE-3 in (select * from API_INFO_PJR where datelecture is null )";
    try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
        int n = pstm.executeUpdate();
        if(n!=0) return true;
        else return false;

    } catch (SQLException e) {
        System.err.println("erreur sql :"+e);
        return false;
    }
}

    @Override
    public boolean removeMessage(Message message) {
        String query ="delete from API_MESSAGE_PJR where id_message=?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,message.getId());
            int n = pstm.executeUpdate();
            if(n!=0) return true;
            else return false;

        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            //logger.error("erreur d'effacement : "+e);
            return false;
        }
    }

    @Override
    public Message updateMessage(Message message) {
        String query = "update API_MESSAGE_PJR set objet=?, contenu=? ,id_employe=? where id_message=?";
        try(PreparedStatement pstm= dbConnect.prepareStatement(query)){
            pstm.setString(1,message.getObjet());
            pstm.setString(2,message.getContenu());
            //pstm.setObject(3, message.getDateEnvoi());
            //pstm.setDate(3,message.getDateEnvoi()!=null?Date.valueOf(message.getDateEnvoi()):null);
            pstm.setInt(3,message.getEmetteur().getId());
            pstm.setInt(4,message.getId());

            int n = pstm.executeUpdate();
            if(n!=0) return readMessage(message.getId());
            else return null;
        }catch (SQLException e){
            System.err.println("erreur sql :" + e);
            //logger.error("erreur d'update : "+e);
            return null;
        }
    }

    @Override
    public Message readMessage(int id_message) {
        String query ="select * from API_MESSAGE_PJR where id_message=?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,id_message);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String objet = rs.getString(2);
                String contenu = rs.getString(3);
                Date date = rs.getDate(4);
                LocalDate dateenvoi = date!=null?date.toLocalDate():null;
                int emetteur = rs.getInt(5);
                Message msg= new Message(id_message,objet,contenu,dateenvoi,new Employe(emetteur));
                return  msg;

            }
            else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            //logger.error("erreur SQL : "+e);
            return null;
        }
    }

    @Override
    public List<Message> getMessages() {
        List<Message> lmsg = new ArrayList<>();
        String query ="select * from API_MESSAGE_PJR";

        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int id_message = rs.getInt(1);
                String objet = rs.getString(2);
                String contenu = rs.getString(3);
                Date date = rs.getDate(4);
                LocalDate dateenvoi = date!=null?date.toLocalDate():null;
                int emetteur = rs.getInt(5);
                Message msg = new Message(id_message,objet,contenu,dateenvoi,new Employe(emetteur));
                lmsg.add(msg);
            }
            return lmsg;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            //logger.error("erreur SQL : "+e);
            return null;
        }
    }
}
