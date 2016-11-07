package it.redhat.demo.interceptor;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Logged 
@Interceptor
public class LoggerInteceptor implements Serializable {
	
	@Inject
	private Logger log;
	
	@AroundInvoke
	public Object logIt(InvocationContext ctx) throws Exception {
		log.info("log it!");
		
		return ctx.proceed();
	}

}
