package ca.zhoozhoo.examples.springwicket.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

import ca.zhoozhoo.examples.springwicket.ui.pages.HelloWorld;

public class WicketApplication extends WebApplication {

	static final Logger logger = LogManager.getLogger(WicketApplication.class.getName());

	@Override
	public Class<? extends Page> getHomePage() {
		return HelloWorld.class;
	}
}
