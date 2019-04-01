package my.vaadin.vaadin_archetype_application;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
     //Create content root layout for the UI
    	VerticalLayout content = new VerticalLayout();
    	setContent(content);
    	Receiver receiver = null;
		Upload upload = new Upload("Upload it here", receiver);
    	upload.setImmediateMode(false);
    	upload.setButtonCaption("Upload Now");
    	
    	//Display the greeting
    	content.addComponent(new Label("Tshepo, How are you my friend?"));
    	
    	//Have a clickable button
    	content.addComponent(new Button("Push me!", click -> Notification.show("I'm goooooood!")));
    	content.addComponent(upload);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
