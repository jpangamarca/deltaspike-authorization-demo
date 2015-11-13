package net.jpangamarca.users;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class User {
	
	public final String username;
	public final String password;
	public final Set<String> permissions;
	
	public User(String username, String password, Set<String> permissions) {
		this.username = username;
		this.password = password;
		this.permissions = Collections.unmodifiableSet(new LinkedHashSet<>(permissions));
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Set<String> getPermissions() {
		return permissions;
	}

}
