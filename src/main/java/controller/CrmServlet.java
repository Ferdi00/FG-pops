package controller;

import http.Controller;
import model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "CrmServlet", value = "/crm/*")
public class CrmServlet extends Controller {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = getPath(request);

    switch (path){

        case "/home":
            request.getRequestDispatcher(view("site/home-products")).forward(request,response);

            break;


        case "/dashboard":
            Account a= (Account) request.getAttribute("session");
            HttpSession session=request.getSession();
            if(session!=null)
            if(a==null){
                request.getRequestDispatcher(view("crm/secret")).forward(request,response);
            }

            if(a.isIsadmin()==true){
            request.getRequestDispatcher(view("crm/home")).forward(request,response);}
            else
                request.getRequestDispatcher(view("crm/secret")).forward(request,response);
            break;
        default:
            response.sendError(HttpServletResponse.SC_NOT_FOUND,"risorsa non trovata");

        case"/isAdmin":
             session=request.getSession();
            if(session!=null){
                request.getRequestDispatcher(view("crm/home")).forward(request,response);
            }

        case"/category":
            session=request.getSession();
             a= (Account) session.getAttribute("session");
            session.setAttribute("session",a);
            int id=Integer.parseInt(request.getParameter("categoryId"));
            ProductDao pd=new ProductDao();
            ArrayList<Product> list=new ArrayList<>();
            if(id==0){
                request.getRequestDispatcher(view("site/home-products")).forward(request,response);
            }
            else
            list= (ArrayList<Product>) pd.doRetrievebyCat(id);
            request.setAttribute("Products",list);
            request.getRequestDispatcher(view("site/category-products")).forward(request,response);
    break;

        case "/ordini":
            System.out.println("sto in ordini");
            OrdineDao od=new OrdineDao();
            ArrayList<Ordine> listord=od.doRetrieveAll();
            request.setAttribute("ordini",listord);
            request.getRequestDispatcher(view("crm/ordini")).forward(request,response);
    }




    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = getPath(request);

        switch (path){
            case "/show":

                HttpSession session=request.getSession();
                Account a= (Account) session.getAttribute("session");
                session.setAttribute("session",a);
                String nome=request.getParameter("name");
                ProductDao pd= new ProductDao();
                Product p=pd.doRetrievebyName(nome);
                request.setAttribute("product",p);
                request.getRequestDispatcher(view("site/show")).forward(request,response);
                break;

        }
    }




}
