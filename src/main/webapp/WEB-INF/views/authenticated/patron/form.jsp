
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="authenticated.patron.patron.form.label.company" path="company"/>
	<acme:input-textbox code="authenticated.patron.patron.form.label.statement" path="statement"/>
	<acme:input-textbox code="authenticated.patron.patron.form.label.link" path="link"/>
	
	<acme:submit test="${command == 'create'}" code="authenticated.patron.patron.form.button.create" action="/authenticated/patron/create"/>
	<acme:submit test="${command == 'update'}" code="authenticated.patron.patron.form.button.update" action="/authenticated/patron/update"/>
</acme:form>
