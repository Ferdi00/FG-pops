
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value=""/>
    </jsp:include>

    <link href="/FG_pops_war_exploded/css/products.css" rel="stylesheet">
    <link href="/FG_pops_war_exploded/css/crm.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/crm.js" defer></script>
    <style>
        .create *{
        }
        legend{
            color: darkred;
            font-size: 100%;
        }
        fieldset{
            height: 200px;
        }
    </style>
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
        <div class="body grid-x justify-center ">
            <form  method="post" action="${pageContext.request.contextPath}/categories/create">
                <fieldset class="grid-x cell create">
                    <legend>Aggiungi Categoria</legend>

                    <label for="name" class="field cell w50">
                        <input type="text"id="name" name="name" placeholder="nome">
                    </label>

                    <label for="id" class="field cell w50">
                        <input type="text" id="id" name="id" placeholder="id">
                    </label>
                    <button type="submit" class="btn special primary">Aggiungi Categoria</button>
                </fieldset>
            </form>
        </div>

    </section>
</main>
</body>
</html>

