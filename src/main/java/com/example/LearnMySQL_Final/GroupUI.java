package com.example.LearnMySQL_Final;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;

import com.vaadin.navigator.View;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class GroupUI  extends Panel implements View{
	
	Button newGroup = new Button("New Group");
	static TextArea area;
	LayoutHelper lh = new LayoutHelper();
	public static Button back = new Button("back");
	VerticalLayout content = new VerticalLayout();
	GroupServerManagementConnection gsmc;
	User u = new User();
	Person p = u.person;
	GroupObject go = new GroupObject();
	
	
	public GroupUI() {
		// setting up a panel
		gsmc = new GroupServerManagementConnection();
	
			content.setHeight("100%");
			content.setWidth("100%");
			content.setSizeFull();
			setContent(content);
			
			setHeight("100%");
			getContent().setHeightUndefined();
			
			// Find the application directory
			String basepath = VaadinService.getCurrent()
			                  .getBaseDirectory().getAbsolutePath();

			// Image as a file resource
			FileResource resource = new FileResource(new File(basepath +
			                        "/WEB-INF/images/back.png"));
			

			
			back.setStyleName(ValoTheme.BUTTON_LINK);
			back.setIcon(resource); 
			
			
			 groupUIInit();
			 
		
	}
	
	
	public void groupUIInit() {
		//set up the content of the page
		content.removeAllComponents();
		 triplet t = gsmc.alreadyCreatedGroup(p);
		 
		 

		 //check if they already created a group)
		 if (!t.queryOk) { 
			content.removeAllComponents();
			content.addComponents(newGroup,back);
			newGroup.addClickListener(e3->{
				newGroup();
			});
		 }
		
		
		
		
		 if(t.queryOk) {
			 String groupName = go.getName();
			Label l = new Label();
			l.setValue("Welcome " + groupName);
			Button addMember = new Button("Add Members");
			Button viewMembers = new Button("View Members");
			
			Button GroupQueryBox = new Button("Group Query Box");
			addMember.addClickListener(e4->{
				addMember();
			});
			
			viewMembers.addClickListener(e8->{
				viewMembers();
			});
			
			GroupQueryBox.addClickListener(e9->{
				queryBox();
			});
			
			
			
			content.addComponents(back,l,addMember,viewMembers,GroupQueryBox);
		 }
		
		
		
		
	}
	
	public void newGroup() {
		//create a group and is called by new Group
		content.removeAllComponents();
	
		 TextField groupname = new TextField();
		 groupname.setCaption("Group Name");
		 Button createGroup = new Button("New Group");
		 
		 
		
		 
			 content.addComponents(groupname,createGroup);
			 createGroup.addClickListener(e->{
				 
				 if(groupname.getValue().length() == 0) {
					 Notification.show("Failed",
		                       "Please enter a group name",
		                       Notification.Type.HUMANIZED_MESSAGE);
				 }
				 else {
					 if(gsmc.addGroup(p, groupname.getValue())){
						 groupUIInit();
					 }
					 else {
	  	         	   Notification.show("Failed",
	                 "Group name already used",
	                 Notification.Type.HUMANIZED_MESSAGE);
	     }
					 
				 }
			 });
			 
		 }
	
	//interface to add student
	public void addMember() {
		content.removeAllComponents();
		TextField memberName = new TextField();
		 memberName.setCaption("Please enter a student Number");
		 Button createMember = new Button("Add to Group");
		 Button TempBack = new Button("back");
		 TempBack.addClickListener(e7->{
			 groupUIInit();
		 });
		 
		 content.addComponents(memberName, createMember,TempBack);
		 
		 
		 createMember.addClickListener(e5->{
		 if(memberName.getValue().length() == 0) {
			 Notification.show("Failed",
                       "Please enter a Student Number",
                       Notification.Type.HUMANIZED_MESSAGE);
		 }
		 
		 
		 
		 if(gsmc.addMember(memberName.getValue())) {
			 groupUIInit();
		 }
		 else {
         	   Notification.show("Failed",
           "Student Number invalid",
           Notification.Type.HUMANIZED_MESSAGE);
}
		 
		 
		 });
		 
	}
		 
		
	public void viewMembers() {
		content.removeAllComponents();
		LayoutHelper lh = new LayoutHelper();
		triplet t = gsmc.getGroupMembers(go.getID());
		try {
			Grid<HashMap<String, String>> t2 = lh.ResultSetToGrid(t.rs);
			t2.setWidth("100%");
			content.addComponent(t2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Button TempBack = new Button("back");
		 TempBack.addClickListener(e7->{
			 groupUIInit();
		 });
		 content.addComponent(TempBack);
		 
		
	} 
		 
	public void queryBox() {
		content.removeAllComponents();
		System.out.println(go.getDatabase());
		
		
		HorizontalSplitPanel hsp = new HorizontalSplitPanel();
		hsp.addComponent(new TheQueryBox(TheQueryBox.area.getValue(),"", go.getDatabase()));
		hsp.addComponent(new HistoryTab());
		
		
		content.addComponent(hsp);
		//setContent(new TheGroupQueryBox("",go.getDatabase()));
		
		TheQueryBox.back.addClickListener(e423->{
			content.removeAllComponents();
			groupUIInit();
		});
		
		
		}
	
	
	
	
	
	
}
