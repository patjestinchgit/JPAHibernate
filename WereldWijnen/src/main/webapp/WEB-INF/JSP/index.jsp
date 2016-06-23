<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="v" uri='http://wereldwijnen.be/tags'%>
<!doctype html>
<html lang='nl'>
<v:head title='${"Wijnen in soort"}' />
<body>
	<header>
		<v:menu />
	</header>

	<c:if test='${not empty land}'>
		<h1>Soorten uit ${land.naam}</h1>
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
	<c:if test='${not empty bestelbon}'>
		
		<h1>Je mandje is bevestigd als bestelbon ${bestelbon}</h1>
		
	</c:if>

	
</body>
</html>