<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<acme:message code="patron.patron-dashboard.form.label.avg-title"/>	
<table class="table table-sm" aria-describedby="Patron dashboard - Average">	
	<jstl:forEach items="${averageBudgetOfPatronages}" var="entry"> 	
	<tr>	
		<th scope="row">	
		<acme:message code="patron.patron-dashboard.form.label.average-sentence"/>		
		<jstl:set var = "string1" value = "${entry.key}"/>
		<jstl:set var = "string2" value = "${fn:split(string1, '->')}" />
		<acme:print value="${string2[0]}"/>
		<acme:message code="patron.patron-dashboard.form.label.and-status"/>
		<acme:print value="${string2[1]}"/>
		<acme:message code="patron.patron-dashboard.form.label.colon"/>
		</th>
		<td>
			<acme:print value="${entry.value}"/>
			
		</td>		
	</tr>
	</jstl:forEach>
</table>

<acme:message code="patron.patron-dashboard.form.label.deviationTitle"/>		
<table class="table table-sm" aria-describedby="Patron dashboard - Desviation">
	<jstl:forEach items="${deviationBudgetOfPatronages}" var="entry"> 
	<tr>	
		<th scope="row">
			<acme:message code="patron.patron-dashboard.form.label.deviation-sentence"/>		
			<jstl:set var = "string1" value = "${entry.key}"/>
			<jstl:set var = "string2" value = "${fn:split(string1, '->')}" />
			<acme:print value="${string2[0]}"/>
			<acme:message code="patron.patron-dashboard.form.label.and-status"/>		
			<acme:print value="${string2[1]}"/>
			<acme:message code="patron.patron-dashboard.form.label.colon"/>		
		</th>
		<td>
			<acme:print value="${entry.value}"/>
		</td>		
	</tr>
	</jstl:forEach>
</table>


<acme:message code="patron.patron-dashboard.form.label.minTitle"/>
<table class="table table-sm" aria-describedby="Patron dashboard - Minimum">	
	<jstl:forEach items="${minBudgetOfPatronages}" var="entry"> 
		<tr>	
			<th scope="row">
				<acme:message code="patron.patron-dashboard.form.label.min-sentence"/>		
				<jstl:set var = "string1" value = "${entry.key}"/>
				<jstl:set var = "string2" value = "${fn:split(string1, '->')}" />
				<acme:print value="${string2[0]}"/>
				<acme:message code="patron.patron-dashboard.form.label.and-status"/>		
				<acme:print value="${string2[1]}"/>
				<acme:message code="patron.patron-dashboard.form.label.colon"/>		
			</th>
			<td>
				<acme:print value="${entry.value}"/>
			</td>		
		</tr>
	</jstl:forEach>
</table>		
<acme:message code="patron.patron-dashboard.form.label.maxTitle"/>		
<table class="table table-sm" aria-describedby="Patron dashboard - Maximum">
	<jstl:forEach items="${maxBudgetOfPatronages}" var="entry"> 
		<tr>	
			<th scope="row">
				<acme:message code="patron.patron-dashboard.form.label.max-sentence"/>		
				<jstl:set var = "string1" value = "${entry.key}"/>
				<jstl:set var = "string2" value = "${fn:split(string1, '->')}" />
				<acme:print value="${string2[0]}"/>
				<acme:message code="patron.patron-dashboard.form.label.and-status"/>		
				<acme:print value="${string2[1]}"/>
				<acme:message code="patron.patron-dashboard.form.label.colon"/>		
			</th>
			<td>
				<acme:print value="${entry.value}"/>
			</td>		
		</tr>
	</jstl:forEach>
</table>
<acme:message code="patron.patron-dashboard.form.label.absoluteTitle"/>		
<table class="table table-sm" aria-describedby="Patron dashboard - Total number">
		<tr>	
			<th scope="row">
				<acme:message code="patron.patron-dashboard.form.label.totalNumberOfProposedPatronages"/>		
	
			</th>
			<td>
				<acme:print value="${totalNumberOfProposedPatronages}"/>
			</td>		
		</tr>
		<tr>	
			<th scope="row">
				<acme:message code="patron.patron-dashboard.form.label.totalNumberOfAcceptedPatronages"/>
			</th>
			<td>
				<acme:print value="${totalNumberOfAcceptedPatronages}"/>
			</td>		
		</tr>
		<tr>	
			<th scope="row">
				<acme:message code="patron.patron-dashboard.form.label.totalNumberOfDeniedPatronages"/>		
			</th>
			<td>
				<acme:print value="${totalNumberOfDeniedPatronages}"/> 
			</td>		
		</tr>
</table>
	
<div>
	<canvas id="canvas"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {			
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0
						}
					}
				]
			},
			labels : [
					"PROPOSED", "ACCEPTED", "DENIED"
			],
			datasets : [
				{
					data : [
						<jstl:out value="${totalNumberOfProposedPatronages}"/>, 
						<jstl:out value="${totalNumberOfAcceptedPatronages}"/>, 
						<jstl:out value="${totalNumberOfDeniedPatronages}"/>
					]
				}
			]
		};
		var options = {
			legend : {
				display : false
			}
		};
	
		var canvas, context;
	
		canvas = document.getElementById("canvas");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>

<acme:return/>

	
	