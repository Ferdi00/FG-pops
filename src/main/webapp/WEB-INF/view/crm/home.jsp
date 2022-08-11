<%@ page import="model.ProductDao" %>
<%@ page import="model.AccountDao" %>
<%@ page import="model.OrdineDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Ordine" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="fg-home"/>
    </jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/crm.js" defer></script>

    <link href="/FG_pops_war_exploded/css/crm.css" rel="stylesheet">
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
    <%ProductDao pd= new ProductDao();
        AccountDao ad=new AccountDao();
        OrdineDao od=new OrdineDao();
    int prod=pd.countAll();
    int acc=ad.countAll();
    int ord=od.countAll();
        ArrayList<Ordine> list=od.doRetrieveAll();
    %>
        <div class="body grid-x justify-center">
            <div class="grid-y cell w40">
                <h4>prodotti</h4>
                <h2><%=prod%></h2>
            </div>
            <div class="grid-y cell w40">
                <h4>incasso</h4>
                <h2><%double sum=0;
                    for ( Ordine o:list) {
                        sum+=o.getTotale();
                    } String b = String.format("%.2f", sum);
                %><%=b%>€</h2>
            </div>
            <div class="grid-y cell w40">
                <h4>ordini totali</h4>
                <h2><%=ord%></h2>
            </div>
            <div class="grid-y cell w40">
                <h4>clienti registrati</h4>
                <h2><%=acc%></h2>
            </div>
        </div>

    </section>
</main>
</body>
</html>

