package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class AccountDao {




    public boolean findAccount(String email)throws SQLException{
        ResultSet rs;
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM utente WHERE email=?");
            ps.setString(1,email);
            rs = ps.executeQuery();
            if(rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }




    public  boolean createAccount(Account a){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO utente (nome, cognome,email, pw,telefono,indirizzo,isadmin) VALUES(?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, a.getFirstname());
            ps.setString(2,a.getLastname());
            ps.setString(3, a.getEmail());
            ps.setString(4,a.getPassword());
            ps.setString(5,a.getTelephone());
            ps.setString(6,a.getAddress());
            ps.setBoolean(7,a.isIsadmin());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            a.setId(id);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;



    }

    public Account  doRetrieveByEmailPassword(String email,String password) {
        Statement st;
        ResultSet rs;
        PreparedStatement ps;
        Account ac=new Account();
        try (Connection con = ConPool.getConnection()) {
            ps=con.prepareStatement("SELECT * FROM utente WHERE email=? AND pw=?");
            ps.setString(1,email);
            ps.setString(2,password);

            rs = ps.executeQuery();
            while(rs.next()) {
                ac=new Account();
               ac.setFirstname(rs.getString(2));
               ac.setLastname(rs.getString(3));
               ac.setEmail(rs.getString(4));
               ac.setTelephone(rs.getString(6));
               ac.setAddress(rs.getString(7));
               ac.setIsadmin(rs.getBoolean(8));
            }
            return ac;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public ArrayList<Account>  doRetrieveAll() {
        Statement st;
        ResultSet rs;
        PreparedStatement ps;
        Account ac=new Account();
        ArrayList<Account> list=new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            ps=con.prepareStatement("SELECT * FROM utente ");
            rs = ps.executeQuery();
            while(rs.next()) {
                ac=new Account();
                ac.setFirstname(rs.getString(2));
                ac.setLastname(rs.getString(3));
                ac.setEmail(rs.getString(4));
                ac.setTelephone(rs.getString(6));
                ac.setAddress(rs.getString(7));
                ac.setIsadmin(rs.getBoolean(8));
                list.add(ac);
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public int countAll() throws SQLException {
        int n = 0;
        try (Connection con = ConPool.getConnection()) {
            Statement st= con.createStatement();
            ResultSet rs= st.executeQuery("select count(id_utente) as totale from utente ");
            while (rs.next())
                n=rs.getInt("totale");
            return n;
        }
    }

    }


