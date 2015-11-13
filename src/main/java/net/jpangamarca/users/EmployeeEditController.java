package net.jpangamarca.users;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import net.jpangamarca.util.Log;

@Named
@ConversationScoped
public class EmployeeEditController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject @Log
	private Logger log;
	
	private Long id;
	
	public void logCurrentAction() {
		this.log.info(this.getCurrentAction());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.log.log(Level.INFO, "Setting id = {0}", id);
		this.id = id;
	}
	
	public String getCurrentAction() {
		return this.getId() == null
				? "I'm creating a new employee"
				: "I'm editing the employee with id " + this.getId();
	}
	
}
