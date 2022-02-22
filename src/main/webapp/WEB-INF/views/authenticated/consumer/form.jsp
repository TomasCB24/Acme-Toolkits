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
	<acme:input-textbox code="authenticated.consumer.consumer.form.label.company" path="company"/>
	<acme:input-textbox code="authenticated.consumer.consumer.form.label.sector" path="sector"/>
	
	<acme:submit test="${command == 'create'}" code="authenticated.consumer.consumer.form.button.create" action="/authenticated/consumer/create"/>
	<acme:submit test="${command == 'update'}" code="authenticated.consumer.consumer.form.button.update" action="/authenticated/consumer/update"/>
</acme:form>
