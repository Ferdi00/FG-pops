<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="gestione prodotti"/>
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
                <div id="special" >LISTA PRODOTTI</div>
                <%@include file="../product/table.jsp" %>
                <button onclick="location.href='/FG_pops_war_exploded/products/create'" type="submit" class="btn primary "  >AGGIUNGI NUOVO PRODOTTO</button>
                <button onclick="location.href='/FG_pops_war_exploded/products/update'" type="submit" class="btn primary "  >MODIFICA PRODOTTO</button>
            </section>

        </div>

    </section>
</main>
</body>
</html>