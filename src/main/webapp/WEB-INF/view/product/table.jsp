<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table product-table">
    <thead>
    <tr>
        <th>id</th>
        <th>nome</th>
        <th>prezzo</th>
        <th>url</th>
        <th>#prodotti</th>
        <th>descrizione</th>
    </tr>
    </thead>
    <tbody>
   <c:if test="${empty products}">
       <caption style="font-weight: bold ;color:black" >NESSUN PRODOTTO PRESENTE</caption>
   </c:if>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td data-head="id">${product.id}</td>
                    <td data-head="nome">${product.name}</td>
                    <td data-head="prezzo">${product.price}</td>
                    <td data-head="url"><a href="${pageContext.request.contextPath}/covers/${product.cover}">immagine</a> </td>
                    <td data-head="#prodotti">${product.total}</td>
                    <td data-head="descrizione">${product.description}</td>
                </tr>
            </c:forEach>
    </tbody>
</table>