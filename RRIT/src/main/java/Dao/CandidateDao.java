package Dao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*; 
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import Bean.Candidate;

public class CandidateDao {

	/*	Setup Database Connection*/
	public static Connection setConnection()
	{
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");		 
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useSSL=false","root","root");  
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return con;
	}

	
	/*	Create Candidate*/
	public static String createCandidate(Candidate candidate, File file) throws IOException
	{
		String message="";
		try {
			Connection con=setConnection();
			int check = checkExisting(candidate.getCand_id());
			if(check==1)
			{
				message="Details already present for "+candidate.getCand_name();
				return message;
			}
			if(file!=null){
			PreparedStatement prepared = con.prepareStatement("insert into Candidate_Details (cand_id, cand_name, domain, experience,cv) values(?,?,?,?,?);");
			prepared.setInt(1,candidate.getCand_id());
			prepared.setString(2, candidate.getCand_name());
			prepared.setString(3, candidate.getDomain());
			prepared.setInt(4, candidate.getExperience());
			FileInputStream fis = new FileInputStream(file);
			prepared.setBlob(5, fis);
			prepared.executeUpdate();
			message = "success";
			}
			else
			{
				PreparedStatement prepared = con.prepareStatement("insert into Candidate_Details (cand_id, cand_name, domain, experience) values(?,?,?,?);");
				prepared.setInt(1,candidate.getCand_id());
				prepared.setString(2, candidate.getCand_name());
				prepared.setString(3, candidate.getDomain());
				prepared.setInt(4, candidate.getExperience());
				prepared.executeUpdate();
				message = "success";
			}
			con.close();  
			}
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return message;
	}
	
