package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.awt.Desktop;
import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Bean.Candidate;
import Bean.Employee;
import Dao.CandidateDao;
import Dao.EmployeeDao;

@Controller
public class myController {
	
	
	/*	Login Page Request*/
	@RequestMapping("/")
	public String indexPage() {
		return "index";
	}
	
	
	/*	Employee Registeration*/
	@RequestMapping(value="reg", method=RequestMethod.POST)
	public ModelAndView register(@ModelAttribute Employee employee) {
		ModelAndView modelAndView = new ModelAndView();
		
		String message =EmployeeDao.save(employee);
		if(message.equals("success"))
		{
		modelAndView.setViewName("index");
		}
		else
		{
			modelAndView.addObject("message", message);	
			modelAndView.setViewName("register");
		}
		return modelAndView;
	}
	
	
	/*	Employee Login*/
	@RequestMapping(value="login", method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute Employee employee,HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		
		String message =EmployeeDao.loginUser(employee);
		
		
		if(!(message.equals("Invalid Password") || message.equals("No User associated with this ID.")))
		{
		modelAndView.setViewName("Candidate");
		
		HttpSession session =  request.getSession();
		String split[]= message.split(";");
		session.setAttribute("name", split[0].toUpperCase());
		int flag = Integer.parseInt(split[1]);
		if(flag>0)
		{
			session.setAttribute("access", "Admin");
			modelAndView.setViewName("ViewAll");
		}
		else
		{
			session.setAttribute("access", "Moderator");
			modelAndView.setViewName("ViewAll");
		}
		session.setAttribute("id", employee.getId());
		}
		else
		{
			modelAndView.addObject("message",message);
			modelAndView.setViewName("index");
			
		}
		return modelAndView;
	}
	
	
	/*	Candidate Registration*/
	@RequestMapping(value="cand", method=RequestMethod.POST)
	public  ModelAndView candidateDetails(@ModelAttribute("cand") Candidate candidate) throws IOException {
		String message="";
		File selected=null;
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter( "PDF Documents" , "pdf" );
		chooser.setFileFilter(filter);
		if( chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ){
	        selected = chooser.getSelectedFile();
	    }
		ModelAndView modelAndView = new ModelAndView();
		message =CandidateDao.createCandidate(candidate,selected);
		if(message.equals("success"))
		{
			modelAndView.addObject("message", "successfully saved");		
			modelAndView.setViewName("View");
		}   
		else
		{
			modelAndView.addObject("message", message);	
			modelAndView.setViewName("Candidate");
		}
		return modelAndView;
	}
	
	
	/*	CV Uploading */
	@RequestMapping(value="updateCV", method=RequestMethod.POST)
	public  ModelAndView updateCV( @RequestParam Map<String,String> reqPar) throws ClassNotFoundException, SQLException, IOException {
		ModelAndView modelAndView = new ModelAndView();
		File selected=null;
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter( "PDF Documents" , "pdf" );
		chooser.setFileFilter(filter);
		String name = reqPar.get("cvs");
		if( chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ){
	        selected = chooser.getSelectedFile();
	    }
		String message=CandidateDao.updateCV(name,selected);
		System.out.println(message);
		if(message.equals("success"))
		{
			modelAndView.addObject("message","CV Successfully Uploaded");
			modelAndView.setViewName("View");
		}
		else
		{
			modelAndView.addObject("message","No File Choosen");
			modelAndView.setViewName("View");
		}
		return modelAndView;
	}
	
	
	/*	Update Candidate Assessments*/
	@RequestMapping(value="cand2", method=RequestMethod.POST)
	public  ModelAndView candidateDetails2(@ModelAttribute("cand") Candidate candidate, @RequestParam Map<String,String> reqPar) throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		String name = reqPar.get("submit");
		String message = CandidateDao.updateCandidate(candidate,name);
		if(message.equals("successfully saved"))
		{
			modelAndView.addObject("message",message);
			modelAndView.setViewName("View");
		}
		else
		{
			modelAndView.addObject("message",message);
			modelAndView.setViewName("View");
		}
		return modelAndView;
	}
	
	
	/*	Employee Access*/
	@RequestMapping(value="access", method=RequestMethod.POST)
	public  ModelAndView access(@ModelAttribute("emp") Employee employee, @RequestParam Map<String,String> reqPar) throws IOException, SQLException {
		ModelAndView modelAndView = new ModelAndView();
		String name = reqPar.get("submit");
		String message = EmployeeDao.updateAccess(employee,name);
		modelAndView.addObject("message",message);
		modelAndView.setViewName("Access");
		return modelAndView;
		
	}
	
	/*	Employee Logout*/
	@RequestMapping(value="logout", method=RequestMethod.POST)
	public  ModelAndView logout(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session =  request.getSession();
		session.setAttribute("name", null);
		modelAndView.setViewName("index");
		 
           
		return modelAndView;
		
	}
	
	
	/*	Download Candidate CV*/
	@RequestMapping(value="downloadCV", method=RequestMethod.POST)
	public  ModelAndView downloadCV(@RequestParam Map<String,String> reqPar, HttpServletResponse response,  HttpServletRequest request) throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		String name = reqPar.get("submit");
		int download=CandidateDao.cvDownload(Integer.valueOf(name));
		if(download==1)
		{
			modelAndView.setViewName("ViewAll");
			modelAndView.addObject("message", "CV downloaded Successfully for candidate with ID : " +name);
			Desktop desktop = Desktop.getDesktop();
	        File dirToOpen = null;
	        try {
	        	String username = System.getProperty("user.name");
			    String path ="C:/Users/"+username+"/Downloads";
			    dirToOpen = new File(path);
	            desktop.open(dirToOpen);
	        } catch (IllegalArgumentException iae) {
	            System.out.println("File Not Found");
	        }
		}
		else
		{
			modelAndView.setViewName("ViewAll");
			modelAndView.addObject("message", "No CV found for candidate with ID : "+name);
		}
		return modelAndView;
	}
	
	
	/*	Export Data To An Excel File*/
	@RequestMapping(value="download", method=RequestMethod.POST)
	public  ModelAndView candidateDetails1() throws ClassNotFoundException, SQLException, IOException {
		CandidateDao.download();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("ViewAll");
		Desktop desktop = Desktop.getDesktop();
        File dirToOpen = null;
        try {
        	String username = System.getProperty("user.name");
		    String path ="C:/Users/"+username+"/Downloads";
            dirToOpen = new File(path);
            desktop.open(dirToOpen);
        } catch (IllegalArgumentException iae) {
            System.out.println("File Not Found");
        }
		return modelAndView;
	}
	
	
	
}