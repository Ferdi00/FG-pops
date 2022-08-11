package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {


    public boolean createProduct (Product p) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO prodotto (id , nome, descrizione, prezzo , filename,totale) VALUES(?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, p.getId());
            ps.setString(2, p.getName());
            ps.setString(3, p.getDescription());
            ps.setDouble(4,p.getPrice());
            ps.setString(5,p.getCover());
            ps.setInt(6,p.getTotal());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
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
           ResultSet rs= st.executeQuery("select count(id) as totale from prodotto ");
            while (rs.next())
               n=rs.getInt("totale");
            return n;
        }
}

    public List<Product> doRetrieveAll() throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            ArrayList<Product> pl= new ArrayList<>();
          Statement  st = con.createStatement();
           ResultSet rs = st.executeQuery("SELECT * FROM prodotto WHERE 1=1");
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setPrice(rs.getDouble(4));
                p.setCover(rs.getString(5));
                p.setTotal(rs.getInt(6));
                pl.add(p);
            }
            return pl;

    }
}


    public boolean doUpdate(Product p) {
        Statement st;
        ResultSet rs;

        try (Connection con = ConPool.getConnection()) {
            st = con.createStatement();
            String s = "UPDATE prodotto SET id = '"+p.getId()+"', nome = '"+p.getName()+"', descrizione = '"+p.getDescription()+"', prezzo = '"+p.getPrice()+"', filename = '"+p.getCover()+"', totale = '"+p.getTotal()+"' WHERE id = '"+p.getId()+"'";
            st.executeUpdate(s);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public void addCategory(Product p ,Category c){
        Statement st;
        ResultSet rs;

        try (Connection con = ConPool.getConnection()) {
            st=con.createStatement();
             String s="insert into appartenere values ("+p.getId()+","+c.getId()+")";
            st.executeUpdate(s);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void updateCategory(Product p ,Category c){
        Statement st;
        ResultSet rs;

        try (Connection con = ConPool.getConnection()) {
            st=con.createStatement();
            String s="update appartenere SET id_prod = '"+p.getId()+"', id_cat = '"+c.getId()+"' WHERE id_prod = '"+p.getId()+"'";
            st.executeUpdate(s);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public List<Product> doRetrievebyCat(int id){

        ArrayList<Product> products = new ArrayList<>();
        Statement st;
        ResultSet rs;
        Product p;
        try (Connection con = ConPool.getConnection()) {
            st = con.createStatement();
            String s="SELECT prodotto.id,prodotto.nome,prodotto.descrizione,prodotto.prezzo,prodotto.filename,prodotto.totale FROM  appartenere,prodotto,categoria WHERE  appartenere.id_prod=prodotto.id and appartenere.id_cat=categoria.id and categoria.id="+id+"";
            rs = st.executeQuery(s);
            while(rs.next()) {
                p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("nome"));
                p.setDescription(rs.getString("descrizione"));
                p.setPrice(rs.getDouble("prezzo"));
                p.setCover(rs.getString("filename"));
                p.setTotal(rs.getInt("totale"));
                products.add(p);
            }
            return products;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Product doRetrievebyName(String name){
        Statement st;
        ResultSet rs;
        Product p=new Product();
        try (Connection con = ConPool.getConnection()) {
            st = con.createStatement();
            String s="SELECT * FROM prodotto WHERE nome = '"+name+"'";
            rs = st.executeQuery(s);
            while(rs.next()) {
                p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("nome"));
                p.setDescription(rs.getString("descrizione"));
                p.setPrice(rs.getDouble("prezzo"));
                p.setCover(rs.getString("filename"));
                p.setTotal(rs.getInt("totale"));
            }
            return p;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Product doRetrievebyId(int id){
        Statement st;
        ResultSet rs;
        Product p=new Product();
        try (Connection con = ConPool.getConnection()) {
            st = con.createStatement();
            String s="SELECT * FROM prodotto WHERE id = '"+id+"'";
            rs = st.executeQuery(s);
            while(rs.next()) {
                p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("nome"));
                p.setDescription(rs.getString("descrizione"));
                p.setPrice(rs.getDouble("prezzo"));
                p.setCover(rs.getString("filename"));
                p.setTotal(rs.getInt("totale"));
            }
            return p;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
