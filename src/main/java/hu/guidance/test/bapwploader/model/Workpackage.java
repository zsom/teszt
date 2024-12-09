package hu.guidance.test.bapwploader.model;

import java.util.Date;

public class Workpackage {
	public static int CF_EV = 28;
	public static int CF_BER_KAT = 36;
	public static int CF_SZLA_NETTO = 11;
	public static int CF_SZLA_DEV = 12;
	public static int CF_SZLA_ARF = 25;
	public static int CF_KTSG_HELY2 = 35;
	
	private int id;
	private int projectId = 7; //Bér-adó 
	private int typeId = 11; //Költség
	private int statusId = 1; //New
	private int authorId;
	private int priorityId = 8; //Normal
	private Date created = new Date();
	private String subject;
	
	private String ev;
	private String berKat = "614"; //Bér
	private String szlaNetto;
	private String szlaDev = "14"; //HUF 
	private String arfolyam = "1";
	
	public Workpackage() {}
	
	public Workpackage(int authorId, String subject) {
		super();
		this.authorId = authorId;
		this.subject = subject;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public int getPriorityId() {
		return priorityId;
	}
	public void setPriorityId(int priorityId) {
		this.priorityId = priorityId;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Workpackage [id=");
		builder.append(id);
		builder.append(", authorId=");
		builder.append(authorId);
		builder.append(", created=");
		builder.append(created);
		builder.append(", subject=");
		builder.append(subject);
		builder.append(", ev=");
		builder.append(ev);
		builder.append(", berKat=");
		builder.append(berKat);
		builder.append(", szlaNetto=");
		builder.append(szlaNetto);
		builder.append(", szlaDev=");
		builder.append(szlaDev);
		builder.append(", arfolyam=");
		builder.append(arfolyam);
		builder.append("]");
		return builder.toString();
	}

	public String getEv() {
		return ev;
	}

	public void setEv(String ev) {
		this.ev = ev;
	}

	public String getBerKat() {
		return berKat;
	}

	public void setBerKat(String berKat) {
		this.berKat = berKat;
	}

	public String getSzlaNetto() {
		return szlaNetto;
	}

	public void setSzlaNetto(String szlaNetto) {
		this.szlaNetto = szlaNetto;
	}

	public String getSzlaDev() {
		return szlaDev;
	}

	public void setSzlaDev(String szlaDev) {
		this.szlaDev = szlaDev;
	}

	public String getArfolyam() {
		return arfolyam;
	}

	public void setArfolyam(String arfolyam) {
		this.arfolyam = arfolyam;
	}
	
	
	
}
