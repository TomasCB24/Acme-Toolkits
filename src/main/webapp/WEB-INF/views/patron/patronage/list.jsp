<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="patron.patronage.list.label.code" path="code" width="10%"/>
	<acme:list-column code="patron.patronage.list.label.creation-date" path="creationDate" width="10%"/>
	<acme:list-column code="patron.patronage.list.label.legal-stuff" path="legalStuff" width="80%"/>
</acme:list> 