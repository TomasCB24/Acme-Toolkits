<%--
- company.jsp
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

<h1><acme:message code="master.company.title"/></h1>

<p><acme:message code="master.company.text"/></p>

<address>
  <strong><acme:message code="master.company.name"/></strong> <br/>
  <span class="fas fa-map-marker"> &nbsp; </span><acme:message code="master.company.address"/> <br/>
  <span class="fa fa-phone"></span> &nbsp; <acme:message code="master.company.phone"/><br/>
  <span class="fa fa-at"></span> &nbsp; <a href="mailto:<acme:message code="master.company.email"/>"><acme:message code="master.company.email"/></a> <br/>
</address>

