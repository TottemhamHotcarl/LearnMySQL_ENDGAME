package com.example.LearnMySQL_Final;



import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class HistoryTab  extends Panel implements View {

	VerticalLayout content;
	Button del,del1;
	Button sel;
	HorizontalLayout hl;
	LayoutHelper lh;
	 Button search;
	 public static Button SaveTabButton = new Button("SAVE TAB");
	public static Button refresh;
	public HistoryTab() {
		 content = new VerticalLayout();
		content.setWidth("100%");
		content.setHeight("100%");
		setHeight("100%");
		del1=new Button("Delete Row");
		del=new Button("Delete selected");
        sel=new Button("Add to query box");
	
		 hl = new HorizontalLayout();
		TextField searchBox = new TextField();
		search = new Button("search");
		refresh = new Button("refresh");
		search.setId("search");
		
		SaveTabButton.addClickListener(e->{
			saveTab();
		});
		
		
		
		
		hl.addComponents(searchBox,search,refresh,SaveTabButton);
		content.addComponent(hl);
		lh = new LayoutHelper();
		User u = new User();
		Person p = u.person;
		
		ServerManagementConnection smc = new ServerManagementConnection();
		triplet t = smc.getStudentHistoryQuery(p);
		if(t.queryOk) {
			update(t);
			}
		
			search.addClickListener(e->{
			String searchText = searchBox.getValue();
			triplet t2 = smc.getStudentHistoryQueryWithSearch(p, searchText);
			if(t2.queryOk) {
				
				update(t2);
					
				}
			
		});
		
		refresh.addClickListener(e->{
			searchBox.setValue("");
			triplet t5 = smc.getStudentHistoryQuery(p);
			if(t.queryOk) {
				update(t5);
				}
			
				search.addClickListener(e3->{
				String searchText = searchBox.getValue();
				triplet t2 = smc.getStudentHistoryQueryWithSearch(p, searchText);
				if(t2.queryOk) {
					
					update(t2);
						
					}
				
			});
		});
			
		
		setContent(content);

		getContent().setHeightUndefined();
		
		
		
		}
	
	
	public void update(triplet t) {
		ResultSet rs = t.rs;
		Grid<HistoryObject> grid = lh.ResultSetToHIstoryGrid(rs);
		content.removeAllComponents();
		content.addComponent(hl);
		content.addComponent(grid);
		
		 grid.addItemClickListener(e ->{
	        	content.addComponent(del);
	        	content.addComponent(sel);
	        	
	        	HistoryObject ho = e.getItem();
	        	del.addClickListener(e1->{
	        		System.out.println(ho.getHistoryID());
	        		ServerManagementConnection smc2 = new ServerManagementConnection();
	        		smc2.deleteHistory(ho.getHistoryID());
	        		search.click();
	        	});
	        	sel.addClickListener(e3->{
	        		//setting the Query box to empty
	        		TheQueryBox.area.setValue("");
	        		TheQueryBox.addToQueryBox(ho.getQuery());
	        	});
	        	
		 });
	}
	
	
	public void saveTab() {
		
		content.removeAllComponents();
		content.addComponent(hl);
		
		
		ServerManagementConnection smc =new ServerManagementConnection();
		lh = new LayoutHelper();
		User u = new User();
		Person p = u.person;
		triplet t = smc.getStudentSavedQuery(p);
		Grid<savedObject> grid = lh.ResultSetToSavedGrid(t.rs);
		
		if(t.queryOk) {
			content.addComponent(grid);
		}
		
		grid.addItemClickListener(e ->{
			savedObject ho = e.getItem();
	    	System.out.println(ho.getQuery());
        	content.addComponent(del1);
        	content.addComponent(sel);
        	
        	del1.addClickListener(e2->{
        		System.out.println(ho.getSavedQueryID());
        		ServerManagementConnection smc2 = new ServerManagementConnection();
        		smc2.deleteSaved_Query(ho.getSavedQueryID());
        		SaveTabButton.click();
        	});
        	sel.addClickListener(e3->{
        		TheQueryBox.area.setValue("");
        		TheQueryBox.addToQueryBox(ho.getQuery());
        	});
        
		});
		
	}
		
		
	
	
}
