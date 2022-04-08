<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="any.user-account.form.label.username" path="username"/>	
	<acme:input-textbox code="any.user-account.form.label.name" path="name"/>
	<acme:input-textarea code="any.user-account.form.label.surname" path="surname"/>
	<acme:input-url code="any.user-account.form.label.email" path="email"/>
</acme:form>