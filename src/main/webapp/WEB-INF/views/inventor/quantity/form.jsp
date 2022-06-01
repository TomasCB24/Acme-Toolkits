<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	
	<acme:input-integer code="inventor.quantity.form.label.number" path="number"/>
	
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:input-select code="inventor.quantity.form.label.item-code" path="itemCode">
				<jstl:forEach var="item" items="${items}">	
					<acme:input-option code="${item.code}" value="${item.code}"/>
				</jstl:forEach>
			</acme:input-select>
		</jstl:when>
		<jstl:otherwise>
			<acme:input-textbox readonly='true' code="inventor.quantity.form.label.item-code" path="itemCode"/>
		</jstl:otherwise>
	</jstl:choose>
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
			<acme:submit code="inventor.quantity.form.button.update" action="/inventor/quantity/update"/>
			<acme:submit code="inventor.quantity.form.button.delete" action="/inventor/quantity/delete"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.quantity.form.button.create" action="/inventor/quantity/create"/>
		</jstl:when>	
	</jstl:choose>
	
</acme:form>

