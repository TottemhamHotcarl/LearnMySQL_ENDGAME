package com.example.LearnMySQL_Final;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;

public class SQL_help extends Panel implements View{
	static Button backUI =new Button("Back");
	public SQL_help(){
		setHeight("100%");
		User u = new User();
		String s = "Here is a list of SQL statements. You can use this to check syntax:\n"
				+ "ALTER TABLE table_name;\n" + 
				"ADD column_name datatype;\n"
				+ "SELECT column_name(s)\r\n" + 
				"FROM table_name;\n" + 
				"WHERE column_1 = value_1;\n" + 
				"  AND column_2 = value_2;\n"
				+"\n"
				+ "Back Button: Esc \n";
		
		final HorizontalLayout hl = new HorizontalLayout();
		TextArea area2 = new TextArea("SQL HELP DOCUMENTS");
		backUI.setClickShortcut(KeyCode.ESCAPE);
	
		area2.setValue(s);
		area2.setWidth("100%");
		area2.setHeight("100");
		area2.setEnabled(false);
		
		backUI.setClickShortcut(KeyCode.ESCAPE);
		
		hl.addComponent(area2);
		hl.addComponent(backUI);
		hl.setWidth("100%");
		hl.setHeight("100%");
		hl.setSizeFull();
		setContent(hl);
		
	}
}
