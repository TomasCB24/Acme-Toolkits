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
	<acme:input-textbox code="administrator.system-configuration.form.label.system-currency" path="systemCurrency"/>
	<acme:input-textbox code="administrator.system-configuration.form.label.accepted-currencies" path="acceptedCurrencies"/>
	<acme:input-textbox code="administrator.system-configuration.form.label.strong-spam-words" path="strongSpamWords"/>
	<acme:input-double code="administrator.system-configuration.form.label.strong-spam-threshold" path="strongSpamThreshold"/>
	<acme:input-textbox code="administrator.system-configuration.form.label.weak-spam-words" path="weakSpamWords"/>
	<acme:input-double code="administrator.system-configuration.form.label.weak-spam-threshold" path="weakSpamThreshold"/>
	
</acme:form>