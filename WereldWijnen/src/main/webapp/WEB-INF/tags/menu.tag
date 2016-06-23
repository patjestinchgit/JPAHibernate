<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<div id="container">
	
	<p id="text">Wereldwijnen</p>
	<%-- <img src='<c:url value="/images/intro.jpg"/>' alt='wereldwijnen'id='logo' /> --%>
	<ul class='zonderbolletjes'>
		<c:forEach items='${landen}' var='land'>
			<c:url value='' var='url'>
				<c:param name='landid' value='${land.id}'/>
			</c:url>
			<li><a href='${url}'><img src='<c:url value="/images/${land.id}.png"/>' alt='land' id='vlag' /></a>
		</c:forEach>
	</ul>
	<c:if test='${not empty mandje}'>
			<c:url value='' var="mandje">
				<c:param name='mandje' value='${mandje}'/>
			</c:url>
			<a href='${mandje}'><img src='<c:url value="/images/mandje.png"/>' alt='mandje' id='mandje' /></a>
			
	</c:if>
	
</div>
