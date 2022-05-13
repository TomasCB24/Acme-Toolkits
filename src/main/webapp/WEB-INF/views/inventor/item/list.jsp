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

	<acme:list-column code="inventor.item.list.label.type" path="type" width="30%"/>
	<acme:list-column code="inventor.item.list.label.name" path="name" width="30%"/>
	<acme:list-column code="inventor.item.list.label.retailPrice" path="retailPrice" width="25%"/>
	<acme:list-column code="inventor.item.list.label.quantity" path="quantity" width="15%"/>

</acme:list>

<jstl:if test="${command == 'list-mine'}">
	<acme:button code="inventor.item.list.button.create" action="/inventor/item/create"/>
</jstl:if>
