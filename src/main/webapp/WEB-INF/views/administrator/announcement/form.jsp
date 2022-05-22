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
		<acme:input-textbox code="administrator.announcement.form.label.title" path="title"/>	
		<acme:input-textarea code="administrator.announcement.form.label.body" path="body"/>
		<acme:input-url code="administrator.announcement.form.label.link" path="link"/>
		
		<acme:input-select code="administrator.announcement.form.label.flag" path="flag">
			<acme:input-option code="administrator.announcement.form.label.is-critical" value="TRUE" selected="${flag eq 'TRUE'}"/>
			<acme:input-option code="administrator.announcement.form.label.is-not-critical" value="FALSE" selected="${flag eq 'FALSE'}"/>
		</acme:input-select>
	
	<jstl:if test="${command == 'create'}">
		<acme:input-checkbox code="administrator.announcement.form.label.confirmation" path="confirmation"/>
		<acme:submit code="administrator.announcement.form.button.create" action="/administrator/announcement/create"/>
	</jstl:if>
</acme:form>