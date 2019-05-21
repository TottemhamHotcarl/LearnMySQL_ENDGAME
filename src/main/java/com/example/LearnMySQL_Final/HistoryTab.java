package com.example.LearnMySQL_Final;



import java.awt.List;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.server.ClassResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.Grid.Column;


@Theme("mytheme")
public class HistoryTab  extends Panel implements View {

	VerticalLayout content;


	Button del,del1;
	Button sel;
	Button add;
	HorizontalLayout hl;
	LayoutHelper lh;
	 Button search;
	 public static Button SaveTabButton = new Button("SAVE TAB");
	public static Button refresh;
	 TextField searchBox;
	 ServerManagementConnection smc =new ServerManagementConnection();
	
	 
	public HistoryTab() {
		 content = new VerticalLayout();
			content.setWidth("100%");
			content.setHeight("100%");
			setHeight("100%");
			
			
			del1=new Button("Delete Row");
			add=new Button("Save_To_Query");
			del=new Button("Delete selected");
	        sel=new Button("Add to query box");
		
			 hl = new HorizontalLayout();
			 
			 searchBox = new TextField();
				search = new Button("search");
				refresh = new Button("refresh");
				//search.setId("search");
				
				// Find the application directory
				String basepath = VaadinService.getCurrent()
				                  .getBaseDirectory().getAbsolutePath();

				// Image as a file resource
				FileResource resource = new FileResource(new File(basepath +
				                        "/WEB-INF/images/mag.png"));
				FileResource resource2 = new FileResource(new File(basepath +
                        "/WEB-INF/images/refresh.png"));
				
				search.setStyleName(ValoTheme.BUTTON_LINK);
				search.setIcon(resource);
				
				refresh.setStyleName(ValoTheme.BUTTON_LINK);
				refresh.setIcon(resource2);
				
				
				
				
				hl.addComponents(searchBox,search,refresh,SaveTabButton);
				
		
		HistoryTabInit();
		
	}
	
	
	public void HistoryTabInit() {
		
		
		content.removeAllComponents();
		
		SaveTabButton.setCaption("SAVED TAB");
		SaveTabButton.addClickListener(e->{
			saveTab();
		});
		
		

		content.addComponent(hl);
		lh = new LayoutHelper();
		User u = new User();
		Person p = u.person;
		
		
		triplet t = smc.getStudentHistoryQuery(p);
		if(t.queryOk) {

			updateHistory(t);
		}

		
			search.addClickListener(e->{
			String searchText = searchBox.getValue();
			triplet t2 = smc.getStudentHistoryQueryWithSearch(p, searchText);
			if(t2.queryOk) {
				
				updateHistory(t2);

					
				}
			
		});
		
			
			
			
			
		refresh.addClickListener(e->{

			HistoryTabInit();
		});
			
		setContent(content);
		getContent().setHeightUndefined();	
		}
	
	public void updateHistory(triplet t) {
		User u = new User();
		Person p = u.person;
		ResultSet rs = t.rs;
		
		Grid<HistoryObject> grid = lh.ResultSetToHIstoryGrid(rs);
		content.removeAllComponents();
		content.addComponent(hl);
		content.addComponent(grid);
		
		 grid.addItemClickListener(e ->{
	        	content.addComponent(del);
	        	content.addComponent(sel);
	        	content.addComponent(add);
	        	HistoryObject ho = e.getItem();
	        	del.addClickListener(e1->{
	        		System.out.println(ho.getHistoryID());
	        		smc.deleteHistory(ho.getHistoryID());
	        		search.click();
	        	});
	        	sel.addClickListener(e3->{
	        	//setting the Query box to empty
	        		TheQueryBox.area.setValue("");
	        		TheQueryBox.addToQueryBox(ho.getQuery());
	        	});
	        	
	        	
	      //SAVING A QUERY FROM HISTORY TABLE TO SAVED TABLE............................
	        	add.addClickListener(e3->{
	        		content.removeAllComponents();
	        		
	        		final TextField text=new TextField();
	        		text.setCaption("Type your QueryName here:");
	                Button ex=new Button("Save");
	        		content.removeComponent(del);
	        		content.removeComponent(sel);
	        		content.addComponent(text);
	        		content.addComponent(ex);
	        		ex.addClickListener(e4->{
	        			smc. addStudentSavedQuery(p,text.getValue() ,ho.getQuery());
	        			content.addComponent(hl);
	        	    search.click();
	        	
	        		});
	        		
	        	});	
	        	
		 });

	}
	
	
	public void saveTab() {

		
		content.removeAllComponents();
		SaveTabButton.setCaption("HISTORY TAB");
		

		SaveTabButton.addClickListener(e->{
			SaveTabButton.setCaption("SAVE TAB");
			HistoryTabInit();
		});
		

		content.addComponent(hl);

		
		User u = new User();
		Person p = u.person;
		triplet t = smc.getStudentSavedQuery(p);
		//Grid<savedObject> grid = lh.ResultSetToSavedGrid(t.rs);
		

		if(t.queryOk) {
			updateSave(t);
		}
		
		search.addClickListener(e->{
			String searchText = searchBox.getValue();
			triplet t2 = smc.getStudentSavedQueryWithSearch(p, searchText);
			if(t2.queryOk) {
				
				updateSave(t2);
					
				}
			
		});
		
		refresh.addClickListener(e->{
			saveTab();
		});
		
		setContent(content);

		getContent().setHeightUndefined();
	}

	
	
	public void updateSave(triplet t) {
		ResultSet rs = t.rs;
		Grid<savedObject> grid = lh.ResultSetToSavedGrid(rs);
		content.removeAllComponents();
		content.addComponent(hl);
		content.addComponent(grid);
		
		 grid.addItemClickListener(e ->{
	        	savedObject ho = e.getItem();
		    	System.out.println(ho.getQuery());
	        	content.addComponent(del1);
	        	content.addComponent(sel);
	        	del1.addClickListener(e2->{
	        		System.out.println(ho.getSavedQueryID());
	        		smc.deleteSaved_Query(ho.getSavedQueryID());
	        		search.click();
	        	});
	        	
	        	
	        	sel.addClickListener(e3->{
	        		//TheQueryBox.area.setValue("");
	        		System.out.println("~~~~~~~" + ho.getQuery());
	        		TheQueryBox.addToQueryBox(ho.getQuery());
	        	});
	        	
	        
			});
		 //grid.sort(HISTORY_DATE, SortDirection.DESCENDING);
	}

		
		
	
	
}
