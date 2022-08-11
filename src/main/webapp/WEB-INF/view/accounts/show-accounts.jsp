<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="gestione utenti"/>
    </jsp:include>
    <link href="/FG_pops_war_exploded/css/products.css" rel="stylesheet">
    <link href="/FG_pops_war_exploded/css/crm.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/crm.js" defer></script>

</head>

<body >
<main class="app">
    <%@include file="../partials/sidebar.jsp"%>
    <section class="content grid-y">
        <header class="topbar grid-x align-center">
            <%@ include file="../../../icons/menu.svg" %>
            <label class="field command align-center">
            </label>
            <span class="account">
            <%@ include file="../../../icons/user.svg" %>
            Pagina Amministratore
        </span>
        </header>
        <div class="body grid-x justify-center">
            <section class="grid-y cell products">
                <div id="special" >LISTA UTENTI</div>
                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <table class="table product-table">
                    <thead>
                    <tr>
                        <th>nome</th>
                        <th>cognome</th>
                        <th>email</th>
                        <th>telefono</th>
                        <th>indirizzo di spedizione</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${empty accounts}">
                        <caption style="font-weight: bold ;color:black" >NESSUN PROFILO REGISTRATO</caption>
                    </c:if>
                    <c:forEach items="${accounts}" var="account">
                        <tr>
                            <td data-head="firstname">${account.firstname}</td>
                            <td data-head="lastname">${account.lastname}</td>
                            <td data-head="email">${account.email}</td>
                            <td data-head="telephone">${account.telephone}</td>
                            <td data-head="address">${account.address}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </section>

        </div>

    </section>
</main>
</body>
</html>