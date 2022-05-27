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

<acme:list>

	<acme:list-column code="inventor.quantity.list.label.number" path="number" width="20%"/>
	<acme:list-column code="inventor.quantity.list.label.item-code" path="itemCode" width="30%"/>
	<acme:list-column code="inventor.quantity.list.label.item-name" path="itemName" width="30%"/>
	<acme:list-column code="inventor.quantity.list.label.item-type" path="itemType" width="20%"/>

</acme:list>

<acme:button code="inventor.quantity.form.button.create" action="/inventor/quantity/create?masterId=${masterId}"/>
