package com.example.LearnMySQL_Final;


import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;

public class InfoUI extends Panel implements View{
	
	static Button backUI = new Button("Back");
	
	
	public InfoUI() {
		setHeight("100%");
		User u = new User();
		String s = "You can connect to the database using the terminal use:\n"
				+ "ssh d" + u.person.id +"@1606558.ms.wits.ac.za:\n"
						+ "\n"
						+ "sudo mysql -u d" + u.person.id+"\n"
								+ "your password is d" + u.person.id;
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
