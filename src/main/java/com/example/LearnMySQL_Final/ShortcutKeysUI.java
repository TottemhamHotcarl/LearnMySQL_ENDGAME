package com.example.LearnMySQL_Final;

import java.io.File;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;


public class ShortcutKeysUI extends Panel implements View {
	static Button backUI =new Button("Back");
	
	public ShortcutKeysUI(){
		
		// Find the application directory
		String basepath = VaadinService.getCurrent()
		                  .getBaseDirectory().getAbsolutePath();

		// Image as a file resource
		FileResource resource = new FileResource(new File(basepath +
		                        "/WEB-INF/images/back.png"));
		

		
		backUI.setStyleName(ValoTheme.BUTTON_LINK);
		backUI.setIcon(resource);
		
		
		setHeight("100%");
		User u = new User();
		String s = "Note the following shortcuts for the Query Box interface:\n \n"
				+ "Execute Code: F1\n"
				+ "Clear the InPut Area: F3\n"
				+"Save Query: F2\n"
				+ "Back Button: Esc";
		
		final VerticalLayout hl = new VerticalLayout();
		TextArea area2 = new TextArea();
		area2.addStyleName(ValoTheme.TEXTAREA_BORDERLESS);
		area2.addStyleName(ValoTheme.TEXTAREA_HUGE);
		area2.addStyleName(ValoTheme.TEXTAREA_ALIGN_CENTER);
		backUI.setClickShortcut(KeyCode.ESCAPE);
	
		area2.setValue(s);
		area2.setWidth("100%");
		area2.setHeight("100%");
		hl.addComponent(backUI);
		hl.addComponent(area2);
	
		hl.setWidth("100%");
		hl.setHeight("100%");
		hl.setExpandRatio(backUI, .05f);
		hl.setExpandRatio(area2, 0.95f);
		setContent(hl);
		
		
	}

}


