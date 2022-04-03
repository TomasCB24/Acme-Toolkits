<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form> 
	<acme:input-select code="inventor.patronage.form.label.status" path="status">
		<acme:input-option code="PROPOSED" value="PROPOSED" selected="${status == 'PROPOSED'}"/>
		<acme:input-option code="ACCEPTED" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
		<acme:input-option code="DENIED" value="DENIED" selected="${status == 'DENIED'}"/>
	</acme:input-select>
	<acme:input-textbox code="inventor.patronage.form.label.code" path="code"/>
	<acme:input-textbox code="inventor.patronage.form.label.legal-stuff" path="legalStuff"/>
	<acme:input-money code="inventor.patronage.form.label.budget" path="budget"/>
	<acme:input-moment code="inventor.patronage.form.label.creation-date" path="creationDate"/>
	<acme:input-moment code="inventor.patronage.form.label.initial-period-date" path="initialPeriodDate"/>
	<acme:input-moment code="inventor.patronage.form.label.final-period-date" path="finalPeriodDate"/>
	<acme:input-url code="inventor.patronage.form.label.link" path="link"/>
	
	<!-- 
	TO-DO: Será algo similar para enlazar esta vista con la del perfil del user-account del patrón
	<acme:button code="any.user-account.form.button.patron" action="/any/user-account/list?masterId=${id}"/>
	-->
</acme:form>