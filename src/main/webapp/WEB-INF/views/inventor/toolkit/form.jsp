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
	<acme:input-textbox code="inventor.toolkit.form.label.code" path="code"/>
	<acme:input-textbox code="inventor.toolkit.form.label.title" path="title"/>
	<acme:input-textarea code="inventor.toolkit.form.label.description" path="description"/>
	<acme:input-textarea code="inventor.toolkit.form.label.assembly-notes" path="assemblyNotes"/>
	
	<acme:input-select code="inventor.toolkit.form.label.draft-mode" path="draftMode">
		<acme:input-option code="inventor.toolkit.form.label.draft-mode.true" value="true" selected="${draftMode == 'true'}"/>
		<acme:input-option code="inventor.toolkit.form.label.draft-mode.false" value="false" selected="${draftMode == 'false'}"/>
	</acme:input-select>
	
	<acme:input-url code="inventor.toolkit.form.label.link" path="link"/>
	
	<acme:button code="inventor.toolkit.form.label.items" action="/inventor/item/list?masterId=${inventorId}"/>
	
</acme:form>
