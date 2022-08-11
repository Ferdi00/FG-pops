<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<header class="topbarhome grid-x align-center">
    <span class="logo"><img src="${pageContext.request.contextPath}/images/logo1.png" width="65" height="60"></span>
    <section style="display: inline-block" class="command align-center">
        <span class=" link"><a href="http://localhost:8080/FG_pops_war_exploded/">HOME</a></span>
        <span class=" link"><a href="/FG_pops_war_exploded/crm/category?categoryId=0">PRODOTTI</a></span>
        <span class=" link"><a href="/FG_pops_war_exploded/Ac_Servlet/logout">LOGOUT</a></span>
    </section>
    <span class="account">
                    <%@ include file="/icons/user.svg" %>
                    </span>
    <span class="shopping-cart">
        <%@ include file="/icons/shopping-cart.svg" %>
        <span class="badge" id="badge" >
            ${fn:length(Cart.list)}
        </span>
    </span>
    <span class="logo"><img src="${pageContext.request.contextPath}/images/logo2.png" width="65" height="60"></span>
</header>