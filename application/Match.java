package application;

public class Match {
int idm; 
int idwinner;
int scccow ;
int idlosser;
int scccorl ;
int idtournement ;
public Match(int idm ,int idtournement , int idwinner, int scccow, int idlosser, int scccorl) {
	super();
	this.idtournement=idtournement;
	this.idm = idm;
	this.idwinner = idwinner;
	this.scccow = scccow;
	this.idlosser = idlosser;
	this.scccorl = scccorl;
}
public Match( int idtournement , int idwinner, int scccow, int idlosser, int scccorl) {
	super();
	this.idtournement=idtournement;
	this.idwinner = idwinner;
	this.scccow = scccow;
	this.idlosser = idlosser;
	this.scccorl = scccorl;
}
public int getIdtournement() {
	return idtournement;
}
public void setIdtournement(int idtournement) {
	this.idtournement = idtournement;
}
public int getIdm() {
	return idm;
}
public void setIdm(int idm) {
	this.idm = idm;
}
public int getIdwinner() {
	return idwinner;
}
public void setIdwinner(int idwinner) {
	this.idwinner = idwinner;
}
public int getScccow() {
	return scccow;
}
public void setScccow(int scccow) {
	this.scccow = scccow;
}
public int getIdlosser() {
	return idlosser;
}
public void setIdlosser(int idlosser) {
	this.idlosser = idlosser;
}
public int getScccorl() {
	return scccorl;
}
public void setScccorl(int scccorl) {
	this.scccorl = scccorl;
}


}
