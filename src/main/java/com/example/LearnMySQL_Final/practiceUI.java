package com.example.LearnMySQL_Final;

import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class practiceUI extends VerticalLayout implements View{

	public practiceUI() {
		setHeight("100%");
		setWidth("100%");
		Panel historyPanel = new Panel("History Panel");
		historyPanel.setContent(new HistoryTab());
		Button QueryBox = new Button("The Query Box");
		QueryBox.addClickListener(e -> {
			removeAllComponents();
			Panel p = new TheQueryBox("");
			addComponent(p);
			
		
		});
		
		addComponents(QueryBox,historyPanel);
		setComponentAlignment( historyPanel, Alignment.TOP_RIGHT);
		
	}
}
