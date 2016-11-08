package it.redhat.demo.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import it.redhat.demo.service.SecuredEjb;

@Path("")
public class RestService {
	
	@Inject
	private SecuredEjb service;

	@GET
	public String ciao() {
		return "ciao";
	}
	
	@GET
	@Path("all")
	public String all() {
		return service.all();
	}
	
	@GET
	@Path("manager")
	public String manager() {
		return service.manager();
	}
	
	@GET
	@Path("user")
	public String user() {
		return service.user();
	}
	
	@GET
	@Path("admin")
	public String admin() {
		return service.admin();
	}
	
}
