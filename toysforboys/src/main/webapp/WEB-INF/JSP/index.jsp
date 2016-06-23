<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="v" uri='http://toysforboys.be/tags'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang='nl'>
<v:head title='toysforboys' />
<body>
	<header>
		<%-- <v:menu /> --%>
		<h2>Unshipped orders</h2>
	</header>
	<c:if test="${not empty bestellingen}">
		<form method='post' id='shipping'>
		<table>
			<thead>
				<tr>
					<th>id</th>
					<th>Ordered</th>
					<th>Required</th>
					<th>Customer</th>
					<th>Comments</th>
					<th>Status</th>
					<th>Shipped</th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach items="${bestellingen}" var="order">
					<tr>
						<td style="text-align:right;">
							<c:url value='' var='url'>
								<c:param name='orderid' value='${order.id}'/>
							</c:url>
							<a href='${url}'>${order.id}</a>
						</td>
						<td>
							<fmt:formatDate pattern="dd-MM-yy" value="${order.orderdate}"/>
						</td>
						<td>
							<fmt:formatDate pattern="dd-MM-yy" value="${order.requireddate}"/>
						</td>
						<td>${order.customer.name}</td>
						<td>${order.comments}</td>
						<td>
							<img src='<c:url value="/images/${order.status}.png"/>' alt='status' id='status' />
							${order.status}
						</td>
						<td>
							<input type='checkbox' name='setttingasshipped' value=${order.id}>
						</td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
		<input type='submit' value='Set as shipped' id='shipped'>
		</form>
		
	</c:if>
</body>
</html>