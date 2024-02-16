package application;



public class tournement {
	int id ;
String organisername ;
String tournementName;
String infoscontact ;
int numberofteams;
String stardate;
String enddate;
int numberPlayerTeam;
int idlogo ;
int idter ;
public tournement(int id, String organisername, String tournementName, String infoscontact, int numberofteams,
		String stardate, String enddate, int numberPlayerTeam, int idlogo, int idter) {
	super();
	this.id = id;
	this.organisername = organisername;
	this.tournementName = tournementName;
	this.infoscontact = infoscontact;
	this.numberofteams = numberofteams;
	this.stardate = stardate;
	this.enddate = enddate;
	this.numberPlayerTeam = numberPlayerTeam;
	this.idlogo = idlogo;
	this.idter = idter;
}
public tournement( String organisername, String tournementName, String infoscontact, int numberofteams,
		String stardate, String enddate, int numberPlayerTeam, int idlogo, int idter) {
	super();
	
	this.organisername = organisername;
	this.tournementName = tournementName;
	this.infoscontact = infoscontact;
	this.numberofteams = numberofteams;
	this.stardate = stardate;
	this.enddate = enddate;
	this.numberPlayerTeam = numberPlayerTeam;
	this.idlogo = idlogo;
	this.idter = idter;
}
public String getOrganisername() {
	return organisername;
}
public void setOrganisername(String organisername) {
	this.organisername = organisername;
}
public String getTournementName() {
	return tournementName;
}
public void setTournementName(String tournementName) {
	this.tournementName = tournementName;
}
public String getInfoscontact() {
	return infoscontact;
}
public void setInfoscontact(String infoscontact) {
	this.infoscontact = infoscontact;
}
public int getNumberofteams() {
	return numberofteams;
}
public void setNumberofteams(int numberofteams) {
	this.numberofteams = numberofteams;
}
public String getStardate() {
	return stardate;
}
public void setStardate(String stardate) {
	this.stardate = stardate;
}
public String getEnddate() {
	return enddate;
}
public void setEnddate(String enddate) {
	this.enddate = enddate;
}
public int getNumberPlayerTeam() {
	return numberPlayerTeam;
}
public void setNumberPlayerTeam(int numberPlayerTeam) {
	this.numberPlayerTeam = numberPlayerTeam;
}
public int getIdlogo() {
	return idlogo;
}
public void setIdlogo(int idlogo) {
	this.idlogo = idlogo;
}
public int getIdter() {
	return idter;
}
public void setIdter(int idter) {
	this.idter = idter;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
@Override
public String toString() {
	return "tournement [id=" + id + ", organisername=" + organisername + ", tournementName=" + tournementName
			+ ", infoscontact=" + infoscontact + ", numberofteams=" + numberofteams + ", stardate=" + stardate
			+ ", enddate=" + enddate + ", numberPlayerTeam=" + numberPlayerTeam + ", idlogo=" + idlogo + ", idter="
			+ idter + "]";
}
public tournement( int id,String tournementName ) {
	super();
this.id=id;
	this.tournementName = tournementName;
	
}



}
