package com.example.LearnMySQL_Final;


import java.io.File;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class welcomeUI extends HorizontalLayout implements View{
	public welcomeUI() {
		removeAllComponents();
		VerticalLayout layout = new VerticalLayout();
		setHeight("100%");
		setWidth("100%");
		

		User u = new User();
		
		Label label = new Label("Welcome to LearnMySQL: " + u.person.getName());
		
		
		label.addStyleName(ValoTheme.LABEL_H2);
		label.addStyleName(ValoTheme.LABEL_COLORED);
		
		
		Button queryBox = new Button("queryBox");
		queryBox.setDescription("This is where all the fun is had");
		
		
		Button localInfo = new Button("Terminal Info");
		localInfo.setDescription("How to connect to your database via the Terminal");
		
		Button short_cut_keys= new  Button("Shortcuts");
		
		
		Button Copy_his= new Button("Copy history to clipboard");
		
		Copy_his.setDescription("This lists all your queries that you have run");

		Button SavedQuery= new Button("Copy Saved Query to clipboard");
		SavedQuery.setDescription("This lists all your queries that you have saved");

		Button Groups = new Button("Groups");
		
		Groups.setDescription("This takes you to the groups menu");
		
		// Find the application directory
				String basepath = VaadinService.getCurrent()
				                  .getBaseDirectory().getAbsolutePath();

		// Image as a file resource
		FileResource resource = new FileResource(new File(basepath +
		                        "/WEB-INF/images/SQL_HELP_FILE.pdf"));
		

		Copy_his.addClickListener(e->{
			removeAllComponents();
			addComponent(new Export_history_UI());
		});
		
		SavedQuery.addClickListener(e->{
			removeAllComponents();
			addComponent(new Export_saved_Queries_UI());
		});
		
		

		Link link =  new Link("Link to the SQL Help Files", resource);
				
				
		
		
		
		short_cut_keys.setDescription("A list of all the shortcuts for our project");;
		
		short_cut_keys.addClickListener(e->{
			removeAllComponents();
			addComponent(new ShortcutKeysUI());
		});
		
		
		queryBox.addClickListener(e -> {
			HorizontalSplitPanel hsp = new HorizontalSplitPanel();
			hsp.addComponent(new TheQueryBox(TheQueryBox.area.getValue()));
			hsp.addComponent(new HistoryTab());
			hsp.setWidth("100%");
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
		Export_history_UI.backUI.addClickListener(e6->{
			removeAllComponents();
			addComponent(new welcomeUI());
		});
		ShortcutKeysUI.backUI.addClickListener(e5->{
			removeAllComponents();
			addComponent(new welcomeUI());
		});
		
		Export_saved_Queries_UI.backUI.addClickListener(e5->{
			removeAllComponents();
			addComponent(new welcomeUI());
		});

		
		
		
		Groups.addClickListener(e27->{
			removeAllComponents();
			addComponent(new GroupUI());
		});
		
		GroupUI.back.addClickListener(e37->{
			removeAllComponents();
			addComponent(new welcomeUI());
		});
		
		
		
		
		layout.addComponents(label,queryBox,localInfo);
		layout.setComponentAlignment(queryBox , Alignment.MIDDLE_LEFT);
		layout.setComponentAlignment(label, Alignment.TOP_CENTER);
		

		Panel historyPanel1 = new HistoryTab();

		

		layout.addComponents(label,queryBox,localInfo,short_cut_keys,Groups,Copy_his,SavedQuery,link);


		layout.setComponentAlignment(queryBox , Alignment.MIDDLE_LEFT);
		layout.setComponentAlignment(label, Alignment.TOP_CENTER);
		

		addComponents(layout,historyPanel1);
		
		
		
	}
}
