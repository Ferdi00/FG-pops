package http;

//import javax.annotation.Resource;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class Controller extends HttpServlet {

    //@Resource(name = "jdbc/FG-pops")
    protected static DataSource source;

    protected String getPath(HttpServletRequest req) {return req.getPathInfo() != null ? req.getPathInfo() : "/";}

    protected String view(String viewPath){
        String basePath= getServletContext().getInitParameter("basePath");
        String engine= getServletContext().getInitParameter("engine");
        return basePath + viewPath + engine;

    }


    protected String back(HttpServletRequest request) {return request.getServletPath() + request.getPathInfo();}

    protected String getUploadPath(){
        return System.getenv("CATALINA_HOME") + File.separator + "uploads" + File.separator;
    }

    protected boolean isAjax(HttpServletRequest request){
        return "XMLHttpRequest".equals(request.getHeader("X-requested-With"));
    }

    protected void sendJson(HttpServletResponse response, JSONObject object) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer= response.getWriter();
        writer.print(object.toString());
        writer.flush();
    }

}


