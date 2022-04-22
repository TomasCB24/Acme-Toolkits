
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="any.user-account.list.label.username" path="username" width="20%"/>
	<acme:list-column code="any.user-account.list.label.name" path="identity.name" width="20%"/>
	<acme:list-column code="any.user-account.list.label.surname" path="identity.surname" width="20%"/>
	<acme:list-column code="any.user-account.list.label.email" path="identity.email" width="20%"/>
	<acme:list-column code="any.user-account.form.label.roles" path="roles" width="20%"/>
</acme:list>
