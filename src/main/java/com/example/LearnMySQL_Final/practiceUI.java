package com.example.LearnMySQL_Final;

import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class practiceUI extends HorizontalLayout implements View{

	public practiceUI() {
		setHeight("100%");
		setWidth("100%");
		Panel historyPanel = new HistoryTab();
		Button QueryBox = new Button("The Query Box");
		HorizontalLayout hl = new HorizontalLayout();
		Button back= new Button("Back");
		
		back.addClickListener(e->{
			removeAllComponents();
			addComponent(new welcomeUI());
		});
		
		
		
		QueryBox.addClickListener(e -> {
			removeAllComponents();
			Panel p = new TheQueryBox("");
			Panel p2 = new HistoryTab();
			
			
			
			hl.addComponents(p,p2);
			hl.setWidth("100%");
			hl.setHeight("100%");
			hl.setExpandRatio(p, .60f);
			hl.setExpandRatio(p2, .40f);
			addComponent(hl);
			
			TheQueryBox.back.addClickListener(e4->{
				removeAllComponents();
				addComponent(new welcomeUI());
			});

		
		});
		
		
		
		
		addComponents(QueryBox,historyPanel);
		setComponentAlignment( historyPanel, Alignment.TOP_RIGHT);
		
	}
}
