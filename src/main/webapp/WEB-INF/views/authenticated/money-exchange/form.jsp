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
	<acme:input-money code="authenticated.money-exchange.form.label.source" path="source"/>
	<acme:input-textbox code="authenticated.money-exchange.form.label.target-currency" path="targetCurrency" placeholder="EUR, USD, GBP, ..."/>
	
	<acme:input-money code="authenticated.money-exchange.form.label.date" path="date" readonly="true" placeholder=""/>
	<acme:input-money code="authenticated.money-exchange.form.label.target" path="target" readonly="true" placeholder=""/>
		
	<acme:submit code="authenticated.money-exchange.form.button.perform" action="/authenticated/money-exchange/perform"/>
</acme:form>
