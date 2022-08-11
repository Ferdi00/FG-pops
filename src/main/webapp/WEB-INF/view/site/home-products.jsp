    <%@ page import="java.util.ArrayList" %>
    <%@ page import="model.*" %>
    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
        <html lang="it" dir="ltr">

        <head>
            <jsp:include page="/WEB-INF/view/partials/head.jsp" >
                <jsp:param name="title" value="benvenuto in fg-pops"/>
            </jsp:include>

            <link href="/FG_pops_war_exploded/css/home.css" rel="stylesheet">
            <link href="/FG_pops_war_exploded/css/crm.css" rel="stylesheet">
            <link href="/FG_pops_war_exploded/css/library.css" rel="stylesheet">

            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
            <script type="text/javascript" src="${pageContext.request.contextPath}/js/library.js" defer></script>
        </head>

        <body onload="ajax()">
        <main class="app">
            <section class="content grid-y ">
                <%@include file="/WEB-INF/view/partials/topbarhome.jsp"%> <% Account ac= (Account) session.getAttribute("session"); %>
               <div  class="grid-x justify-center w10" >
                   <form action="${pageContext.request.contextPath}/crm/category"> <select  id="categoryId" name="categoryId" >
                       <option value="0">-SELEZIONA UNA CATEGORIA-</option>
                   </select>
                       <button class="btn primary" type="submit">Visualizza per categoria</button>
                </form>

                </div>
                <div class="grid-x  body  ">
<%session.setAttribute("session",ac); %>
            <% ProductDao pd= new ProductDao();
                ArrayList<Product> list= (ArrayList<Product>)pd.doRetrieveAll();
                for (Product p: list) {
            %>  <div  class="w10 show-prod grid-y align-center  ">
            <img src="${pageContext.request.contextPath}/covers/<%=p.getCover()%>" width="90%" height="75%">
                    <ul style="list-style-type: none;padding: 0;margin: 0">
                        <li><%=p.getPrice()%>€</li>
                    </ul>
                    <form method="post" action="${pageContext.request.contextPath}/crm/show">
                        <input type="hidden" name="name" value="<%=p.getName()%>">
                        <button style="background-color: darkred">mostra</button>
                    </form>
            </div>   <%
            } %>
        </div>
                <label style="align-self: flex-end;color: darkred;margin-right: 0.5rem;font-weight: bold;font-size: 1rem;"><%if(ac==null){%>Accedi con un account<%} else {%> Benvenuto <%=ac.getFirstname()%> <%=ac.getLastname()%> <%}%></label>
                <%@include file="/WEB-INF/view/partials/info.jsp"%>
            </section>
        </main>

        </body>
        </html>