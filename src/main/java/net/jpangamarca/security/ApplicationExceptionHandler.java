package net.jpangamarca.security;

import java.util.Iterator;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import net.jpangamarca.util.Log;
import net.jpangamarca.views.Views;

import org.apache.deltaspike.core.api.config.view.navigation.ViewNavigationHandler;
import org.apache.deltaspike.core.api.exception.control.ExceptionHandler;
import org.apache.deltaspike.core.api.exception.control.Handles;
import org.apache.deltaspike.core.api.exception.control.event.ExceptionEvent;
import org.apache.deltaspike.security.api.authorization.AccessDeniedException;
import org.apache.deltaspike.security.api.authorization.SecurityViolation;
import org.omnifaces.util.Messages;

@ApplicationScoped
@ExceptionHandler
public class ApplicationExceptionHandler {
	
	@Inject @Log
	private Logger log;
	@Inject
	private ViewNavigationHandler vnh;
	
	public void handleAcccesDeniedException(@Handles ExceptionEvent<AccessDeniedException> exceptionEvent) {
		this.clearFacesMessages();
		exceptionEvent.getException().getViolations().stream()
			.map(SecurityViolation::getReason)
			.forEach(r -> Messages.addFlashError(null, r));
		this.vnh.navigateTo(Views.Index.class);
		exceptionEvent.handled();
	}
	
	private void clearFacesMessages() {
		Iterator<FacesMessage> i = FacesContext.getCurrentInstance().getMessages();
		
		while (i.hasNext()) {
			i.next();
			i.remove();
		}
	}
	
}
