<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>

<h1><spring:message code="products.title"/></h1>

<c:forEach items="${products}" var="product">
    <table class="table table-striped">
        <tr>
            <td rowspan="4">
                <img src="${product.imageUrl}" alt="${product.name}" class="product-image">
            </td>
        </tr>
        <tr>
            <th colspan="2"><c:out value="${product.name}"/></th>
        </tr>
        <tr>
            <td colspan="2"><c:out value="${product.description}"/></td>
        </tr>
        <tr>
            <td><spring:message code="product.price"/>&nbsp;<fmt:formatNumber pattern="##,###.##"
                                                                              value="${product.price}"/></td>
            <td><spring:message code="product.number"/>&nbsp;<c:out value="${product.number}"/></td>
        </tr>
    </table>
</c:forEach>

<c:if test="${pageIndex > 0 || hasNext}">
    <table class="table">
        <tr>
            <td>
                <c:if test="${pageIndex > 0}">
                    <a class="btn btn-default" href="${ctx}/products?pageIndex=${pageIndex-1}"><spring:message code="page.previous"/></a>
                </c:if>
            </td>
            <td>
                <c:if test="${hasNext}">
                    <a class="btn btn-default" href="${ctx}/products?pageIndex=${pageIndex+1}"><spring:message code="page.next"/></a>
                </c:if>
            </td>
        </tr>
    </table>
</c:if>

</body>
</html>