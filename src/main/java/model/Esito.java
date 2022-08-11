package model;

public class Esito {

    public Esito(){}

    public Esito(boolean b,String message,String servlet){
        this.check=b;
        this.message=message;
        this.servlet=servlet;
    }

    public boolean getCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isCheck() {
        return check;
    }

    public String getServlet() {
        return servlet;
    }

    public void setServlet(String servlet) {
        this.servlet = servlet;
    }

    private boolean check;
    private String message;
    private String servlet;
}
