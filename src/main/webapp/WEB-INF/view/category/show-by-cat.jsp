<%@ page import="model.ProductDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Product" %><%--
  Created by IntelliJ IDEA.
  User: ferna
  Date: 28/06/2021
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista Prodotti</title>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="gestione prodotti"/>
    </jsp:include>
    <link href="/FG_pops_war_exploded/css/products.css" rel="stylesheet">
    <link href="/FG_pops_war_exploded/css/crm.css" rel="stylesheet">
</head>
<body>
<main class="app">
        <div class="body grid-x justify-center">
            <section class="grid-y cell products">
                <div id="special" >LISTA PRODOTTI</div>
                <%@include file="../product/table.jsp"%>
            </section>
        </div>
</main>
</body>
</html>
