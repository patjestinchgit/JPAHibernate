<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="v" uri='http://toysforboys.be/tags'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html lang='nl'>
<v:head title='toysforboys' />
<body>
	<header>
		<%-- <v:menu /> --%>
		<h2>Order ${order.id}</h2>
	</header>
	<p>Ordered:</p>
	<h3><fmt:formatDate pattern="dd/MM/yy" value="${order.orderdate}"/></h3>
	<p>Customer:</p>
	<h3> ${order.customer.name} </h3>
	<h3> ${order.customer.streetandnumber} </h3>
	<h3> ${order.customer.postalcode} ${order.customer.city} ${order.customer.countrie.name} </h3>
	<p>Comments:</p>
	<h3> ${order.comments} </h3>
	<p>Details:</p>
	
	<c:if test="${not empty orderdetails}">
		<table>
			<thead>
				<tr>
					<th>Product</th>
					<th>Price each</th>
					<th>Quantity</th>
					<th>Value</th>
					<th>Deliverable</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${orderdetails}" var="orderdetail">
					<tr>
						<td>${orderdetail.product.name}</td>
						<td>${orderdetail.priceeach}</td>
						<td>${orderdetail.quantityordered}</td>
						<td>
							<fmt:formatNumber value="${orderdetail.quantityordered*orderdetail.priceeach}" type="currency" currencySymbol="€"/>
						</td>
						<td>
							<c:if test="${orderdetail.product.quantityinstock gt orderdetail.quantityordered}">
								<p>&check;</p>
							</c:if>
							<c:if test="${orderdetail.product.quantityinstock lt orderdetail.quantityordered}">
								<p>&cross;</p>
							</c:if>
						</td>
						<c:set var="totaalBedrag" value="${totaalBedrag + (orderdetail.quantityordered*orderdetail.priceeach)}"/>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<p>Value:</p>
		<fmt:formatNumber value="${totaalBedrag}" type="currency" currencySymbol="€"/>
	</c:if>
	<c:url value='' var='url'>
		<c:param name='terugnaaroverzicht' value='something' />
	</c:url>
	<li><a href='${url}'>Terug naar overzicht</a></li>
</body>
</html>