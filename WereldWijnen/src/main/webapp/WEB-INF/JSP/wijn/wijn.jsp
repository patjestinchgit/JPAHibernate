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
		<h1>Wijn toevoegen aan mandje</h1>
		
		<%-- overzicht --%>
		<c:url value='' var='url'>
			<c:param name='soortid' value='${wijn.soort.id}' />
			<c:param name='landid' value='${wijn.soort.land.id}'/>
		</c:url>
		<li><a href='${url}'>terug naar overzicht</a></li>
		<dl>
			<dt>Land</dt>
			<dd>${wijn.soort.land.naam}</dd>
			<dt>Soort</dt>
			<dd>${wijn.soort.naam}</dd>
			<dt>Jaar</dt>
			<dd>${wijn.jaar}</dd>
			<dt>Beoordeling</dt>
			<dd>
				
				<c:set var="variabele" value="${wijn.beoordeling}"/>
				<c:forEach var="i" begin="1" end= "${variabele}">
					*
				</c:forEach>
				
			</dd>
			<dt>Prijs</dt>
			<dd>${wijn.prijs}</dd>
		</dl>
		<br>
		<form method='post' id='toevoegform'>
			<label>Aantal flessen:<span>${fouten.aantal}</span>
				<input name='aantalflessen' value='${param.aantal}' autofocus required>
			</label>
			<input type='submit' value='Toevoegen' id='toevoegknop'>
		</form>
		
			
	</c:if>
	

</body>
</html>