	/*	CV Uploading*/
	public static String updateCV(String name,File file) throws FileNotFoundException, SQLException
	{
		String message="";
		Connection con=setConnection();
		if(file!=null)
		{
		PreparedStatement prepared = con.prepareStatement("update candidate_details set cv=? where cand_id=?");

		FileInputStream fis = new FileInputStream(file);
		prepared.setBlob(1, fis);
		prepared.setInt(2, Integer.valueOf(name));
		prepared.executeUpdate();
		message="success";
		}
		return message;		
	}
	
	
	/*	Candidate Assessments Update*/
	public static String updateCandidate(Candidate candidate, String name) throws IOException
	{
		String message="successfully saved";
		try {
			Connection con=setConnection();
			PreparedStatement prepared = con.prepareStatement("update candidate_details set l1=?,l2=?,hr=? where cand_id=?");
			String l1="",l2="",hr="";
			if(candidate.getMyCheckBox1()!=null)
			{
				 l1="Cleared";
			}
			else
			{
				l1="Not Cleared";			
			}
			if(candidate.getMyCheckBox2()!=null)
			{
				if(l1.equals("Cleared"))
					
						l2="Cleared";
				else
				{	message="Candidate with ID :"+name+" first need to clear their L1 assessments";
					l2="Not Cleared";
				}
				
			}
			else
			{
				l2="Not Cleared";				
				
			}
			if(candidate.getMyCheckBox3()!=null)
			{
				if(l1.equals("Cleared") && l2.equals("Cleared"))
					hr="Cleared";
				else
				{
					hr="Not Cleared";
					message="Candidate with ID :"+name+" first need to clear their L1 and L2 assessments";
				}
			}
			
			else
			{
					hr="Not Cleared";
			}
			if(message.equals("successfully saved"))
			{	
				prepared.setString(1,l1);
				prepared.setString(2, l2);
				prepared.setString(3, hr);
				prepared.setInt(4, Integer.valueOf(name));
				prepared.executeUpdate();
			}
			con.close();
			}
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 return message;
	}
	
	
	/*	Check Existing Candidate*/
	public static int checkExisting(int id)
	{
		try {
			Connection con=setConnection();
			PreparedStatement prepared = con.prepareStatement("select cand_name from Candidate_Details where cand_id = ?");
			prepared.setInt(1,id);
			ResultSet rows = prepared.executeQuery();
			if(rows.next())
			{
				return  1;
			}
			else
			{
				return 0;
			}
		}
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return 0;
	}
	
	
	/*	CV Downloading*/
	@SuppressWarnings("resource")
	public static int cvDownload(int id)
	{
	        try {
	        	Connection con=setConnection();
				PreparedStatement prepared = con.prepareStatement("select cv from Candidate_Details where cand_id = ?");
				prepared.setInt(1,id);
	            ResultSet rs = prepared.executeQuery();
	            if (rs.next()) {
	            	String username = System.getProperty("user.name");
	            	Blob blob = rs.getBlob("cv");
	            	if(blob!=null)
	            	{
	            		InputStream inputStream = blob.getBinaryStream();
		                byte[] buffer = new byte[inputStream.available()];
		                inputStream.read(buffer);
		                File targetFile = new File("C:/Users/"+username+"/Downloads/"+id+"_cv.pdf");
		                OutputStream outStream = new FileOutputStream(targetFile);
		                outStream.write(buffer);
		                return 1;
	            	}
	            	else
	            	{
	            		return 0;
	            	}
	                
	            }
	             
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			return 0;
	    }
	
	
	/*	Export Data to An Excel File*/
	@SuppressWarnings({ "resource", "deprecation" })
	public static void download()
	{
		 try {
	        	Connection con=setConnection();
	        	 Statement statement = con.createStatement();
	             ResultSet resultSet = statement.executeQuery("select cand_id, cand_name, domain, experience, l1, l2, hr from Candidate_Details");
	             HSSFWorkbook workbook = new HSSFWorkbook();
	             HSSFSheet spreadsheet = workbook.createSheet("employe db");
	             HSSFRow row = spreadsheet.createRow(1);
			      HSSFCell cell;
			      HSSFCellStyle style = workbook.createCellStyle();
			        style.setBorderTop(BorderStyle.DOUBLE);
			        style.setBorderBottom(BorderStyle.THIN);
			        style.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
			        
			        HSSFFont font = workbook.createFont();
			        font.setFontName(HSSFFont.FONT_ARIAL);
			        font.setFontHeightInPoints((short) 8);
			        font.setBold(true);
			        font.setColor(HSSFColor.BLUE.index);
			        style.setFont(font);
			        
			      cell = row.createCell(1);
			      cell.setCellValue("cand_id");
			      cell.setCellStyle(style);
			      cell = row.createCell(2);
			      cell.setCellValue("cand_name");
			      cell.setCellStyle(style);
			      cell = row.createCell(3);
			      cell.setCellValue("domain");
			      cell.setCellStyle(style);
			      cell = row.createCell(4);
			      cell.setCellValue("experience");
			      cell.setCellStyle(style);
			      cell = row.createCell(5);
			      cell.setCellValue("l1");
			      cell.setCellStyle(style);
			      cell = row.createCell(6);
			      cell.setCellValue("l2");
			      cell.setCellStyle(style);
			      cell = row.createCell(7);
			      cell.setCellValue("hr");
			      cell.setCellStyle(style);
			      int i = 2;
			      while(resultSet.next()) {
			         row = spreadsheet.createRow(i);
			         cell = row.createCell(1);
			         cell.setCellValue(resultSet.getInt("cand_id"));
			         cell = row.createCell(2);
			         cell.setCellValue(resultSet.getString("cand_name"));
			         cell = row.createCell(3);
			         cell.setCellValue(resultSet.getString("domain"));
			         cell = row.createCell(4);
			         cell.setCellValue(resultSet.getString("experience"));
			         cell = row.createCell(5);
			         cell.setCellValue(resultSet.getString("l1"));
			         cell = row.createCell(6);
			         cell.setCellValue(resultSet.getString("l2"));
			         cell = row.createCell(7);
			         cell.setCellValue(resultSet.getString("hr"));
			         i++;
			      }
			      String username = System.getProperty("user.name");
			      FileOutputStream out = new FileOutputStream(new File("C:/Users/"+username+"/Downloads/CandidatesData.xls"));
			      workbook.write(out);
			      out.close();
		 }
		 catch (Exception e) {
	            e.printStackTrace();
	        }
	}
}