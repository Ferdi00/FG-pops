package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    public ArrayList<Category> doRetrieveAll() throws SQLException {
        ArrayList<Category> categories = new ArrayList<>();
        Statement st;
        ResultSet rs;
        Category c;
        try (Connection con = ConPool.getConnection()) {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM categoria WHERE 1=1");
            while (rs.next()) {
                c = new Category();
                c.setId(rs.getInt(1));
                c.setNome(rs.getString(2));
                categories.add(c);
            }
            con.close();
            return categories;
        }
    }

    public boolean doUpdate(Category c) {
            Statement st;
            ResultSet rs;

            try (Connection con = ConPool.getConnection()) {
                st = con.createStatement();
                String s = "UPDATE categoria SET id = '"+c.getId()+"', nome = '"+c.getNome()+"' WHERE id = '"+c.getId()+"'";
                st.executeUpdate(s);
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return false;

    }


    public boolean createCategory(Category c) {
            try (Connection con = ConPool.getConnection()) {
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO categoria (id , nome) VALUES(?,?)",
                        Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, c.getId());
                ps.setString(2, c.getNome());

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

}
