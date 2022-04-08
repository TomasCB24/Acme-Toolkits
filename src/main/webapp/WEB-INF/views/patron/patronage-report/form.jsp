<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="patron.patronage-report.form.label.serial-number" path="serialNumber"/>
	<acme:input-moment code="patron.patronage-report.form.label.creation-moment" path="creationMoment"/>
	<acme:input-textarea code="patron.patronage-report.form.label.memorandum" path="memorandum"/>
	<acme:input-textbox code="patron.patronage-report.form.label.link" path="link"/>	
</acme:form>