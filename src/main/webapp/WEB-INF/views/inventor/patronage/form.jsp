<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form> 
	<acme:input-select code="inventor.patronage.form.label.status" path="status">
		<acme:input-option code="PROPOSED" value="PROPOSED" selected="${status == 'PROPOSED'}"/>
		<acme:input-option code="ACCEPTED" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
		<acme:input-option code="DENIED" value="DENIED" selected="${status == 'DENIED'}"/>
	</acme:input-select>

	<acme:input-textbox readonly='true' code="inventor.patronage.form.label.code" path="code"/>
	<acme:input-textbox readonly='true' code="inventor.patronage.form.label.legal-stuff" path="legalStuff"/>
	<acme:input-money readonly='true' code="inventor.patronage.form.label.budget" path="budget"/>
	<acme:input-moment readonly='true' code="inventor.patronage.form.label.creation-date" path="creationDate"/>
	<acme:input-moment readonly='true' code="inventor.patronage.form.label.initial-period-date" path="initialPeriodDate"/>
	<acme:input-moment readonly='true' code="inventor.patronage.form.label.final-period-date" path="finalPeriodDate"/>
	<acme:input-url readonly='true' code="inventor.patronage.form.label.link" path="link"/>
	<acme:input-textbox readonly='true' code="inventor.patronage.form.label.company" path="company"/>
	<acme:input-textarea readonly='true' code="inventor.patronage.form.label.statement" path="statement"/>
	<acme:input-url readonly='true' code="inventor.patronage.form.label.patronLink" path="patronLink"/>
	
		<jstl:choose>
	<jstl:when test="${acme:anyOf(command, 'show, update') && draftMode == 'true' && status=='PROPOSED'}">
		<acme:submit code="inventor.patronage.form.button.update" action="/inventor/patronage/update"/>
	</jstl:when>

	</jstl:choose>
</acme:form>