<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>

<h1><spring:message code="products.title"/></h1>

<c:forEach items="${products}" var="product">
    <table class="table table-striped">
        <tr>
            <td rowspan="3">
                <img src="${product.imageUrl}" alt="${product.name}">
            </td>
        </tr>
        <tr>
            <th colspan="2"><c:out value="${product.name}"/></th>
        </tr>
        <tr>
            <td><fmt:formatNumber pattern="##,###.##" value="${product.price}"/></td>
            <td><c:out value="${product.number}"/></td>
        </tr>
    </table>
</c:forEach>

</body>
</html>