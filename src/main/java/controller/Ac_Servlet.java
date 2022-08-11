package controller;

import http.Controller;
import model.Account;
import model.AccountDao;
import model.Esito;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

@WebServlet(name = "Ac_Servlet", value = "/Ac_Servlet/*")
public class Ac_Servlet extends Controller {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = getPath(request);

        switch (path){


            case "/accounts":
               AccountDao ad=new AccountDao();
                ArrayList<Account> list=new ArrayList<>();
               list=ad.doRetrieveAll();
               request.setAttribute("accounts",list);
                request.getRequestDispatcher(view("accounts/show-accounts")).forward(request,response);
                break;


            case "/show":
                request.getRequestDispatcher(view("site/home-products")).forward(request,response);
                break;

            case "/secret":
                request.getRequestDispatcher(view("crm/secret")).forward(request,response);
                break;

            case "/logout":

                HttpSession session = request.getSession();
                Account account= (Account) session.getAttribute("session");
                if(session==null|| account==null){
                    response.sendRedirect("http://localhost:8080/FG_pops_war_exploded/");
                }else{
                    session.removeAttribute("session");
                    session.invalidate();
                    response.sendRedirect("http://localhost:8080/FG_pops_war_exploded/");
                }
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND,"risorsa non trovata");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path=getPath(request);
        AccountDao ad= new AccountDao();
        switch (path){

            case "/login":
                HttpSession session=request.getSession();
                session.invalidate();
                Account ac=new Account();
                Esito esito = null;
                ac.setEmail(request.getParameter("email"));
                try {
                    ac.setPassword(request.getParameter("password"));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                ad = new AccountDao();
                Account found=new Account();
                found=ad.doRetrieveByEmailPassword(ac.getEmail(), ac.getPassword());
                if(found.getEmail()!=null ) {
                     session= request.getSession(true);
                    session.setAttribute("session",found);
                    if (found.isIsadmin() == false) {
                        request.getRequestDispatcher(view("site/home-products")).forward(request, response);
                    } else {
                        request.getRequestDispatcher(view("crm/home")).forward(request, response);
                    }
                    
                }else 
                    esito= new Esito(false,"ERRORE LOGIN RIPROVA!","login");
                request.setAttribute("esito",esito);
                request.getRequestDispatcher("/WEB-INF/view/crm/secret-alert.jsp").forward(request,response);

            break;



            case"/signin":
                Account a=new Account();
                a.setFirstname(request.getParameter("firstname"));
                a.setLastname(request.getParameter("lastname"));
                try {
                    if (Pattern.matches("^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",request.getParameter("Remail")) && ad.findAccount(request.getParameter("Remail"))==false){
                        a.setEmail(request.getParameter("Remail"));}
                    else {
                        Esito e= new Esito(false,"EMAIL NON VALIDA O GIA' REGISTRATA","signin");
                        request.setAttribute("esito",e);
                        request.getRequestDispatcher("/WEB-INF/view/crm/secret-alert.jsp").forward(request,response);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                if(Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",request.getParameter("Rpassword"))){
                    try {
                        a.setPassword(request.getParameter("Rpassword"));
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Esito e= new Esito(false,"ERRORE REGISTRAZIONE RIPROVA! PASSWORD NON VALIDA ","signin");
                    request.setAttribute("esito",e);
                    request.getRequestDispatcher("/WEB-INF/view/crm/secret-alert.jsp").forward(request,response);
                }
                if(Pattern.matches("^((00|\\+)39[\\. ]??)??3\\d{2}[\\. ]??\\d{6,7}$",request.getParameter("telephone"))){
                    a.setTelephone(request.getParameter("telephone"));
                }
                else {
                    Esito e= new Esito(false,"ERRORE REGISTRAZIONE RIPROVA! NUMERO DI TELEFONO NON VALIDO","signin");
                    request.setAttribute("esito",e);
                    request.getRequestDispatcher("/WEB-INF/view/crm/secret-alert.jsp").forward(request,response);
                }
                a.setAddress(request.getParameter("address"));
                if(ad.createAccount(a)){
                    Esito e= new Esito(true,"REGISTRAZIONE EFFETTUATA CON SUCCESSO","signin");
                    request.setAttribute("esito",e);

                    request.getRequestDispatcher("/WEB-INF/view/crm/secret-alert.jsp").forward(request,response);

                }else{
                    Esito e= new Esito(false,"ERRORE REGISTRAZIONE RIPROVA!  INDIRIZZO NON VALIDO","signin");
                    request.setAttribute("esito",e);
                    request.getRequestDispatcher("/WEB-INF/view/crm/secret-alert.jsp").forward(request,response);
                }
                break;


        }
    }
}
