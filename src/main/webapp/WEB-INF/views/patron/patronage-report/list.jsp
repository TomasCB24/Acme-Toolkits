<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="patron.patronage-report.list.label.serial-number" path="serialNumber" width="80%"/>	
	<acme:list-column code="patron.patronage-report.list.label.memorandum" path="memorandum" width="20%"/>
</acme:list>