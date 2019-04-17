package com.example.LearnMySQL_Final;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class TheQueryBox   extends VerticalLayout implements View {
	
	public TheQueryBox() {
		User u = new User();
		Person p = u.person;
		removeAllComponents();
		setHeight("100%");
		setWidth("100%");
		TextArea area = new TextArea("The Query Box");
		area.setWidth("100%");
		TextArea outputArea = new TextArea("Output Area");
		outputArea.setReadOnly(true);
		outputArea.setWidth("100%");
		final HorizontalLayout layout = new HorizontalLayout();
		
		
		Button execute = new Button("Execute");
		Button save = new Button("Save");
		
	
		save.addClickListener(e -> {
			String Query = area.getValue();
			saveQuery sq = new saveQuery(Query);
			removeAllComponents();
			addComponent(new saveUI());
		});
		
		
		
		VerticalLayout tableLayout = new VerticalLayout();
		execute.addClickListener(e->{
			String querys = area.getValue();
			String[] query = querys.split(";");
			String output = "";
			tableLayout.removeAllComponents();
			for(int i =  0; i < query.length;i++) {
				String currQuery = query[i];
				
					if(!currQuery.toUpperCase().contains("SELECT")) {
						
						StudentQueryHelper sqh = new StudentQueryHelper(p);
						String S = sqh.queryUpdateRun(currQuery);
						ServerManagementConnection smc = new ServerManagementConnection();
						smc.addStudentHistoryQuery(p, currQuery, "PiZZA");
						output = output + currQuery +":" + "\n";
						output = output + S + "\n";
					}
					else if(currQuery.toUpperCase().contains("SELECT")) {
						StudentQueryHelper sqh = new StudentQueryHelper(p);
						triplet trp = sqh.querySelectRun(currQuery);
						if(trp.queryOk) {
							LayoutHelper lh = new LayoutHelper();
							try {
								tableLayout.addComponent(lh.ResultSetToGrid(trp.rs));
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						else if(!trp.queryOk) {
							System.out.println(trp.error);
							output = output + currQuery +":" + "\n";
							output = output + trp.error + "\n";
						}
					}
					
					
					
					
					outputArea.setValue(output);
					
					
				
				
				
				
				
				
				
				
				
			}
		});
		
		

		execute.setWidth("100%");
		save.setWidth("100%");
		layout.addComponents(execute,save);
		layout.setExpandRatio(execute, .5f);
		layout.setExpandRatio(save, .5f);
		layout.setSizeFull();
		addComponents(area,layout,outputArea,tableLayout);
		/*setExpandRatio(area, .4f);
		setExpandRatio(layout, .1f);
		setExpandRatio(outputArea, .4f);*/
		setSizeFull();
		

	}
	
	
	public TheQueryBox(String query2) {
		removeAllComponents();
		setHeight("100%");
		setWidth("100%");
		TextArea area = new TextArea("The Query Box");
		area.setWidth("100%");
		area.setValue(query2);
		TextArea outputArea = new TextArea("Output Area");
		outputArea.setReadOnly(true);
		outputArea.setWidth("100%");
		final HorizontalLayout layout = new HorizontalLayout();
		
		
		Button execute = new Button("Execute");
		Button save = new Button("Save");
		
	
		save.addClickListener(e -> {
			String Query = area.getValue();
			saveQuery sq = new saveQuery(Query);
			removeAllComponents();
			addComponent(new saveUI());
		});
		
		execute.addClickListener(e->{
			String querys = area.getValue();
			String[] query = querys.split(";");
			String output = "";
			for(int i =  0; i < query.length;i++) {
				String currQuery = query[i];
				if(!currQuery.toUpperCase().contains("USES")) {
					if(!currQuery.toUpperCase().contains("SELECT")) {
						User u = new User();
						Person p = u.person;
						StudentQueryHelper sqh = new StudentQueryHelper(p);
						String S = sqh.queryUpdateRun(currQuery);
						output = output + currQuery +":" + "\n";
						output = output + S + "\n";
						
					}
					outputArea.setValue(output);
				}
			}
		});
		
		

		execute.setWidth("100%");
		save.setWidth("100%");
		layout.addComponents(execute,save);
		layout.setExpandRatio(execute, .5f);
		layout.setExpandRatio(save, .5f);
		layout.setSizeFull();
		addComponents(area,layout,outputArea);
		setExpandRatio(area, .4f);
		setExpandRatio(layout, .1f);
		setExpandRatio(outputArea, .4f);
		setSizeFull();
		
	}
	
	
	
}
