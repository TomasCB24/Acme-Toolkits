<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list readonly="false">
	<acme:list-column code="inventor.patronage-report.list.label.sequence-number" path="sequenceNumber" width="20%"/>	
	<acme:list-column code="inventor.patronage-report.list.label.memorandum" path="memorandum" width="80%"/>
</acme:list>

<jstl:if test="${command == 'list-mine'}">
	<acme:button code="inventor.patronage-report.list.button.create" action="/inventor/patronage-report/create"/>
</jstl:if>