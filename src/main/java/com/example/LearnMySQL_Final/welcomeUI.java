package com.example.LearnMySQL_Final;


import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
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
		
		Button queryBox = new Button("queryBox");
		
		Button localInfo = new Button("Terminal Info");
		
		
		HorizontalLayout hl = new HorizontalLayout();
		queryBox.addClickListener(e -> {
			HorizontalSplitPanel hsp = new HorizontalSplitPanel();
			hsp.addComponent(new TheQueryBox(""));
			hsp.addComponent(new HistoryTab());
			
			removeAllComponents();
			addComponent(hsp);

			
			TheQueryBox.back.addClickListener(e4->{
				removeAllComponents();
				addComponent(new welcomeUI());
			});

		});
		
		localInfo.addClickListener(e4->{
			removeAllComponents();
			addComponent(new InfoUI());
		});
		
		
		InfoUI.backUI.addClickListener(e5->{
			removeAllComponents();
			addComponent(new welcomeUI());
		});
		
		
		
		Panel historyPanel = new Panel("History Panel");
		historyPanel.setContent(new HistoryTab());
		
		layout.addComponents(label,queryBox,localInfo);
		layout.setComponentAlignment(queryBox , Alignment.MIDDLE_LEFT);
		layout.setComponentAlignment(label, Alignment.TOP_CENTER);
		

		
		
		addComponents(layout,historyPanel);
		
		
		
	}
}
