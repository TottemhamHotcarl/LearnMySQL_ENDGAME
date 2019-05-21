package com.example.LearnMySQL_Final;


import java.io.File;

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
		
		Button queryBox = new Button("queryBox");
		
		Button localInfo = new Button("Terminal Info");
		
		Button Help_Docs= new Button(" SQL Help Docs");
		
		Button short_cut_keys= new  Button("Shortcuts");
		
		
		Button Groups = new Button("Groups");
		
		
		// Find the application directory
				String basepath = VaadinService.getCurrent()
				                  .getBaseDirectory().getAbsolutePath();

		// Image as a file resource
		FileResource resource = new FileResource(new File(basepath +
		                        "/WEB-INF/images/SQL_HELP_FILE.pdf"));
		
		Link link =  new Link("Link to the SQL Help Files", resource);
				
				
		
		
		
		HorizontalLayout hl = new HorizontalLayout();
	
		Help_Docs.addClickListener(e->{
			removeAllComponents();
			addComponent(new SQL_help());
		});
		
		short_cut_keys.addClickListener(e->{
			removeAllComponents();
			addComponent(new ShortcutKeysUI());
		});
		
		
		queryBox.addClickListener(e -> {
			HorizontalSplitPanel hsp = new HorizontalSplitPanel();
			hsp.addComponent(new TheQueryBox(TheQueryBox.area.getValue()));
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
		ShortcutKeysUI.backUI.addClickListener(e5->{
			removeAllComponents();
			addComponent(new welcomeUI());
		});
		SQL_help.backUI.addClickListener(e5->{
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

		
		layout.addComponents(label,queryBox,localInfo,short_cut_keys,Help_Docs,Groups,link);

		layout.setComponentAlignment(queryBox , Alignment.MIDDLE_LEFT);
		layout.setComponentAlignment(label, Alignment.TOP_CENTER);
		

		addComponents(layout,historyPanel1);
		
		
		
	}
}
