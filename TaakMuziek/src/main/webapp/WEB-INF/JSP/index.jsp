<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="v" uri='http://muziek.be/tags'%>
<!doctype html>
<html lang='nl'>
<v:head title='muziek' />
<body>
	<header>
		<v:menu />
		<h1>Musicbox</h1>
		<h2>De platen van de musicbox</h2>
		<!--  <img src='<c:url value="/images/logo.jpg"/>' alt='fietsers' id='logo' />  -->
		<ul>
			<c:forEach items='${albums}' var='album'>
			<c:url value='/albums/zoekenopnummer.htm' var='url'>
				<c:param name='albumid' value='${album.id}'/>
			</c:url>
				<li><a href='${url}'>${album.naam}</a>(${album.artiest.naam})</li>
			</c:forEach>
		</ul>
	</header>
</body>
</html>
