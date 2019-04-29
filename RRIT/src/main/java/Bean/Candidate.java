package Bean;

import java.io.File;

public class Candidate {

	private int cand_id;
	private String cand_name;
	private String domain;
	private int experience;
	private File cv;
	private String myCheckBox1;
	private String myCheckBox2;
	private String myCheckBox3;
	
	public Candidate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Candidate(int cand_id, String cand_name, String domain, int experience, File cv, String myCheckBox1, String myCheckBox2, String myCheckBox3) {
		super();
		this.cand_id = cand_id;
		this.cand_name = cand_name;
		this.domain = domain;
		this.experience = experience;
		this.cv = cv;
		this.myCheckBox1 = myCheckBox1;
		this.myCheckBox2 = myCheckBox2;
		this.myCheckBox3 = myCheckBox3;
	}

	public String getMyCheckBox1() {
		return myCheckBox1;
	}
	public void setMyCheckBox1(String myCheckBox1) {
		this.myCheckBox1 = myCheckBox1;
	}
	public String getMyCheckBox2() {
		return myCheckBox2;
	}
	public void setMyCheckBox2(String myCheckBox2) {
		this.myCheckBox2 = myCheckBox2;
	}
	public String getMyCheckBox3() {
		return myCheckBox3;
	}
	public void setMyCheckBox3(String myCheckBox3) {
		this.myCheckBox3 = myCheckBox3;
	}
	public int getCand_id() {
		return cand_id;
	}
	public void setCand_id(int cand_id) {
		this.cand_id = cand_id;
	}
	
	@Override
	public String toString() {
		return "Candidate [cand_id=" + cand_id + ", cand_name=" + cand_name + ", domain=" + domain + ", experience="
				+ experience + ", cv=" + cv + ", myCheckBox1=" + myCheckBox1 + ", myCheckBox2=" + myCheckBox2
				+ ", myCheckBox3=" + myCheckBox3 + "]";
	}
	public String getCand_name() {
		return cand_name;
	}
	public void setCand_name(String cand_name) {
		this.cand_name = cand_name;
	}
	
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	public File getCv() {
		return cv;
	}
	public void setCv(File cv) {
		this.cv = cv;
	}
	
}
