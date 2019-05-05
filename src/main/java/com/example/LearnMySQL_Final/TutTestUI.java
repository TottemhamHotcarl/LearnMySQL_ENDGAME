package com.example.LearnMySQL_Final;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

public class TutTestUI extends Panel implements View{

static Button backUI = new Button("Back");
	
	
	


	public TutTestUI() {
		// TODO Auto-generated constructor stub
	
		setHeight("100%");
		User u = new User();
		final VerticalLayout hl = new VerticalLayout();
		TextArea area2 = new TextArea("Question 1");
		TextArea area3 = new TextArea("Question 2");
		backUI.setClickShortcut(KeyCode.ESCAPE);
		String s = "Create a table called STUDENT with two columns: + \n"
				+ "Column 1: Called STUDENT_NO which is a primary key + \n"
				+ "Column 2: Called STUDENT_EMAIL which is a VARCHAR of length 25";
		
		String s2 = "Insert into the following in STUDENT table: + \n"
				+ "1606558 with 1606558@students.wits.ac.za";
		
		
		area2.setHeightUndefined();
		area3.setHeightUndefined();
		
		
		area2.setValue(s);
		area2.setWidth("100%");
		area3.setValue(s2);
		area3.setWidth("100%");
		area2.setHeightUndefined();
		hl.addComponent(area2);
		hl.addComponent(new TheQueryBoxTut(""));
		hl.addComponent(area3);
		hl.addComponent(new TheQueryBoxTut(""));
		
		hl.addComponent(backUI);
		hl.setWidth("100%");
		hl.setHeightUndefined();
		
		setContent(hl);
		
		
	}
	
	
}
