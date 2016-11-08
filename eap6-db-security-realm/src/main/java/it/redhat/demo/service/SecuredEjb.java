package it.redhat.demo.service;

import java.security.Principal;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

@Stateless
@DeclareRoles({"manager", "admin", "user"})
@SecurityDomain("myDom")
public class SecuredEjb {
	
	@Resource
	public SessionContext context;
	
	@PermitAll
	public String all() {
		Principal principal = context.getCallerPrincipal();
		
		return "ALL " + principal.getName();
	}
	
	@RolesAllowed("user")
	public String user() {
		Principal principal = context.getCallerPrincipal();
		
		return "USER " + principal.getName();
	}
	
	@RolesAllowed("manager")
	public String manager() {
		Principal principal = context.getCallerPrincipal();
		
		return "MANAGER " + principal.getName();
	}
	
	@RolesAllowed("admin")
	public String admin() {
		Principal principal = context.getCallerPrincipal();
		
		return "ADMIN " + principal.getName();
	}

}
