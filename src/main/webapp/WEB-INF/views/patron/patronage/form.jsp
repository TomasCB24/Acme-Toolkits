<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form> 
	<acme:input-select readonly='true' code="patron.patronage.form.label.status" path="status">
		<acme:input-option code="PROPOSED" value="PROPOSED" selected="${status == 'PROPOSED'}"/>
		<acme:input-option code="ACCEPTED" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
		<acme:input-option code="DENIED" value="DENIED" selected="${status == 'DENIED'}"/>
	</acme:input-select>
	
	<acme:input-textbox code="patron.patronage.form.label.code" path="code"/>
	<acme:input-textbox code="patron.patronage.form.label.legal-stuff" path="legalStuff"/>
	<acme:input-money code="patron.patronage.form.label.budget" path="budget"/>
	<acme:input-moment code="patron.patronage.form.label.creation-date" path="creationDate"/>
	<acme:input-moment code="patron.patronage.form.label.initial-period-date" path="initialPeriodDate"/>
	<acme:input-moment code="patron.patronage.form.label.final-period-date" path="finalPeriodDate"/>
	<acme:input-url code="patron.patronage.form.label.link" path="link"/>
	<acme:input-textbox code="patron.patronage.form.label.inventor" path="inventor"/>
	
	
	
	<jstl:choose>
	<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && draftMode == 'true'}">
		<acme:submit code="patron.patronage.form.button.update" action="/patron/patronage/update"/>
		<acme:submit code="patron.patronage.form.button.delete" action="/patron/patronage/delete"/>
		<acme:submit code="patron.patronage.form.button.publish" action="/patron/patronage/publish"/>
	</jstl:when>
	<jstl:when test="${command == 'create'}">
		<acme:submit code="patron.patronage.form.button.create" action="/patron/patronage/create"/>
	</jstl:when>
	<jstl:when test="${draftMode == 'false'}">	
		<acme:input-textbox code="patron.patronage.form.label.company" path="company"/>
		<acme:input-textarea code="patron.patronage.form.label.statement" path="statement"/>
		<acme:input-url code="patron.patronage.form.label.inventorLink" path="inventorLink"/>
	</jstl:when>
		

	</jstl:choose>
</acme:form> 