package com.example.LearnMySQL_Final;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;


public class ShortcutKeysUI extends Panel implements View {
	static Button backUI =new Button("Back");
	
	public ShortcutKeysUI(){
		setHeight("100%");
		User u = new User();
		String s = "Note the following shortcuts for the Query Box interface:\n"
				+ "Execute Code: Fn + F1\n"
				+ "Clear the InPut Area: Fn + F3\n"
				+"Save Query: Fn + F2\n"
				+ "Back Button: Esc \n";
		
		final HorizontalLayout hl = new HorizontalLayout();
		TextArea area2 = new TextArea("Info");
		backUI.setClickShortcut(KeyCode.ESCAPE);
	
		area2.setValue(s);
		area2.setWidth("100%");
		area2.setHeightUndefined();
		hl.addComponent(area2);
		hl.addComponent(backUI);
		hl.setWidth("100%");
		hl.setHeight("100%");
		setContent(hl);
		
	}

}


