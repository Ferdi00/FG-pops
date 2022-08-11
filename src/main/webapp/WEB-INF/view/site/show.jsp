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

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/library.js" defer></script>
    <style>
        .product-info label{
            font-family: Calibri;
        }
    </style>
</head>

<body>
<main class="app">
    <section class="content grid-y ">
        <%@include file="/WEB-INF/view/partials/topbarhome.jsp"%>
        <% Account ac= (Account) session.getAttribute("session"); %>
        <div class="grid-x body justify-center ">
            <section class="w25 justify-center product-info"><img src="${pageContext.request.contextPath}/covers/${product.cover}" width="80%" height=80%"></section>
            <section class= "grid-y justify-center product-info"style="float:left;flex: 1">
                <form class="grid-y justify-center" action="${pageContext.request.contextPath}/cart/add" method="post">
                    <input type="text" hidden name="id" value="${product.id}">
                <label >Nome:${product.name}</label>
                <label >Prezzo:${product.price}</label>
                <label >Descrizione:${product.description}</label>
                <label >Quantità disponibili:${product.total}</label>
                <div><label for="tot">Inserisci quantità: </label> <input  style="height: 1.3rem;width: 6rem" type="number" id="tot" name="tot"  max="${product.total}" min="0"></div>
                <div><button type="submit" onclick="total()" class="btn primary">AGGIUNGI AL CARRELLO <%@ include file="/icons/shopping-cart.svg" %></button></div>
                </form>
            </section>
        </div>
        <label style="align-self: flex-end;color: darkred;margin-right: 0.5rem;font-weight: bold;font-size: 1rem;"><%if(ac==null){%>Accedi con un account<%} else {%> Benvenuto <%=ac.getFirstname()%> <%=ac.getLastname()%> <%}%></label>
        <%@include file="/WEB-INF/view/partials/info.jsp"%>
    </section>
</main>

</body>
</html>