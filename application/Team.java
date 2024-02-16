package application;

public class Team {
int id ;
int idtournement ;
String teamName;
String capitanName;
int numplayer;
int points;
int rank ;
int gd ;
int play ;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getIdtournement() {
	return idtournement;
}
public void setIdtournement(int idtournement) {
	this.idtournement = idtournement;
}
public String getTeamName() {
	return teamName;
}
public void setTeamName(String teamName) {
	this.teamName = teamName;
}
public String getCapitanName() {
	return capitanName;
}
public void setCapitanName(String capitanName) {
	this.capitanName = capitanName;
}
public int getNumplayer() {
	return numplayer;
}
public void setNumplayer(int numplayer) {
	this.numplayer = numplayer;
}

public Team( int idtournement, String teamName, String capitanName, int numplayer) {
	super();
	
	this.idtournement = idtournement;
	this.teamName = teamName;
	this.capitanName = capitanName;
	this.numplayer = numplayer;
}
public int getPoints() {
	return points;
}
public void setPoints(int points) {
	this.points = points;
}
public Team(int id, int idtournement, String teamName, String capitanName, int numplayer, int points) {
	super();
	this.id = id;
	this.idtournement = idtournement;
	this.teamName = teamName;
	this.capitanName = capitanName;
	this.numplayer = numplayer;
	this.points = points;
}
public Team( int idtournement, String teamName,int play ,int gd, int points, int rank ) {
	super();
	this.rank=rank;
	this.idtournement = idtournement;
	this.teamName = teamName;
	
	
	this.points = points;
	this.gd = gd;
	this.play=play;
	
}
public int getRank() {
	return rank;
}
public void setRank(int rank) {
	this.rank = rank;
}
public int getGd() {
	return gd;
}
public void setGd(int gd) {
	this.gd = gd;
}
public int getPlay() {
	return play;
}
public void setPlay(int play) {
	this.play = play;
}


}
