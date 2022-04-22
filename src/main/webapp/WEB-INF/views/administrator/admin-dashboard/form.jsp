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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<h2>
	<acme:message code="administrator.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-of-components"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfComponents}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-of-tools"/>
		</th>
		<td>
			<acme:print value="${tools}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-of-proposed-patronages"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfProposedPatronages}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-of-accepted-patronages"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfAcceptedPatronages}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-of-denied-patronages"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfDeniedPatronages}"/>
		</td>
	</tr>	
</table>

<acme:message code="administrator.dashboard.form.label.averageTitle"/>
<table class="table table-sm">
		<tr>
			<th>
				<acme:message code ="administrator.dashboard.form.label.components"/>
			</th>	
		</tr>
	<c:forEach items="${averageOfComponentsRetailPrice}" var="entry"> 	
		<tr>	
			<th>	
			<acme:message code="administrator.dashboard.form.label.average-sentence"/>		
			<c:set var = "string1" value = "${entry.key}"/>
			<c:set var = "string2" value = "${fn:split(string1, '->')}" />
			<acme:print value="${string2[0]}"/>
			<acme:message code="administrator.dashboard.form.label.and-technology"/>
			<acme:print value="${string2[1]}"/>
			<acme:message code="administrator.dashboard.form.label.colon"/>
			</th>
			<td>
				<acme:print value="${entry.value}"/>
				
			</td>		
		</tr>
	</c:forEach>
		<tr>
			<th>
				<acme:message code ="administrator.dashboard.form.label.tools"/>
			</th>	
		</tr>
	
	<c:forEach items="${averageOfToolsRetailPrice}" var="entry"> 	
		<tr>	
			<th>	
			<acme:message code="administrator.dashboard.form.label.average-sentence"/>		
			<c:set var = "string1" value = "${entry.key}"/>
			<c:set var = "string2" value = "${fn:split(string1, '->')}" />

			<acme:print value="${string2[0]}"/>
			<acme:message code="administrator.dashboard.form.label.colon"/>
			</th>
			<td>
				<acme:print value="${entry.value}"/>
				
			</td>		
		</tr>
		</c:forEach>
		
		<tr>
			<th>
				<acme:message code ="administrator.dashboard.form.label.patronages"/>
			</th>	
		</tr>
		
		<c:forEach items="${averageBudgetPatronages}" var="entry"> 	
			<tr>	
				<th>	
				<acme:message code="administrator.dashboard.form.label.average-sentence"/>		
				<c:set var = "string1" value = "${entry.key}"/>
				<c:set var = "string2" value = "${fn:split(string1, '->')}" />
				<acme:print value="${string2[0]}"/>
				<acme:message code="administrator.dashboard.form.label.patronage-average-sentence-status"/>
				<acme:print value="${string2[1]}"/>
				<acme:message code="administrator.dashboard.form.label.colon"/>
				</th>
				<td>
					<acme:print value="${entry.value}"/>
					
				</td>		
			</tr>
		</c:forEach>
</table>

