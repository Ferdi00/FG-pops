package model;

import java.sql.*;
import java.util.ArrayList;

public class OrdineDao {

    public  ArrayList<Ordine>  doRetrieveAll() {
        Statement st;
        ResultSet rs;
        PreparedStatement ps;
        Ordine od=new Ordine();
        ArrayList<Ordine> list=new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            ps=con.prepareStatement("SELECT * FROM ordine ");
            rs = ps.executeQuery();
            while(rs.next()) {
                od=new Ordine();
                od.setId(rs.getInt(1));
                od.setNome(rs.getString(2));
                od.setCognome(rs.getString(3));
                od.setIndirizzo(rs.getString(4));
                od.setTotale(rs.getDouble(5));
                list.add(od);
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public boolean createOrder (Ordine o) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO ordine ( nome, cognome, indirizzo , totale) VALUES(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, o.getNome());
            ps.setString(2, o.getCognome());
            ps.setString(3,o.getIndirizzo());
            ps.setDouble(4,o.getTotale());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            o.setId(id);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public int countAll() throws SQLException {
        int n = 0;
        try (Connection con = ConPool.getConnection()) {
            Statement st= con.createStatement();
            ResultSet rs= st.executeQuery("select count(id_ordine) as totale from ordine ");
            while (rs.next())
                n=rs.getInt("totale");
            return n;
        }
    }

}
