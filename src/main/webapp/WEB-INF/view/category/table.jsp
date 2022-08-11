<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table product-table">
    <thead>
    <tr>
        <th>id</th>
        <th>nome</th>
        <th>visualizza prodotti</th>
    </tr>
    </thead>
    <tbody>
   <c:if test="${empty categories}">
       <caption style="font-weight: bold ;color:black" >NESSUNA CATEGORIA ESISTENTE </caption>
   </c:if>
            <c:forEach items="${categories}" var="cat">
                <param name="id" value="${cat.id}">
                <tr>
                    <td data-head="id">${cat.id}</td>
                    <td data-head="nome">${cat.nome}</td>
                    <td data-head="link"><form method="get" action="${pageContext.request.contextPath}/categories/show-by-cat" >
                        <input hidden="hidden" type="text" name="id" value="${cat.id}" id="id">
                        <button type="submit" class="btn special primary">Visualizza Prodotti</button>
                    </form> </td>
                </tr>
            </c:forEach>
    </tbody>
</table>