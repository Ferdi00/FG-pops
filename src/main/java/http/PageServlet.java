package http;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PageServlet", value = "/pages/*")
public class PageServlet extends Controller {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String path = getPath(request);
            switch (path) {
                case "/dashboard":
                    request.getRequestDispatcher(view("crm/home")).forward(request, response);
                    break;
            }


    }}


