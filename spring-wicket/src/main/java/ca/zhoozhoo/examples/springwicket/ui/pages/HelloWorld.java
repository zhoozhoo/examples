package ca.zhoozhoo.examples.springwicket.ui.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HelloWorld extends WebPage {

    private static final long serialVersionUID = 1L;

    public HelloWorld() {
        add(new Label("message", "Hello World!"));
    }

    public HelloWorld(IModel<?> model) {
        super(model);
        add(new Label("message", "Hello World!"));
    }

    public HelloWorld(PageParameters parameters) {
        super(parameters);
        add(new Label("message", "Hello World!"));
    }
}
