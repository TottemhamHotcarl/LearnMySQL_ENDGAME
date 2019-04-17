package com.example.LearnMySQL_Final;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class TheQueryBox   extends Panel implements View {
	
	
	VerticalLayout content = new VerticalLayout();
	Person p;
	public TheQueryBox(String s) {
		queryBox(s);
		content.setHeight("100%");
		content.setWidth("100%");
		content.setSizeFull();
		setContent(content);
		
		setHeight("100%");
		getContent().setHeightUndefined();
		User u = new User();
		 p = u.person;
		
		 

	}
	
	
	public void queryBox(String s) {
		content.removeAllComponents();
		
		TextArea area = new TextArea("The Query Box");
		area.setValue(s);
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
			saveUI();
		});
		
		
		
		VerticalLayout tableLayout = new VerticalLayout();
		
		execute.addClickListener(e->{
			String querys = area.getValue();
			String[] query = querys.split(";");
			String output = "";
			tableLayout.removeAllComponents();
			for(int i =  0; i < query.length;i++) {
				String currQuery = query[i];
				if(currQuery.isEmpty()) {
					continue;
				}
					
					if(currQuery.toUpperCase().contains("SELECT")) {
						StudentQueryHelper sqh = new StudentQueryHelper(p);
						triplet trp = sqh.querySelectRun(currQuery);
						if(trp.queryOk) {
							LayoutHelper lh = new LayoutHelper();
							try {
								Grid g = lh.ResultSetToGrid(trp.rs);
								if(g != null) {
									g.setWidth("100%");
									VerticalLayout vltemp = new VerticalLayout();
									TextField outputtemp = new TextField();
									outputtemp.setReadOnly(true);
									outputtemp.setWidth("100%");
									outputtemp.setHeightUndefined();
									outputtemp.setValue(currQuery);
															
									
									vltemp.addComponents(outputtemp,g);
									tableLayout.addComponent(vltemp);
								}
								else {
									VerticalLayout vltemp = new VerticalLayout();
									TextField outputtemp = new TextField();
									outputtemp.setReadOnly(true);
									outputtemp.setWidth("100%");
									outputtemp.setHeightUndefined();
									outputtemp.setValue(currQuery);
									TextArea outputtemp2 = new TextArea();
									outputtemp2.setReadOnly(true);
									outputtemp2.setWidth("100%");
									outputtemp2.setHeightUndefined();
									outputtemp2.setValue("Empty set");
									vltemp.addComponents(outputtemp,outputtemp2);
									tableLayout.addComponent(vltemp);
									
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
						else if(!trp.queryOk) {
							VerticalLayout vltemp = new VerticalLayout();
							TextField outputtemp = new TextField();
							outputtemp.setReadOnly(true);
							outputtemp.setWidth("100%");
							outputtemp.setHeightUndefined();
							outputtemp.setValue(currQuery);
							TextArea outputtemp2 = new TextArea();
							outputtemp2.setReadOnly(true);
							outputtemp2.setWidth("100%");
							outputtemp2.setHeightUndefined();
							outputtemp2.setValue(trp.error);
													
							
							vltemp.addComponents(outputtemp,outputtemp2);
							tableLayout.addComponent(vltemp);
							
							
							
							
						}
					}
					else if(currQuery.toUpperCase().equals("SHOW TABLES")) {
						VerticalLayout vltemp = new VerticalLayout();
						TextField outputtemp = new TextField();
						outputtemp.setReadOnly(true);
						outputtemp.setWidth("100%");
						outputtemp.setHeightUndefined();
						outputtemp.setValue(currQuery);
						
						StudentQueryHelper sqh = new StudentQueryHelper(p);
						Grid g;
						try {
							g = sqh.queryShowTable();
							vltemp.addComponents(outputtemp,g);
							tableLayout.addComponent(vltemp);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
												
						
						
					}
					
					else if(!currQuery.toUpperCase().contains("SELECT")) {
						
						StudentQueryHelper sqh = new StudentQueryHelper(p);
						String S = sqh.queryUpdateRun(currQuery);
						ServerManagementConnection smc = new ServerManagementConnection();
						//smc.addStudentHistoryQuery(p, currQuery, "PiZZA");
						output = output + currQuery +":" + "\n";
						output = output + S + "\n";
						
						VerticalLayout vltemp = new VerticalLayout();
						TextField outputtemp = new TextField();
						outputtemp.setReadOnly(true);
						outputtemp.setWidth("100%");
						outputtemp.setHeightUndefined();
						outputtemp.setValue(currQuery);
						TextArea outputtemp2 = new TextArea();
						outputtemp2.setReadOnly(true);
						outputtemp2.setWidth("100%");
						outputtemp2.setHeightUndefined();
						outputtemp2.setValue(S);
												
						
						vltemp.addComponents(outputtemp,outputtemp2);
						tableLayout.addComponent(vltemp);
						
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
		tableLayout.setWidth("100%");
		tableLayout.setSizeFull();
		content.addComponents(area,layout,tableLayout);
		/*setExpandRatio(area, .4f);
		setExpandRatio(layout, .1f);
		setExpandRatio(outputArea, .4f);*/
		
	}
	
	
	
	
	public void saveUI() {
		content.removeAllComponents();
		saveQuery sq = new saveQuery();
		String query = sq.returnQuery();
		TextArea area = new TextArea("Do you want to save this Query?");
		area.setWidth("100%");
		area.setValue(query);
	
		
		final HorizontalLayout hl = new HorizontalLayout();
		TextField searchBox = new TextField("Please enter a name for your query");
		Button save = new Button("Save");
		hl.addComponents(searchBox,save);
		save.addClickListener(e -> {
			if(addToDatabase(area.getValue(),searchBox.getValue())) {
				content.removeAllComponents();
				System.out.println(area.getValue());
				queryBox(area.getValue());
				
				
			}
			else {
				Notification.show("Query Name already in use");
			}
			
		
		});
		
		content.addComponents(area,hl);
	}
	
	public boolean addToDatabase(String query,String query_name) {
		User u = new User();
		Person p = u.person;
		ServerManagementConnection smc = new ServerManagementConnection();
		if(smc.addStudentHistoryQuery(p, query, query_name)) {
			return true;
		}
		else {
			return false;
		}
	}

	
	
	
}
