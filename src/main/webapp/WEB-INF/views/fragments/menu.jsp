<%--
- menu.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
	      	<acme:menu-suboption code="master.menu.anonymous.favourite-link" action="http://www.example.com/"/>
	      	<acme:menu-suboption code="master.menu.anonymous.favourite-link-tomas" action="https://www3.animeflv.net"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-ezequiel" action="https://github.com/ezegonmac/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-ismael" action="https://gdg.community.dev/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-pablo" action="https://www.mangatigre.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-miguel" action="https://developer.android.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-juan" action="https://www.youtube.com/"/>
			<acme:menu-separator/>
		    <acme:menu-suboption code="master.menu.any.user-account-list" action="/any/user-account/list"/>
		    <acme:menu-suboption code="master.menu.any.item.list-published" action="/any/item/list-published"/>
		    <acme:menu-suboption code="master.menu.any.toolkit" action="/any/toolkit/list-published"/>
			<acme:menu-suboption code="master.menu.any.chirp.list-recent" action="/any/chirp/list-recent"/>

		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
      
			<acme:menu-suboption code="master.menu.authenticated.currency-system" action="/authenticated/system-configuration/show"/>
			<acme:menu-suboption code="master.menu.authenticated.list-recent" action="/authenticated/announcement/list-recent"/>
			<acme:menu-suboption code="master.menu.any.chirp.list-recent" action="/any/chirp/list-recent"/>
      		<acme:menu-suboption code="master.menu.any.user-account-list" action="/any/user-account/list"/>
     		<acme:menu-suboption code="master.menu.any.item.list-published" action="/any/item/list-published"/>
      		<acme:menu-suboption code="master.menu.any.toolkit" action="/any/toolkit/list-published"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/admin-dashboard/show"/>
			<acme:menu-suboption code="master.menu.administrator.create" action="/administrator/announcement/create"/>
			<acme:menu-suboption code="master.menu.administrator.system-configuration" action="/administrator/system-configuration/show"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/populate-sample"/>	
			<acme:menu-separator/>																	
			<acme:menu-suboption code="master.menu.administrator.shut-down" action="/administrator/shut-down"/>
			
			
		</acme:menu-option>


		<acme:menu-option code="master.menu.patron" access="hasRole('Patron')">
      <acme:menu-suboption code="master.menu.patron.patronage-report" action="/patron/patronage-report/list-mine"/>
      <acme:menu-suboption code="master.menu.patron.patronage.list" action="/patron/patronage/list-mine"/>
      <acme:menu-suboption code="master.menu.patron.patron-dashboard" action="/patron/patron-dashboard/show"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.inventor" access="hasRole('Inventor')">
    		<acme:menu-suboption code="master.menu.inventor.patronage-report" action="/inventor/patronage-report/list-mine"/>
        <acme:menu-suboption code="master.menu.inventor.toolkit" action="/inventor/toolkit/list-mine"/>
        <acme:menu-suboption code="master.menu.inventor.patronage.list" action="/inventor/patronage/list-mine"/>
      <acme:menu-suboption code="master.menu.inventor.item.list-mine" action="/inventor/item/list-mine"/>
		</acme:menu-option>

		
	</acme:menu-left>
	

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>			
			<acme:menu-suboption code="master.menu.user-account.become-patron" action="/authenticated/patron/create" access="!hasRole('Patron')"/>
			<acme:menu-suboption code="master.menu.user-account.patron" action="/authenticated/patron/update" access="hasRole('Patron')"/>
			<acme:menu-suboption code="master.menu.user-account.become-inventor" action="/authenticated/inventor/create" access="!hasRole('Inventor')"/>
			<acme:menu-suboption code="master.menu.user-account.inventor" action="/authenticated/inventor/update" access="hasRole('Inventor')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

