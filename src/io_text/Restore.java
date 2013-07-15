package io_text;

import java.io.*;

public class Restore {
	
	Pass md = new Pass();
	final String filename = md.SHGetFolderPath() + "\\memo.txt";
	
	public Restore(){
		
	}
	
	public String getRestore(){
		String data = "";
		try{
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String temp = "";
			while((temp = br. readLine()) != null){
				data += temp + "\n";
			}
			br.close();
		}catch(Exception ex){
			System.out.println("ファイルエラー");
		}
		return data;
	}		
}

