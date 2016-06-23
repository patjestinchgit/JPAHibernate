<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="v" uri='http://muziek.be/tags'%>
<!doctype html>
<html lang='nl'>
<v:head title='muziek' />
<body>
<header>
		<v:menu />
	
	
		<h1>${album.naam}- ${album.artiest.naam}</h1>
		<ul>
			<c:forEach var="track" items="${album.tracks}">
				<li>${track.naam}${track.tijd}</li>
			</c:forEach>
		</ul>
		Totale tijd: ${album.tijd}

	</header>
</body>
</html>
