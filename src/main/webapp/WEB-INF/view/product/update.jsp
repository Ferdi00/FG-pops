<%@ page import="model.CategoryDao" %>
<%@ page import="model.Category" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="fg-home"/>
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
        <div class="body grid-x justify-center">
            <form enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath}/products/update">
                <fieldset class="grid-x cell create">
                    <legend>Modifica Prodotto</legend>

                    <label for="name" class="field cell w50">
                        <input type="text"id="name" name="name" placeholder="nome">
                    </label>

                    <label for="id" class="field cell w50">
                        <input type="number" id="id" name="id" placeholder="id">
                    </label>

                    <label for="price" class="field cell w50">
                        <input type="text" id="price" name="price" placeholder="prezzo">
                    </label>
                    <label
                            for="desc" class="field cell w50">
                        <input type="text" id="desc" name="desc" placeholder="descrizione">
                    </label>
                    <label for="category" class="field cell w50">
                        <select name="category" id="category">
                            <% CategoryDao cd= new CategoryDao();
                                ArrayList<Category> categories = new ArrayList<>();
                                categories=cd.doRetrieveAll();
                                for (Category c:categories) {
                            %>  <option value="<%=c.getId()%>"> <%=c.getNome() %>     </option>
                            <%} %>
                        </select>
                    </label>
                    <label
                            for="totale" class="field cell w50">
                        <input type="number" id="totale" name="totale" placeholder="quantità">
                    </label>
                    <label for="cover" class="field cell w50">
                        <input type="file" name="cover" id="cover">
                    </label>
                    <button type="submit" class="btn special primary">Modifica Prodotto</button>
                </fieldset>
            </form>
        </div>
    </section>
</main>
</body>
</html>

