package net.jpangamarca.security;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.deltaspike.core.api.config.view.ViewConfig;

import net.jpangamarca.users.User;
import net.jpangamarca.views.Views;

@SessionScoped
@Named
public class UserSession implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private User user;
	
	private List<User> users = 
			Arrays.asList(
					new User("john", "john", new HashSet<>(Arrays.asList("employee.create", "employee.edit"))),
					new User("peter", "peter", new HashSet<>(Arrays.asList("employee.edit"))));
	
	@PostConstruct
	private void init() {
		this.user = new User("john", "john", new HashSet<>(Arrays.asList("employee.create", "employee.edit")));
	}
	
	public Boolean isLoggedIn() {
		return true;
	}
	
	public User getUser() {
		return user;
	}
	
	// just to simplify demo...
	public Class<? extends ViewConfig> changeUser(User user) {
		this.user = user;
		return Views.Index.class;
	}
	
	public boolean hasPermission(String permissionCode) {
		return this.user.getPermissions().contains(permissionCode);
	}

	public List<User> getUsers() {
		return users;
	}
	
}
