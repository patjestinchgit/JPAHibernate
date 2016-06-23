<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="v" uri='http://wereldwijnen.be/tags'%>
<!doctype html>
<html lang='nl'>
<v:head title= '${empty land ? "Soorten wijn" : land.id}'/>
<body>
	<header>
		<v:menu />
	</header>
	<h1>test</h1>
	<h1>Soorten van land</h1>
	
	<c:if test='${not empty land}'>
	<h1>${land.naam}</h1>
	<ul class='zonderbolletjes'>
		<c:forEach items='${land.soorten}' var='soort'>
			<c:url value='' var='url'>
				<c:param name='soortid' value='${soort.id}' />
				<c:param name='landid' value='${land.id}'/>
			</c:url>
			<li><a href='${url}'>${soort.naam}</a></li>
		</c:forEach>
	</ul>
	</c:if>
</body>
</html>