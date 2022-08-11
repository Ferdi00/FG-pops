package controller;

import http.Controller;
import model.Category;
import model.Esito;
import model.Product;
import model.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products/*")
@MultipartConfig
public class ProductServlet extends Controller  {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path=getPath(request);
        ProductDao pd= new ProductDao();
        switch (path){
            case "/":
                List<Product> products= null;
                try {
                    products = pd.doRetrieveAll();
                    request.setAttribute("products",products);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                request.getRequestDispatcher(view("crm/products")).forward(request,response);
                break;
            case "/create":
                request.getRequestDispatcher(view("product/create")).forward(request,response);
                break;

            case "/update":
                request.getRequestDispatcher(view("product/update")).forward(request,response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path=getPath(request);
        switch (path) {
            case "/create":
                ProductDao pd =new ProductDao();
                Product p=new Product();
                int id=Integer.parseInt(request.getParameter("id"));
                p.setId(id);
                p.setName(request.getParameter("name"));
                p.setPrice(Double.parseDouble(request.getParameter("price")));
                p.setDescription(request.getParameter("desc"));
                Part filePart=request.getPart("cover");
                String filename= Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                p.setCover(filename);
                p.setCategory(Integer.parseInt(request.getParameter("category")));
                p.setTotal(Integer.parseInt(request.getParameter("totale")));
                Category c= new Category();
                c.setId(Integer.parseInt(request.getParameter("category")));
                String s="Crea nuovo prodotto";
                request.setAttribute("lbl",s);
                if(pd.createProduct(p)){
                    pd.addCategory(p,c);
                    Esito e=new Esito(true,"PRODOTTO CREATO CORRETTAMENTE!","create");
                    request.setAttribute("esito",e);
                    request.getRequestDispatcher("/WEB-INF/view/product/alert.jsp").forward(request,response);
                String uploadRoot = getUploadPath();
                try(InputStream filestream = filePart.getInputStream()) {
                    File file = new File(uploadRoot + filename);
                    if(Files.notExists(file.toPath())){
                        Files.copy(filestream,file.toPath());}
                }
                }else{
                    Esito e=new Esito(true,"ERRORE CREAZIONE PRODOTTO!","create");
                    request.setAttribute("esito",e);
                    request.getRequestDispatcher("/WEB-INF/view/product/alert.jsp").forward(request,response);
                }
                break;

            case "/update":
                p=new Product();
                id=Integer.parseInt(request.getParameter("id"));
                p.setId(id);
                p.setName(request.getParameter("name"));
                p.setPrice(Double.parseDouble(request.getParameter("price")));
                p.setDescription(request.getParameter("desc"));
                filePart=request.getPart("cover");
                filename= Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                p.setCover(filename);
                p.setCategory(Integer.parseInt(request.getParameter("category")));
                p.setTotal(Integer.parseInt(request.getParameter("totale")));
                c= new Category();
                c.setId(Integer.parseInt(request.getParameter("category")));
                ProductDao productdao= new ProductDao();
                s="Modifica prodotto";
                request.setAttribute("lbl",s);
                if(productdao.doUpdate(p)){
                    productdao.updateCategory(p,c);
                    Esito e=new Esito(true,"PRODOTTO MODIFICATO CORRETTAMENTE!","update");
                    request.setAttribute("esito",e);

                    String uploadRoot = getUploadPath();
                    try(InputStream filestream = filePart.getInputStream()) {
                        File file = new File(uploadRoot + filename);
                        if(Files.notExists(file.toPath())){
                        Files.copy(filestream,file.toPath());}
                    }
                    request.getRequestDispatcher("/WEB-INF/view/product/alert.jsp").forward(request,response);
                }else{

                    Esito e= new Esito(false,"ERRORE MODIFICA PRODOTTO!","update");
                    request.setAttribute("esito",e);
                    request.getRequestDispatcher("/WEB-INF/view/category/alert.jsp").forward(request,response);
                }
                break;



        }


    }
    private int id;
    private Part filepart;
    String filename;

}
