package controller;

import http.Controller;
import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "CategoryServlet", value = "/categories/*")
public class CategoryServlet extends Controller  {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = getPath(request);
        CategoryDao cd= new CategoryDao();
        List<Category> categories= new ArrayList<>();
        switch (path) {
            case "/":
                try {
                    categories = cd.doRetrieveAll();
                    request.setAttribute("categories",categories);

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                request.getRequestDispatcher(view("crm/categories")).forward(request,response);
                break;

            case "/update":
                request.getRequestDispatcher(view("category/update")).forward(request,response);
                break;


            case "/create":
                request.getRequestDispatcher(view("category/create")).forward(request,response);
                break;

            case "/show-by-cat":{
                int id=Integer.parseInt(request.getParameter("id"));
                ProductDao pd= new ProductDao();
                ArrayList<Product> list= new ArrayList<>();
                list= (ArrayList<Product>) pd.doRetrievebyCat(id);
                request.setAttribute("products",list);
                request.getRequestDispatcher(view("category/show-by-cat")).forward(request,response);

            }

            case"/test":{
                cd= new CategoryDao();
                categories= new ArrayList<>();
                try {JSONObject root= new JSONObject();
                    categories=cd.doRetrieveAll();
                    JSONArray array= new JSONArray();
                    root.put("categories",array);
                    categories.forEach(category -> array.put(category.toJson()));

                    sendJson(response,root);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            }


        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path=getPath(request);
        CategoryDao cd= new CategoryDao();
        switch (path) {
        case "/update":
        Category c= new Category();
        int id=Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
            c.setId(id);
            c.setNome(request.getParameter("name"));
            String s="Modifica Categoria";
            request.setAttribute("lbl",s);
            if(cd.doUpdate(c)){
                Esito e= new Esito(true,"MODIFICA CATEGORIA EFFETTUATA CON SUCCESSO!","update");
                request.setAttribute("esito",e);
                request.getRequestDispatcher("/WEB-INF/view/category/alert.jsp").forward(request,response);

            }else{
                Esito e= new Esito(false,"ERRORE MODIFICA CATEGORIA!","update");
                request.setAttribute("esito",e);
                request.getRequestDispatcher("/WEB-INF/view/category/alert.jsp").forward(request,response);
            }
            break;

            case "/create":
                 c= new Category();
                c.setId(Integer.parseInt(request.getParameter("id")));
                c.setNome(request.getParameter("name"));
                 s="Creazione Categoria";
                request.setAttribute("lbl",s);
                if(cd.createCategory(c)){
                    Esito e= new Esito(true,"CREAZIONE CATEGORIA EFFETTUATA CON SUCCESSO!","create");
                    request.setAttribute("esito",e);

                    request.getRequestDispatcher("/WEB-INF/view/category/alert.jsp").forward(request,response);

                }else{
                    Esito e= new Esito(false,"ERRORE CREAZIONE CATEGORIA!","create");
                    request.setAttribute("esito",e);
                    request.getRequestDispatcher("/WEB-INF/view/category/alert.jsp").forward(request,response);
                }
                break;







            }


                        }
    }



