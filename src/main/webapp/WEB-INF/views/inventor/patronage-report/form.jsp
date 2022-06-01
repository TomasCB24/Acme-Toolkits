<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<jstl:if test="${command == 'show'}">
		<acme:input-textbox code="inventor.patronage-report.form.label.sequence-number" path="sequenceNumber"/>
		<acme:input-moment code="inventor.patronage-report.form.label.creation-moment" path="creationMoment"/>
		<acme:input-textbox readonly='true' code="inventor.patronage-report.form.label.patronage-code" path="patronageCode"/>
		<acme:input-textbox code="inventor.patronage-report.form.label.serial-number" path="serialNumber"/>
	</jstl:if>
	<acme:input-textarea code="inventor.patronage-report.form.label.memorandum" path="memorandum"/>
	<acme:input-textbox code="inventor.patronage-report.form.label.link" path="link"/>	

	<jstl:if test="${command == 'create'}">
		<acme:input-textbox code="inventor.patronage-report.form.label.serial-number" path="serialNumber"/>
		<acme:input-select code="inventor.patronage-report.form.label.patronage-code" path="patronageCode">
			<jstl:forEach var="patronage" items="${patronages}">	
				<acme:input-option code="${patronage.code}" value="${patronage.code}"/>
			</jstl:forEach>
		</acme:input-select>
		<acme:input-checkbox code="inventor.patronage-report.list.label.confirmation" path="confirmation"/>
		<acme:submit code="inventor.patronage-report.form.button.create" action="/inventor/patronage-report/create"/>
	</jstl:if>

</acme:form>