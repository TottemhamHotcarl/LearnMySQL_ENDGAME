package com.example.LearnMySQL_Final;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import com.vaadin.ui.TextArea;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

public class FileUploader implements Receiver,SucceededListener{
	private File file;
	private TextArea inputArea;
	
	public FileUploader(TextArea inputArea) {
		this.inputArea=inputArea;
	}
	

	@Override
	public void uploadSucceeded(SucceededEvent event) {
		// TODO Auto-generated method stub
		inputArea.setValue("Succeeded");
		try {
			FileReader fileReader = new FileReader(event.getFilename());
			BufferedReader br=new BufferedReader (fileReader);
			String line=null;
			StringBuilder sb = new StringBuilder();
			while((line=br.readLine())!=null) {
				sb.append(line);
				sb.append("\n");
			}
			br.close();
			inputArea.setValue(sb.toString());
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public OutputStream receiveUpload(String filename, String mimeType) {
		// TODO Auto-generated method stub
		FileOutputStream fos=null;
		file=new File(filename);
		
		try {
			fos=new FileOutputStream(file);
		}catch(IOException e) {
			e.printStackTrace();
			inputArea.setValue("exception: something has gone wrong with upload.Please check if you selected a file.");
			return null;
		}
		return fos;
	}

}
