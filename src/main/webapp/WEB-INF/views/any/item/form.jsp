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
	
	<acme:input-select code="any.item.form.label.item-type" path="itemType">
		<acme:input-option code="any.item.form.label.item-type.tool" value="TOOL" selected="${itemType == 'TOOL'}"/>
		<acme:input-option code="any.item.form.label.item-type.component" value="COMPONENT" selected="${itemType == 'COMPONENT'}"/>
	</acme:input-select>
	
	<acme:input-textbox code="any.item.form.label.name" path="name"/>
	<acme:input-textbox code="any.item.form.label.code" path="code"/>
	<acme:input-textarea code="any.item.form.label.technology" path="technology"/>
	<acme:input-textarea code="any.item.form.label.description" path="description"/>
	<acme:input-money code="any.item.form.label.retail-price" path="retailPrice"/>
	<acme:input-url code="any.item.form.label.link" path="link"/>
	<acme:input-textbox code="any.item.form.label.inventor-full-name" path="inventor"/>

	
</acme:form>

