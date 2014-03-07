package ca.zhoozhoo.examples.springwicket.ui;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

import ca.zhoozhoo.examples.springwicket.ui.pages.HelloWorld;

public class ExampleWebApplication extends WebApplication {

	public ExampleWebApplication() {
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return HelloWorld.class;
	}
}
