package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class Controllermatch {


	   @FXML
	    private TableColumn<Match, Integer> t1;

	    @FXML
	    private TableColumn<Match, Integer> t2;

	    @FXML
	    private TableView<Match> table;

	    @FXML
	    private TableColumn<Match, Integer> tid;
	    @FXML
	    private TableColumn<Match, String> sccor;

    @FXML
    private TextField idl;

    @FXML
    private TextField idw;

    @FXML
    private TextField sl;

    @FXML
    private TextField sw;

    
    ObservableList<Match> data;
    
    
  
    

    
    @FXML
    void initialize() {
    	
    	data=FXCollections.observableArrayList();
    	
    	
    	
    	
    	 tid.setCellValueFactory(new PropertyValueFactory<>("idm"));
    	 t1.setCellValueFactory(new PropertyValueFactory<>("idwinner"));
    	 t2.setCellValueFactory(new PropertyValueFactory<>("idlosser"));
    	 
    	 sccor.setCellValueFactory(cellData -> {
    		    Match match = cellData.getValue();
    		    int winnerScore = match.getScccow();
    		    int loserScore = match.getScccorl();
    		    String scoreString = "(" + winnerScore + " - " + loserScore + ")";
    		    return new SimpleStringProperty(scoreString);
    		});

         
         
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
            
            String sql = "SELECT * FROM `Matches` WHERE `idtournament` = ?"; 
            PreparedStatement ps = connection.prepareStatement(sql);
            
            // Remplacer 17 par la valeur de tournamentId
            ps.setInt(1, tournamentId);
            System.out.println(tournamentId);
            
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int id = results.getInt("id");
                int idw = results.getInt("idwinner");
                int sw = results.getInt("sw");
                int idl = results.getInt("idlosser");
                int sl = results.getInt("sl");
              
                System.out.println(tournamentId);
                data.add(new Match(id, tournamentId, idw,sw,idl,sl));
            }
        } catch (Exception e) {
            // Gérer les exceptions
        }
    }

    
    @FXML
    void ajouter(ActionEvent event) {
        try {
            int idloser = Integer.valueOf(idl.getText());
            int sloser = Integer.valueOf(sl.getText());
            int idwinner = Integer.valueOf(idw.getText());
            int swinner = Integer.valueOf(sw.getText());

            // Création d'un nouveau match
            Match p = new Match(tournamentId, idwinner, swinner, idloser, sloser);

            // Réinitialisation des champs après l'ajout
            idl.setText("");
            sl.setText("");
            idw.setText("");
            sw.setText("");

            // Appel à la méthode d'insertion
            insertAndUpdateTable(p);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static void insertAndUpdateTable(Match p) {
        try {
            Connection connection = MysqlConnection.getDBConnection();

            String sql = "INSERT INTO `Matches` (`idtournament`, `idwinner`, `sw`, `idlosser`, `sl`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, p.idtournement);
            ps.setInt(2, p.idwinner);
            ps.setInt(3, p.scccow); // Utiliser le score du gagnant ici
            ps.setInt(4, p.idlosser);
            ps.setInt(5, p.scccorl); // Utiliser le score du perdant ici
            ps.execute();

            // Mettre à jour les points des équipes après l'insertion du match
            updateTeamPoints(connection, p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateTeamPoints(Connection connection, Match p) {
        try {
            // Vérifier les scores pour mettre à jour les points
            if (p.scccow == p.scccorl) {
                System.out.println("Match nul. Ajout de 1 point pour le gagnant et le perdant.");

                updateTeamPoints(connection, p.idwinner, 1); // Ajouter 1 point au gagnant
                updateTeamPoints(connection, p.idlosser, 1); // Ajouter 1 point au perdant
            } else {
                System.out.println("Match non nul. Ajout de 3 points pour le gagnant.");

                updateTeamPoints(connection, p.idwinner, 3); // Ajouter 3 points au gagnant
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateTeamPoints(Connection connection, int teamId, int points) {
        try {
            System.out.println("Mise à jour des points pour l'équipe " + teamId + " de " + points + " points.");

            String sql = "UPDATE teams SET points = points + ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, points);
            ps.setInt(2, teamId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    void delete(ActionEvent event) {
    	int index=table.getSelectionModel().getSelectedIndex();
    	
    	if(index>=0) {
    		
    		Match p=table.getSelectionModel().getSelectedItem();
    		
    		delete(p.idm);
    		data.remove(index);
    	}
    	
    }
    
    @FXML
    void vider(ActionEvent event) {
    	try {
			Connection connection = MysqlConnection.getDBConnection();
			String sql="DELETE FROM `Matches` "; 
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
			String sql="DELETE FROM `Matches` WHERE `Matches`.`id` = ?"; 
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

            int idWinner = Integer.parseInt(idw.getText());
            int swValue = Integer.parseInt(sw.getText());
            int idLoser = Integer.parseInt(idl.getText());
            int slValue = Integer.parseInt(sl.getText());
            Match p = table.getSelectionModel().getSelectedItem();

            // Utilisation de String.format pour formater la chaîne de score
            String scoreString = String.format("(%d - %d)", swValue, slValue);

            String sql = "UPDATE Matches SET idwinner = ?, sw = ?, idlosser = ?, sl = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idWinner);
            ps.setInt(2, swValue);
            ps.setInt(3, idLoser);
            ps.setInt(4, slValue);
             // Mettez à jour la colonne 'score' avec la chaîne de score
            ps.setInt(5, p.idm);
            ps.execute();

            // Effacer les champs après la mise à jour
            idw.setText("");
            sw.setText("");
            idl.setText("");
            sl.setText("");

            // Actualiser la liste
            getAll();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void mouseSelect(MouseEvent event) {
    	selectPersonne();
    }
    
    public void selectPersonne() {
    	Match  p=table.getSelectionModel().getSelectedItem();
    	
    	
    	idw.setText(p.idwinner+"");
    	sw.setText(p.scccow+"");
    idl.setText(p.idlosser+"");
    	sl.setText(p.scccorl+"");
    }

    

}
