package com.example.LearnMySQL_Final;

import java.io.File;


import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.server.FileResource;

import com.vaadin.server.VaadinService;

import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class TheQueryBox   extends Panel implements View {
	
	
	VerticalLayout content = new VerticalLayout();
	static TextArea area = new TextArea("The Query Box");
	
	LayoutHelper lh = new LayoutHelper();

	public static Button back = new Button("back");
	
	ServerManagementConnection smc  = new ServerManagementConnection();
	StudentQueryHelper sqh;
	

	Person p;
	/**
	 * Creates the panel for the query box
	 * @param s : Query that appears in the querybox
	 */
	public TheQueryBox(String s) {
		
		content.setHeight("100%");
		content.setWidth("100%");
		content.setSizeFull();
		setContent(content);
		
		setHeight("100%");
		getContent().setHeightUndefined();
		User u = new User();
		 p = u.person;
		sqh = new StudentQueryHelper(p);
		queryBox(s);
	}
	
	
	/**
	 * Creates the panel for the query box
	 * @param s: Query that appears in the querybox
	 * @param ip: Changes the ip address of the server
	 */
	/*public TheQueryBox(String s, String ip) {
		smc = new ServerManagementConnection(ip);
		queryBox(s);
		content.setHeight("100%");
		content.setWidth("100%");
		content.setSizeFull();
		setContent(content);
		
		setHeight("100%");
		getContent().setHeightUndefined();
		User u = new User();
		 p = u.person;
		 sqh = new StudentQueryHelper(p);

	}*/
	
	/**
	 * This the construstor for the group querybox
	 * @param s
	 * @param ip
	 * @param database
	 */
	public TheQueryBox(String s, String ip,String database) {
		smc = new ServerManagementConnection();
		queryBox(s);
		content.setHeight("100%");
		content.setWidth("100%");
		content.setSizeFull();
		setContent(content);
		
		setHeight("100%");
		getContent().setHeightUndefined();
		User u = new User();
		 p = u.person;
		 sqh = new StudentQueryHelper(database);

	}
	
	
	
	
	
	
	
	/**
	 * Creates the query box
	 * @param s : Query that appears in the querybox
	 */
	public void queryBox(String s) {
		content.removeAllComponents();
		
		// Find the application directory
		String basepath = VaadinService.getCurrent()
		                  .getBaseDirectory().getAbsolutePath();

		// Image as a file resource
		FileResource resource = new FileResource(new File(basepath +
		                        "/WEB-INF/images/back.png"));
		

		
		back.setStyleName(ValoTheme.BUTTON_LINK);
		back.setIcon(resource);

		
		area.setValue(s);
		area.setWidth("100%");
		TextArea outputArea = new TextArea("Output Area");
		outputArea.setReadOnly(true);
		outputArea.setWidth("100%");
		final HorizontalLayout layout = new HorizontalLayout();
		
		
		Button execute = new Button("Execute");
		execute.setDescription("F1");
		Button save = new Button("Save");
		save.setDescription("F3");
		Button clear= new Button("Clear");
		clear.setDescription("F2");
		
		execute.setClickShortcut(KeyCode.F1);
		save.setClickShortcut(KeyCode.F2);
		clear.setClickShortcut(KeyCode.F3);
		back.setClickShortcut(KeyCode.ESCAPE);

		//Button uploadFile = new Button("UPLOAD FILE");
		FileUploader receiver= new FileUploader(area);
		Upload upload= new Upload("Upload file Here",null);
		upload.setReceiver(receiver);
		upload.addSucceededListener(receiver);
		upload.setImmediateMode(false);
		upload.setButtonCaption("Upload now");

		
		save.addClickListener(e -> {
			String Query = area.getValue();
			if(Query.isEmpty()) {
				Notification.show("Cannot Save an empty Query,Please write your query");
			}
			else{
				saveQuery sq = new saveQuery(Query);
			saveUI();
			}



		});
		
		
		
		VerticalLayout tableLayout = new VerticalLayout();
		
		clear.addClickListener(e->{
			area.setValue("");
		});
		


		execute.addClickListener(e->{
			String querys = area.getValue();
			String[] query = querys.split(";");
			String output = "";
			tableLayout.removeAllComponents();
			
			
			
			for(int i =  0; i < query.length;i++) {
				String currQuery = query[i];
				if(currQuery.isEmpty()) {
					Notification.show("Cannot execute empty query,Please write your query");
					continue;
				}
				
				String[] check = currQuery.split(" ");
				
				if(check.length == 0 ) {
					continue;
				}
				
				smc.addStudentHistoryQuery(p, currQuery);
				HistoryTab.refresh.click();
				
				TextField outputtemp = lh.GetOutputHeading(currQuery);

				
					if(currQuery.toUpperCase().contains("SELECT")) {

						triplet trp = sqh.querySelectRun(currQuery);
						
						if(trp.queryOk) {
							
							Grid g = trp.grid;
							if(g != null) {
								//there are enteries in table
								g.setWidth("100%");
								VerticalLayout vltemp = new VerticalLayout();
								vltemp.addComponents(outputtemp,g);
								tableLayout.addComponent(vltemp);
							}
							else {
								//no entries in table
								VerticalLayout vltemp = new VerticalLayout();
								TextArea outputtemp2 = new TextArea();
								outputtemp2.setReadOnly(true);
								outputtemp2.setWidth("100%");
								outputtemp2.setHeightUndefined();
								outputtemp2.setValue("Empty set");
								vltemp.addComponents(outputtemp,outputtemp2);
								tableLayout.addComponent(vltemp);
							}
							
						}
						else if(!trp.queryOk) {
							//error in query given
							VerticalLayout vltemp = new VerticalLayout();
							TextArea outputtemp2 = new TextArea();
							outputtemp2.setReadOnly(true);
							outputtemp2.setWidth("100%");
							outputtemp2.setHeightUndefined();
							outputtemp2.setValue(trp.error);
							vltemp.addComponents(outputtemp,outputtemp2);
							tableLayout.addComponent(vltemp);
						}
					}
					

					else if(currQuery.toUpperCase().contains("DESC")) {

						
						triplet trp = sqh.queryDescRun(currQuery);
						
						if(trp.queryOk) {
							
							Grid g = trp.grid;
							if(g != null) {
								g.setWidth("100%");
								VerticalLayout vltemp = new VerticalLayout();
								vltemp.addComponents(outputtemp,g);
								tableLayout.addComponent(vltemp);
							}
							else {
								VerticalLayout vltemp = new VerticalLayout();
								TextArea outputtemp2 = new TextArea();
								outputtemp2.setReadOnly(true);
								outputtemp2.setWidth("100%");
								outputtemp2.setHeightUndefined();
								outputtemp2.setValue("Empty set");
								vltemp.addComponents(outputtemp,outputtemp2);
								tableLayout.addComponent(vltemp);
								
							}
							
						}
						else if(!trp.queryOk) {
							VerticalLayout vltemp = new VerticalLayout();
							
							TextArea outputtemp2 = new TextArea();
							outputtemp2.setReadOnly(true);
							outputtemp2.setWidth("100%");
							outputtemp2.setHeightUndefined();
							outputtemp2.setValue(trp.error);
													
							
							vltemp.addComponents(outputtemp,outputtemp2);
							tableLayout.addComponent(vltemp);
							
							
							
							
						}
					}
					

					else if(currQuery.toUpperCase().contains("SHOW") && currQuery.toUpperCase().contains("TABLES") && !currQuery.toUpperCase().contains("SELECT") ) {
						VerticalLayout vltemp = new VerticalLayout();
						
						StudentQueryHelper sqh = new StudentQueryHelper(p);
						Grid g;
						g = sqh.queryShowTable();
						vltemp.addComponents(outputtemp,g);
						tableLayout.addComponent(vltemp);

					}
					
					else if(!currQuery.toUpperCase().contains("SELECT")) {
						
						StudentQueryHelper sqh = new StudentQueryHelper(p);
						String S = sqh.queryUpdateRun(currQuery);
						output = output + currQuery +":" + "\n";
						output = output + S + "\n";
						
						VerticalLayout vltemp = new VerticalLayout();
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
		clear.setWidth("100%");
		back.setWidth("20%");
		layout.addComponents(execute,save,clear);
		layout.setExpandRatio(execute, .5f);
		layout.setExpandRatio(save, .5f);
		layout.setExpandRatio(clear,.5f);
		
		layout.setSizeFull();
		tableLayout.setWidth("100%");
		tableLayout.setSizeFull();
		upload.setWidth("100%");
		content.addComponents(back,area,layout,upload,tableLayout);
		
		
	}
	
	
	
	
	/**
	 * This create the saveUI
	 */
	public void saveUI() {
		Button clear= new Button("Clear");
		content.removeAllComponents();
		saveQuery sq = new saveQuery();
		String query = sq.returnQuery();
		 
		TextArea area = new TextArea("Do you want to save this Query?");
		area.setWidth("100%"); area.setValue(query);
	
		
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
	
	/**
	 * This adds the saved Query to the database
	 * @param query: The query that is going to be saved
	 * @param query_name: The name of the query that is going to be saved
	 * @return True if it saved successfully, otherwise it returns false
	 */
	public boolean addToDatabase(String query,String query_name) {
		Person p = User.person;
		ServerManagementConnection smc = new ServerManagementConnection();
		if(smc.addStudentSavedQuery(p, query_name,query)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
/**
 * This function is used by outside objects (like history tab) to add something to the queryBox
 * @param x: The string that we want to add to the queryBox
 */
public static void addToQueryBox(String x) {
		area.setValue(x);
		
	}

	
	
	
}
