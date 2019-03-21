package com.example.LearnMySQL_Final;

import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class welcomeUI extends HorizontalLayout implements View{
	public welcomeUI() {
		final VerticalLayout layout = new VerticalLayout();
		setHeight("100%");
		setWidth("100%");
		
		User u = new User();
		
		Label label = new Label("Welcome to LearnMySQL: " + u.person.getName());
		
		Button practice= new Button("Practice");
		Button Learn = new Button("Learn");
		Button Assesment= new Button("Assesment");
		
		practice.addClickListener(e -> {
			removeAllComponents();
			addComponent(new practiceUI());
		});
		
		
		
		Panel historyPanel = new Panel("History Panel");
		historyPanel.setContent(new HistoryTab());
		
		layout.addComponents(label,practice,Learn,Assesment);
		layout.setComponentAlignment(practice , Alignment.MIDDLE_LEFT);
		layout.setComponentAlignment(label, Alignment.TOP_CENTER);
		layout.setComponentAlignment(Learn, Alignment.MIDDLE_LEFT);
		layout.setComponentAlignment(Assesment, Alignment.MIDDLE_LEFT);
		
		
		addComponents(layout,historyPanel);
		
		
		
		
		
		
		
		
	}
}
