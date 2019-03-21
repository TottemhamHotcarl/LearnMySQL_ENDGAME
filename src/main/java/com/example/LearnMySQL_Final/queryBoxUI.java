package com.example.LearnMySQL_Final;

import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

public class queryBoxUI extends HorizontalLayout implements View {
	public queryBoxUI() {
		removeAllComponents();
		setHeight("100%");
		setWidth("100%");
		Panel historyPanel = new Panel("History Panel");
		historyPanel.setContent(new HistoryTab());
		
		Panel queryPanel = new Panel("Query Panel");
		queryPanel.setHeight("100%");
		queryPanel.setContent(new TheQueryBox());
		
		
		
		addComponents(queryPanel,historyPanel);
		setComponentAlignment( historyPanel, Alignment.TOP_RIGHT);
		
	}
} 


