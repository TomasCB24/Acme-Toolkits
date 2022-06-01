<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form> 
	
	<acme:input-textbox code="patron.patronage.form.label.code" path="code"/>
	<acme:input-textarea code="patron.patronage.form.label.legal-stuff" path="legalStuff"/>
	<acme:input-money code="patron.patronage.form.label.budget" path="budget"/>
	<acme:input-moment code="patron.patronage.form.label.initial-period-date" path="initialPeriodDate"/>
	<acme:input-moment code="patron.patronage.form.label.final-period-date" path="finalPeriodDate"/>
	<acme:input-url code="patron.patronage.form.label.link" path="link"/>
	<acme:input-textbox code="patron.patronage.form.label.inventor" path="inventor"/>
	
	
	
	<jstl:choose>
	<jstl:when test="${command == 'show' && draftMode == 'false'}">
		<acme:input-textbox readonly='true' code="patron.patronage.form.label.status" path="status"/>
		<acme:input-textbox readonly='true' code="patron.patronage.form.label.company" path="company"/>
		<acme:input-textarea readonly='true' code="patron.patronage.form.label.statement" path="statement"/>
		<acme:input-url readonly='true' code="patron.patronage.form.label.inventorLink" path="inventorLink"/>
		<acme:input-moment readonly='true' code="patron.patronage.form.label.creation-date" path="creationDate"/>
	</jstl:when>
	<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && draftMode == 'true'}">
		<acme:submit code="patron.patronage.form.button.update" action="/patron/patronage/update"/>
		<acme:submit code="patron.patronage.form.button.delete" action="/patron/patronage/delete"/>
		<acme:submit code="patron.patronage.form.button.publish" action="/patron/patronage/publish"/>
	</jstl:when>
	<jstl:when test="${command == 'create'}">
		<acme:submit code="patron.patronage.form.button.create" action="/patron/patronage/create"/>
	</jstl:when>

		

	</jstl:choose>
</acme:form> 