<acme:message code="administrator.dashboard.form.label.deviationTitle"/>	
	<table class="table table-sm">	
		<tr>
			<th>
				<acme:message code ="administrator.dashboard.form.label.components"/>
			</th>	
		</tr>	
		<c:forEach items="${deviationOfComponentsRetailPrice}" var="entry"> 	
		<tr>	
			<th>	
			<acme:message code="administrator.dashboard.form.label.deviation-sentence"/>		
			<c:set var = "string1" value = "${entry.key}"/>
			<c:set var = "string2" value = "${fn:split(string1, '->')}" />
			<acme:print value="${string2[0]}"/>
			<acme:message code="administrator.dashboard.form.label.and-technology"/>
			<acme:print value="${string2[1]}"/>
			<acme:message code="administrator.dashboard.form.label.colon"/>
			</th>
			<td>
				<acme:print value="${entry.value}"/>
				
			</td>		
		</tr>
		</c:forEach>
		
		<tr>
			<th>
				<acme:message code ="administrator.dashboard.form.label.tools"/>
			</th>	
		</tr>
		<c:forEach items="${deviationOfToolsRetailPrice}" var="entry"> 	
		<tr>	
			<th>	
			<acme:message code="administrator.dashboard.form.label.deviation-sentence"/>		
			<c:set var = "string1" value = "${entry.key}"/>
			<c:set var = "string2" value = "${fn:split(string1, '->')}" />

			<acme:print value="${string2[0]}"/>
			<acme:message code="administrator.dashboard.form.label.colon"/>
			</th>
			<td>
				<acme:print value="${entry.value}"/>
				
			</td>		
		</tr>
		</c:forEach>
		
		<tr>
			<th>
				<acme:message code ="administrator.dashboard.form.label.patronages"/>
			</th>	
		</tr>
		<c:forEach items="${deviationBudgetPatronages}" var="entry"> 	
		<tr>	
				<th>	
				<acme:message code="administrator.dashboard.form.label.deviation-sentence"/>		
				<c:set var = "string1" value = "${entry.key}"/>
				<c:set var = "string2" value = "${fn:split(string1, '->')}" />
				<acme:print value="${string2[0]}"/>
				<acme:message code="administrator.dashboard.form.label.patronage-deviation-sentence-status"/>
				<acme:print value="${string2[1]}"/>
				<acme:message code="administrator.dashboard.form.label.colon"/>
				</th>
				<td>
					<acme:print value="${entry.value}"/>
					
				</td>		
			</tr>
		</c:forEach>
	</table>
	<acme:message code="administrator.dashboard.form.label.minTitle"/>	
	<table class="table table-sm">	
	
		<tr>
			<th>
				<acme:message code ="administrator.dashboard.form.label.components"/>
			</th>	
		</tr>
		<c:forEach items="${minimumOfComponentsRetailPrice}" var="entry"> 	
		<tr>	
			<th>	
			<acme:message code="administrator.dashboard.form.label.min-sentence"/>		
			<c:set var = "string1" value = "${entry.key}"/>
			<c:set var = "string2" value = "${fn:split(string1, '->')}" />
			<acme:print value="${string2[0]}"/>
			<acme:message code="administrator.dashboard.form.label.and-technology"/>
			<acme:print value="${string2[1]}"/>
			<acme:message code="administrator.dashboard.form.label.colon"/>
			</th>
			<td>
				<acme:print value="${entry.value}"/>
				
			</td>		
		</tr>
		</c:forEach>
		
		<tr>
			<th>
				<acme:message code ="administrator.dashboard.form.label.tools"/>
			</th>	
		</tr>
		<c:forEach items="${minimumOfToolsRetailPrice}" var="entry"> 	
		<tr>	
			<th>	
			<acme:message code="administrator.dashboard.form.label.min-sentence"/>		
			<c:set var = "string1" value = "${entry.key}"/>
			<c:set var = "string2" value = "${fn:split(string1, '->')}" />

			<acme:print value="${string2[0]}"/>
			<acme:message code="administrator.dashboard.form.label.colon"/>
			</th>
			<td>
				<acme:print value="${entry.value}"/>
				
			</td>		
		</tr>
		</c:forEach>
		
		<tr>
			<th>
				<acme:message code ="administrator.dashboard.form.label.patronages"/>
			</th>	
		</tr>
		<c:forEach items="${minimumBudgetPatronages}" var="entry"> 	
		<tr>	
				<th>	
				<acme:message code="administrator.dashboard.form.label.min-sentence"/>		
				<c:set var = "string1" value = "${entry.key}"/>
				<c:set var = "string2" value = "${fn:split(string1, '->')}" />
				<acme:print value="${string2[0]}"/>
				<acme:message code="administrator.dashboard.form.label.patronage-min-sentence-status"/>
				<acme:print value="${string2[1]}"/>
				<acme:message code="administrator.dashboard.form.label.colon"/>
				</th>
				<td>
					<acme:print value="${entry.value}"/>
					
				</td>		
			</tr>
		</c:forEach>
	</table>
	
	<acme:message code="administrator.dashboard.form.label.maxTitle"/>
	
			
	<table class="table table-sm">
		<tr>
			<th>
				<acme:message code ="administrator.dashboard.form.label.components"/>
			</th>	
		</tr>	
		<c:forEach items="${maximumOfComponentsRetailPrice}" var="entry"> 	
		<tr>	
			<th>	
			<acme:message code="administrator.dashboard.form.label.max-sentence"/>		
			<c:set var = "string1" value = "${entry.key}"/>
			<c:set var = "string2" value = "${fn:split(string1, '->')}" />
			<acme:print value="${string2[0]}"/>
			<acme:message code="administrator.dashboard.form.label.and-technology"/>
			<acme:print value="${string2[1]}"/>
			<acme:message code="administrator.dashboard.form.label.colon"/>
			</th>
			<td>
				<acme:print value="${entry.value}"/>
				
			</td>		
		</tr>
		</c:forEach>
		
		<tr>
			<th>
				<acme:message code ="administrator.dashboard.form.label.tools"/>
			</th>	
		</tr>
		<c:forEach items="${maximumOfToolsRetailPrice}" var="entry"> 	
		<tr>	
			<th>	
			<acme:message code="administrator.dashboard.form.label.max-sentence"/>		
			<c:set var = "string1" value = "${entry.key}"/>
			<c:set var = "string2" value = "${fn:split(string1, '->')}" />

			<acme:print value="${string2[0]}"/>
			<acme:message code="administrator.dashboard.form.label.colon"/>
			</th>
			<td>
				<acme:print value="${entry.value}"/>
				
			</td>		
		</tr>
		</c:forEach>
		
		<tr>
			<th>
				<acme:message code ="administrator.dashboard.form.label.patronages"/>
			</th>	
		</tr>
		<c:forEach items="${maximumBudgetPatronages}" var="entry"> 	
		<tr>	
				<th>	
				<acme:message code="administrator.dashboard.form.label.max-sentence"/>		
				<c:set var = "string1" value = "${entry.key}"/>
				<c:set var = "string2" value = "${fn:split(string1, '->')}" />
				<acme:print value="${string2[0]}"/>
				<acme:message code="administrator.dashboard.form.label.patronage-max-sentence-status"/>
				<acme:print value="${string2[1]}"/>
				<acme:message code="administrator.dashboard.form.label.colon"/>
				</th>
				<td>
					<acme:print value="${entry.value}"/>
					
				</td>		
			</tr>
		</c:forEach>
	</table>
	


<h2>
	<acme:message code="administrator.dashboard.form.title.application-statuses"/>
</h2>

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
						<c:out value="${totalNumberOfProposedPatronages}"/>, 
						<c:out value="${totalNumberOfAcceptedPatronages}"/>, 
						<c:out value="${totalNumberOfDeniedPatronages}"/>
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

