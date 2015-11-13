package net.jpangamarca.employees;

import java.util.Collections;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.apache.deltaspike.security.api.authorization.AccessDecisionVoter;
import org.apache.deltaspike.security.api.authorization.AccessDecisionVoterContext;
import org.apache.deltaspike.security.api.authorization.SecurityViolation;

import net.jpangamarca.security.SimpleSecurityViolation;
import net.jpangamarca.security.UserSession;
import net.jpangamarca.users.EmployeeEditController;
import net.jpangamarca.util.Log;

@RequestScoped
public class EmployeeEditAccessDecisionVoter implements AccessDecisionVoter {
	
	public static final long serialVersionUID = 1L;
	
	@Inject
	private EmployeeEditController employeeEditCtlr;
	@Inject
	private UserSession userSession;
	@Inject @Log
	private Logger log;

	@Override
	public Set<SecurityViolation> checkPermission(
			AccessDecisionVoterContext accessDecisionVoterContext) {
		
		if (this.userSession.isLoggedIn()) {
			this.log.log(Level.INFO, "Right now, current action for controller is {0}",
					this.employeeEditCtlr.getId() == null? "CREATE" : "EDIT");
			String requiredPermission = this.employeeEditCtlr.getId() == null
					? "employee.create" : "employee.edit";
			this.log.log(Level.INFO, "Required permission is {0}", requiredPermission);
			 
			if (this.userSession.hasPermission(requiredPermission)) {
				this.log.info("Authorized!");
				return Collections.emptySet();
			} else {
				this.log.severe("Not authorized.");
				return Collections.singleton(new SimpleSecurityViolation("Not authorized."));
			}
		} else {
			this.log.severe("Not logged in.");
			return Collections.singleton(new SimpleSecurityViolation("Not logged in."));
		}
		
	}
	
}
