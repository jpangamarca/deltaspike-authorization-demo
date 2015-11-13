package net.jpangamarca.security;

import org.apache.deltaspike.security.api.authorization.SecurityViolation;

public class SimpleSecurityViolation implements SecurityViolation {

	private static final long serialVersionUID = 1L;
	
	private final String reason;
	
	public SimpleSecurityViolation(String reason) {
		this.reason = reason;
	}

	@Override
	public String getReason() {
		return this.reason;
	}
	
}
