<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en-US" >
<head>
<title>Welocme</title>
</head>
<body>
	<h2>${greeting}</h2>
	
	<table>
	<c:forEach items="${products}" var="prod" varStatus="itemRow">
		<tr>
			<td align="center">${prod.pid}</td><td align="center">${prod.brandName}</td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>

