package net.jpangamarca.views;

import javax.enterprise.context.ApplicationScoped;

import net.jpangamarca.employees.EmployeeEditAccessDecisionVoter;

import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.spi.config.view.ViewConfigRoot;
import org.apache.deltaspike.jsf.api.config.view.Folder;
import org.apache.deltaspike.jsf.api.config.view.View;
import org.apache.deltaspike.jsf.api.config.view.View.NavigationMode;
import org.apache.deltaspike.security.api.authorization.Secured;

@ApplicationScoped
@ViewConfigRoot
@Folder(name = "/")
public interface Views extends ViewConfig {
	
	@View(navigation = NavigationMode.REDIRECT)
	public class Index implements ViewConfig {}

	@Folder
	public interface Hr extends ViewConfig {
		
		@View(navigation = NavigationMode.REDIRECT)
		@Secured(value = EmployeeEditAccessDecisionVoter.class)
		public class Employee implements ViewConfig { }
		
	}

}
