package com.example.LearnMySQL_Final;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.UserError;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;

import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI{

	//Label label = new Label("");

	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	final VerticalLayout layout = new VerticalLayout();
    	
    	/*FileUploader receiver = new FileUploader();
    	
    	ProgressBar progressBar=new ProgressBar();
    	progressBar.setVisible(false);
    	progressBar.setIndeterminate(true);
    	progressBar.setCaption("Uploading....");
    	
    	Upload upload =new Upload("Upload file here", receiver);
    	upload.setImmediateMode(false);
    	upload.setButtonCaption("Upload Now");
    	upload.addProgressListener(null);
    	*/
    	
    	
    	Label wel = new Label("Welcome to LearnMySQL");
    	Label label = new Label("");
    	label.setId("Error");
    	
    	
        final TextField username = new TextField();
        final PasswordField password = new PasswordField();
        Button login = new Button("Log In");
        
        username.setCaption("Username");
        password.setCaption("Password");
        
        login.setClickShortcut(KeyCode.ENTER);
	
    
        setContent(layout);
        
           

        
        
        layout.addComponents(wel,username, password,login);
        layout.setComponentAlignment(wel, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(username, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(password, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(login, Alignment.MIDDLE_CENTER);
            
        setContent(layout);

        
        login.addClickListener(e -> {
        
        String user = username.getValue();
        String pass = password.getValue();
        
        boolean everythingOkay = true;
        
        if(user.length() == 0) {
        	username.setComponentError(new UserError("Enter username"));
        	everythingOkay = false;
        }
        else username.setComponentError(null);
        
        if(pass.length() == 0) {
        	password.setComponentError(new UserError("Enter password"));
        	everythingOkay = false;
        }
        else password.setComponentError(null);
        	
        if(everythingOkay) {
            LoginTool lt = new LoginTool(user,pass);
            if(lt.verifyStudentDetails()) {
            		checkFirstTimeLogin();
            		setContent(new welcomeUI());
            		
            }
            else {
        	         	   Notification.show("Login Failed",
                       "Wrong credentials(username or password) provided",
                       Notification.Type.HUMANIZED_MESSAGE);
           }
        }
        });

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    public void checkFirstTimeLogin() {
    	ServerManagementConnection smc = new ServerManagementConnection();
    	User u = new User();
    	Person p = u.person;
    	System.out.println(p.toString());
    	if(smc.isFirstTime(p.getId())) {
    		System.out.println("is not first time");
    		return;
    	}
    	System.out.println("is first time");
    	if(smc.addStudentToStudentTableInDatabase(p)) {
    		smc.addStudentDatabase(p);
    	}
    
    	
    	
    	
    }
}
