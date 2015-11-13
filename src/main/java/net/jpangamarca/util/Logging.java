package net.jpangamarca.util;

import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

@ApplicationScoped
public class Logging {
	
	@Produces @Log
	public Logger log(InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint.getMember().getClass().getSimpleName());
	}

}
