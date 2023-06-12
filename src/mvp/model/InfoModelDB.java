package mvp.model;

import Entreprise.Infos;
import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InfoModelDB implements DAOInfo {
    protected Connection dbConnect;
    public InfoModelDB() {
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
    public Infos addInfo(Infos infos) {
        String query3 = "select * from API_MESSAGE_PJR";


        String query1 = "insert into API_INFO_PJR(id_employe,datelecture) values(?,?)";

        String query2 = "select id_message from API_INFO_PJR where id_employe=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setInt(1, infos.getRecepteur().getId());
            pstm1.setObject(2, infos.getDateLecture());
            int n = pstm1.executeUpdate();
            if (n == 1) {
                pstm2.setInt(1, infos.getRecepteur().getId());
                pstm2.setObject(1, infos.getDateLecture());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int id_message = rs.getInt(1);
                    return infos;
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
}
