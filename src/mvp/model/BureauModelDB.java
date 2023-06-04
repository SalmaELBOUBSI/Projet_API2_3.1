package mvp.model;

import Entreprise.Bureau;
import myconnections.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BureauModelDB implements DAOBureau {

    protected Connection dbConnect;

    public BureauModelDB(){
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");
            System.exit(1);
        }
        System.out.println("connexion etablie");
        //logger.error("connexion etablie");
    }

    @Override
    public Bureau addBureau(Bureau bureau) {
        String query1 = "insert into API_BUREAU_PJR(sigle,tel) values(?,?)";
        String query2 = "select id_bureau from API_BUREAU_PJR where sigle=? and tel=?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
        ){
            pstm1.setString(1,bureau.getSigle());
            pstm1.setString(2,bureau.getTel());
            int n = pstm1.executeUpdate();
                if(n==1) {
                    pstm2.setString(1, bureau.getSigle());
                    pstm2.setString(2, bureau.getTel());
                    ResultSet rs = pstm2.executeQuery();
                    if (rs.next()) {
                        int id_bureau = rs.getInt(1);
                        bureau.setId(id_bureau);
                        return bureau;
                    } else {
                        System.err.println("record introuvable");
                        return null;
                    }
                }
                else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            //logger.error("erreur SQL : "+e);
            return null;
        }
    }

    @Override
    public boolean removeBureau(Bureau bureau) {
        String query = "delete from API_BUREAU_PJR where id_bureau = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,bureau.getId());
            int n = pstm.executeUpdate();
            if(n!=0) return true;
            else return false;

        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            //logger.error("erreur SQL : "+e);
            return false;
        }
    }

    @Override
    public Bureau updateBureau(Bureau bureau) {
        String query = "update API_BUREAU_PJR set sigle=?,tel=? where id_bureau = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1,bureau.getSigle());
            pstm.setString(2,bureau.getTel());
            pstm.setInt(3, bureau.getId());
            int n = pstm.executeUpdate();
            if(n!=0) return readBureau(bureau.getId());
            else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            //logger.error("erreur SQL : "+e);
            return null;
        }
    }

    @Override
    public Bureau readBureau(int id_bureau) {
        String query = "select * from API_BUREAU_PJR where id_bureau = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,id_bureau);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String sigle = rs.getString(2);
                String tel = rs.getString(3);
                Bureau br = new Bureau(id_bureau,sigle,tel);
                return  br;

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
    public List<Bureau> getBureaux() {
        List<Bureau> lbr = new ArrayList<>();
        String query="select * from API_BUREAU_PJR";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int id_bureau = rs.getInt(1);
                String sigle= rs.getString(2);
                String tel = rs.getString(3);
                Bureau br = new Bureau(id_bureau,sigle,tel);
                lbr.add(br);
            }
            return lbr;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            //logger.error("erreur SQL : "+e);
            return null;
        }
    }
}
