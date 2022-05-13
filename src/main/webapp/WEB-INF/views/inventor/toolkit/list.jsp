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
	<acme:list-column code="inventor.toolkit.list.label.code" path="code" width="26%"/>
	<acme:list-column code="inventor.toolkit.list.label.title" path="title" width="37%"/>
	<acme:list-column code="inventor.toolkit.list.label.retail-price" path="retailPrice" width="37%"/>
</acme:list>

<jstl:if test="${command == 'list-mine'}">
	<acme:button code="inventor.toolkit.list.button.create" action="/inventor/toolkit/create"/>
</jstl:if>	
