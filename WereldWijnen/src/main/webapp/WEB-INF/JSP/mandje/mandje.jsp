<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="v" uri='http://wereldwijnen.be/tags'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html lang='nl'>
<v:head title='${"Wijnen in soort"}' />
<body>
	<header>
		<v:menu />
	</header>
	<h1>mandje</h1>
	<c:url value='' var='url'>
		<c:param name='terugnaaroverzicht' value='something' />
	</c:url>
	<li><a href='${url}'>Terug naar overzicht</a></li>
	<c:if test="${not empty param and empty fouten and empty tijdelijkebestelling}">
		We zijn de bestelling verloren.
	</c:if>
	<c:if test="${not empty tijdelijkebestelling}">
		<table>
			<thead>
				<tr>
					<th>Wijn</th>
					<th>Prijs</th>
					<th>Aantal</th>
					<th>Te betalen</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${tijdelijkebestelling}" var='wijn'>
				<tr>
					<td>${wijn.key.soort.land.naam} ${wijn.key.soort.naam} ${wijn.key.jaar}</td>
					<td>${wijn.key.prijs}</td>
					<td>${wijn.value}</td>
					<td>
						<fmt:formatNumber value="${wijn.key.prijs*wijn.value}" type="currency" currencySymbol="€"/>
					</td>
					<c:set var="totaalBedrag" value="${totaalBedrag + (wijn.key.prijs*wijn.value)}"/>
				</tr>
				</c:forEach>
			</tbody>
			</tbody>
            <tfoot>
                <tr>
                    <td class="righttext" colspan="3">Totaal:</td>
                    <td class="prijsCel righttext"><fmt:formatNumber value="${totaalBedrag}" type="currency" currencySymbol="€"/></td>
                </tr>
            </tfoot>
		</table>	
	</c:if>
	<form method='post' id='toevoegform'>
		<label>Naam:<span>${fouten.naam}</span>
			<input name='naam' value='${param.naam}' autofocus required>
		</label>
		<label>Straat:<span>${fouten.straat}</span>
			<input name='straat' value='${param.straat}' autofocus required>
		</label>
		<label>Huisnummer:<span>${fouten.huisnummer}</span>
			<input name='huisnummer' value='${param.huisnummer}' autofocus required>
		</label>
		<label>Postcode:<span>${fouten.postcode}</span>
			<input name='postcode' value='${param.postcode}' autofocus required>
		</label>
		<label>Gemeente:<span>${fouten.gemeente}</span>
			<input name='gemeente' value='${param.gemeente}' autofocus required>
		</label>
		<div>
			<span>${fouten.bestelwijze}</span>
			<br>
				<label>
					<input name='bestelwijze' value='A' type='radio' id='afhalen' ${param.bestelwijze=="A" ? "checked" : ""}>Afhalen
				</label>
		</div>
		<div>
			<label>
				<input name='bestelwijze' value='O' type='radio' id='ophalen' ${param.bestelwijze == "O" ? "checked" : ""}>Ophalen
			</label>
		</div>
		<input type='submit' value='Toevoegen' id='toevoegknop'>
	</form>
</body>
</html>