package net.jpangamarca.views;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.apache.deltaspike.core.api.config.view.ViewConfig;

@ApplicationScoped
@Named
public class ViewRefs {
	
	public Class<? extends ViewConfig> index() {
		return Views.Index.class;
	}
	
	public Class<? extends ViewConfig> hr() {
		return Views.Hr.class;
	}
	
	public Class<? extends ViewConfig> hrEmployee() {
		return Views.Hr.Employee.class;
	}
	
}
