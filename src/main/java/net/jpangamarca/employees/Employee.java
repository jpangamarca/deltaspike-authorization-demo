package net.jpangamarca.employees;

public class Employee {
	
	private Long id;
	private String name;
	
	public Employee(Long id, String name) {
		this.id = id;
		this.setName(name);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
