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

<body >
<main class="app">
    <section class="content grid-y ">
        <%@include file="/WEB-INF/view/partials/topbarhome.jsp"%><% Account ac= (Account) session.getAttribute("session"); %>
        <div class="grid-x body justify-center ">
            <section class= "grid-y product-info   "style="flex: 1;font-family: Calibri">
                <% Cart cart=(Cart) session.getAttribute("Cart");%>
                <div></div>
                <div></div>
                <table class="table product-table justify-center align-center">
                    <thead>
                    <tr>
                        <th>nome</th>
                        <th>prezzo</th>
                        <th>quantità</th>
                        <th>descrizione</th>
                        <th>elimina</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% if(cart==null || cart.getList().isEmpty()){
                    %> <label>  Il tuo carrello è vuoto premi il link per visualizzare i nostri prodotti  <a href="http://localhost:8080/FG_pops_war_exploded/crm/category?categoryId=0">premi</a></label><%
                    }else{%>
                    <label><%if(ac==null){%>Visitatore ecco il tuo carrello <br> Ricordati di accedere per effettuare l'acquisto premendo <a style="text-decoration: none" href="${pageContext.request.contextPath}/Ac_Servlet/secret"> qui </a><%} else {%> <%=ac.getFirstname()%> ecco il tuo carrello<br> Se sei soddisfatto procedi con l'acquisto <%}%></label>
                    <%
                        for (Product p : cart.getList() ) {%>
                    <br>
                    <tr>
                        <td ><%=p.getName()%></td>
                        <td ><%=p.getPrice()%></td>
                        <td ><%=p.getTotal()%></td>
                        <td ><%=p.getDescription()%></td>
                        <td >
                            <form action="${pageContext.request.contextPath}/cart/remove" method="post">
                                <input type="hidden" value="<%=p.getId()%>" name="id" >
                                <button style="background-color: darkred;color: white" type="submit"><%@ include file="/icons/trash-can.svg"%></button>
                            </form></td>
                    </tr>
                    <%}}%>
                    </tbody>
                </table>
                <br>
                <%if(cart!=null){%>
                <span > <label>Prezzo totale:<%=cart.getTotale()%></label>  <input onclick="window.location.href='http://localhost:8080/FG_pops_war_exploded/cart/confirm'" type="button" class="btn primary" value="Procedi con l'acquisto"></span><%
            }else {%>
                <br><br><br><br>
                <span> <label style="margin-right: 1rem">Prezzo totale:0</label><input onclick="window.location.href='http://localhost:8080/FG_pops_war_exploded/cart/confirm'" type="button " class="btn primary" value="Procedi con l'acquisto"></span>
                <%}%>
                <%
                    Esito esito= (Esito) request.getAttribute("esito");
                    String s= (String) request.getAttribute("lbl");

                    if (esito.getCheck()==true){
                %><div class="success"> <%= esito.getMessage() %>  </div> <%}

            else {
            %><div class="failure"> <%= esito.getMessage() %>  </div> <%}%>
            </section>
        </div>
        <label style="align-self: flex-end;color: darkred;margin-right: 0.5rem;font-weight: bold;font-size: 1rem;"><%if(ac==null){%>Accedi con un account<%} else {%> Benvenuto <%=ac.getFirstname()%> <%=ac.getLastname()%> <%}%></label>
        <%@include file="/WEB-INF/view/partials/info.jsp"%>
    </section>
</main>

</body>
</html>