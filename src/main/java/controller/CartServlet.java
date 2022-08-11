package controller;

import http.Controller;
import model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@WebServlet(name = "CartServlet", value = "/cart/*")
public class CartServlet extends Controller {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = getPath(request);
        switch (path) {

            case "/show":
                HttpSession session = request.getSession();
                Account a = (Account) session.getAttribute("session");
                session.setAttribute("account", a);
                Cart cart = (Cart) session.getAttribute("Cart");
                session.setAttribute("Cart",cart);
                request.getRequestDispatcher(view("site/cart")).forward(request, response);
                break;

            case "/confirm":
                session= request.getSession();
                 a = (Account) session.getAttribute("session");
                 cart = (Cart) session.getAttribute("Cart");
                 if(a==null || cart==null || cart.getList().isEmpty()){
                     Esito e= new Esito(false,"SI E' RILEVATO UN ERRORE DURANTE L'ACQUISTO PER FAVORE CONTROLLA DI AVER EFFETTUATO L'ACCESSO O CHE IL CARRELLO NON SIA VUOTO","confirm");
                     request.setAttribute("esito",e);
                     request.getRequestDispatcher(view("site/cart-alert")).forward(request, response);
                 }else{
                     Ordine o=new Ordine();
                     o.setNome(a.getFirstname());
                     o.setCognome(a.getLastname());
                     o.setIndirizzo(a.getAddress());
                     o.setTotale(cart.getTotale());
                     OrdineDao od=new OrdineDao();
                     if(od.createOrder(o)){
                         Esito e= new Esito(true,"ORDINE EFFETTUATO CON SUCCESSO  nome:"+o.getNome()+"--cognome:"+o.getCognome()+"--indirizzo:"+o.getIndirizzo()+"--totale:"+o.getTotale()+"","confirm");
                         request.setAttribute("esito",e);
                         request.getRequestDispatcher(view("site/cart-alert")).forward(request, response);
                     };
                 }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = getPath(request);
        switch (path) {

            case "/add":
                ProductDao pd=new ProductDao();
                HttpSession session = request.getSession();
                Cart c = (Cart) session.getAttribute("Cart");
                if(c==null){
                    c=new Cart();
                }
                int id=Integer.parseInt(request.getParameter("id"));
                Product p=pd.doRetrievebyId(id);
                int tot=Integer.parseInt(request.getParameter("tot"));
                ArrayList<Product>list=c.getList();
               c.addProduct(p,tot);
                if(session.getAttribute("Cart")==null){
                   session.setAttribute("Cart",c);
                }

                Account a = (Account) session.getAttribute("session");
                session.setAttribute("account", a);
                request.getRequestDispatcher(view("site/home-products")).forward(request, response);
                break;

            case "/remove":
                int removeId=Integer.parseInt(request.getParameter("id"));
                session= request.getSession();
                a= (Account) session.getAttribute("session");
                 c= (Cart) session.getAttribute("Cart");
                 if(c.removeProduct(removeId)){
                    session.setAttribute("Cart",c);
                     session.setAttribute("session",a);
                     request.getRequestDispatcher(view("site/cart")).forward(request, response);
                 };
                 break;

        }
    }
}