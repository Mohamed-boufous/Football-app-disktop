package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class controlladd {

	@FXML
    private TableView<Team> table;

    @FXML
    private TableColumn<Team, String> tcap;

    @FXML
    private TextField teamcapitan;

    @FXML
    private TextField teamname;

    @FXML
    private TextField teamnumber;

    @FXML
    private TableColumn<Team, Integer> tid;

    @FXML
    private TableColumn<Team, String> tname1;

    @FXML
    private TableColumn<Team, Integer> tnum;
    @FXML
    private TableColumn<Team, Integer> pointes;

    
    ObservableList<Team> data;
    
    
  
    

    
    @FXML
    void initialize() {
    	
    	data=FXCollections.observableArrayList();
    	
    	
    	
    	
    	 tid.setCellValueFactory(new PropertyValueFactory<>("id"));
         tname1.setCellValueFactory(new PropertyValueFactory<>("teamName"));
         tcap.setCellValueFactory(new PropertyValueFactory<>("capitanName"));
         tnum.setCellValueFactory(new PropertyValueFactory<>("numplayer"));
         pointes.setCellValueFactory(new PropertyValueFactory<>("points"));
         
         table.setItems(data);
    	
    	
        
    }
    int tournamentId ;
    public void getid(int idt) {
        
    	this.tournamentId = idt; // Stockage de l'ID dans la variable locale tournamentId
    	  System.out.println(tournamentId); 
    	  getAll();
    }
    
    public void getAll() {
        data.clear();
        try {
            Connection connection = MysqlConnection.getDBConnection();
            
            String sql = "SELECT * FROM `teams` WHERE `idtournament` = ?"; 
            PreparedStatement ps = connection.prepareStatement(sql);
            
            // Remplacer 17 par la valeur de tournamentId
            ps.setInt(1, tournamentId);
            System.out.println(tournamentId);
            
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int id = results.getInt("id");
                String nom = results.getString("teamName");
                String capitan = results.getString("captainName");
                int num = results.getInt("numPlayers");
                int p = results.getInt("points");
                
                System.out.println(tournamentId);
                data.add(new Team(id, tournamentId, nom, capitan, num,p));
            }
        } catch (Exception e) {
            // Gérer les exceptions
        }
    }

   
    
    @FXML
    void ajouter(ActionEvent event) {
    	String nom=teamname.getText();
    	String capitan=teamcapitan.getText();
    	int num=Integer.valueOf(teamnumber.getText());

    	Team p=new Team(tournamentId ,nom,capitan,num);
    	teamname.setText("");
    	teamcapitan.setText("");
    	teamnumber.setText("");
    	
    	data.clear();
    	insert(p);
    	getAll();
    	//data.add(p);
    }

    public static void insert(Team p) {
        try {
            Connection connection = MysqlConnection.getDBConnection();

            String sql = "INSERT INTO `teams` (`idtournament`,`teamName`, `captainName`, `numPlayers`) VALUES (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, p.idtournement); // Utilisation de l'ID du tournoi pour l'insertion
            ps.setString(2, p.teamName);
            ps.setString(3, p.capitanName);
            ps.setInt(4, p.numplayer);
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void delete(ActionEvent event) {
    	int index=table.getSelectionModel().getSelectedIndex();
    	
    	if(index>=0) {
    		
    		Team p=table.getSelectionModel().getSelectedItem();
    		
    		delete(p.id);
    		data.remove(index);
    	}
    	
    }
    
    @FXML
    void vider(ActionEvent event) {
    	try {
			Connection connection = MysqlConnection.getDBConnection();
			String sql="DELETE FROM `teams` "; 
			PreparedStatement ps=connection.prepareStatement(sql);
			

			ps.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	data.clear();
    }
    
    
    
    public void delete(int id) {
		try {
			Connection connection = MysqlConnection.getDBConnection();
			String sql="DELETE FROM `teams` WHERE `teams`.`id` = ?"; 
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, id);

			ps.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
   
    @FXML
    void update(ActionEvent event) {
        try {
            Connection connection = MysqlConnection.getDBConnection();
            
            String nom = teamname.getText();
            String capitan = teamcapitan.getText();
            int num = 0; // Valeur par défaut
            
            String teamNumText = teamnumber.getText();
            if (!teamNumText.isEmpty()) {
                // Vérifie si le texte peut être converti en entier
                try {
                    num = Integer.parseInt(teamNumText);
                } catch (NumberFormatException ex) {
                    // Gérez l'erreur de conversion ici si le texte n'est pas un entier valide
                    ex.printStackTrace();
                    // Vous pouvez afficher un message d'erreur à l'utilisateur ou faire autre chose selon votre application
                }
            }
            
            Team p = table.getSelectionModel().getSelectedItem();

            String sql = "UPDATE `teams` SET `teamName` = ?, `captainName` = ?, `numPlayers` = ? WHERE `teams`.`id` = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, nom);
            ps.setString(2, capitan);
            ps.setInt(3, num);
            ps.setInt(4, p.id);
            ps.execute();

            teamname.setText("");
            teamcapitan.setText("");
            teamnumber.setText("");

            getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void mouseSelect(MouseEvent event) {
        selectTeam();
    }

    public void selectTeam() {
        Team p = table.getSelectionModel().getSelectedItem();
        
        teamname.setText(p.teamName);
        teamcapitan.setText(p.capitanName);
        teamnumber.setText(String.valueOf(p.numplayer));
    }


    

}
