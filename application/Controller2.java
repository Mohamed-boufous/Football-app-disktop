package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
public class Controller2 implements Initializable{
    @FXML
    private BorderPane bp;
	@FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    @FXML
    private ImageView img4;

    @FXML
    private ImageView imgter1;

    @FXML
    private ImageView imgter2;

    @FXML
    private ImageView imgter3;

    @FXML
    private ImageView imgter4;
    ObservableList<tournement> data;
	 private String org;
	    private String name;
	    private String cont;
	    private int numteams;
	    private String debut;
	    private String fin;
	    private int numplayers;
	  
	   
	    

	public void setData(String org, String name, String cont, int numteams, String debut, String fin, int numplayers) {
        this.org = org;
        this.name = name;
        this.cont = cont;
        this.numteams = numteams;
        this.debut = debut;
        this.fin = fin;
        this.numplayers = numplayers;
      

  
    }


	
	private boolean isSelected = false; // Variable pour suivre l'état de sélection

	private int selectedImageId = 0; // Variable pour stocker l'ID de l'image sélectionnée

	@FXML
	void handleLogo(MouseEvent event) {
		ImageView image = (ImageView) event.getSource(); // Récupérer l'image associée à l'événement

		int currentImageId = 0; // ID de l'image actuellement sélectionnée

		// Obtenez l'ID de l'image en fonction de son ID FXML
		if (image.getId().equals("img1")) {
			currentImageId = 1;
		} else if (image.getId().equals("img2")) {
			currentImageId = 2;
		} else if (image.getId().equals("img3")) {
			currentImageId = 3;
		} else if (image.getId().equals("img4")) {
			currentImageId = 4;
		}

		// Si l'image actuelle est déjà sélectionnée, déselectionnez-la
		if (isSelected && selectedImageId == currentImageId) {
			image.setOpacity(1.0); // Rétablir l'opacité initiale
			isSelected = false; // Mettre à jour le statut de sélection
			selectedImageId = 0; // Aucune image sélectionnée
		} else {
			// Sinon, désélectionnez toute autre image sélectionnée précédemment
			img1.setOpacity(1.0);
			img2.setOpacity(1.0);
			img3.setOpacity(1.0);
			img4.setOpacity(1.0);

			// Sélectionnez l'image actuelle
			image.setOpacity(0.5); // Réduire l'opacité pour indiquer la sélection
			isSelected = true; // Mettre à jour le statut de sélection
			selectedImageId = currentImageId; // Mettre à jour l'ID de l'image sélectionnée
		}

		System.out.println(selectedImageId);
	}

	private boolean isSelectedter = false; // Variable pour suivre l'état de sélection
	private int selectedImageIdter = 0; // Variable pour stocker l'ID de l'image sélectionnée

	@FXML
	void handleter(MouseEvent event) {
		ImageView image = (ImageView) event.getSource(); // Récupérer l'image associée à l'événement

		int currentImageId = 0; // ID de l'image actuellement sélectionnée

		// Obtenez l'ID de l'image en fonction de son ID FXML
		if (image.getId().equals("imgter1")) {
			currentImageId = 1;
		} else if (image.getId().equals("imgter2")) {
			currentImageId = 2;
		} else if (image.getId().equals("imgter3")) {
			currentImageId = 3;
		} else if (image.getId().equals("imgter4")) {
			currentImageId = 4;
		}

		// Désélectionnez toute autre image sélectionnée précédemment
		imgter1.setOpacity(1.0);
		imgter2.setOpacity(1.0);
		imgter3.setOpacity(1.0);
		imgter4.setOpacity(1.0);

		// Si l'image actuelle est déjà sélectionnée, déselectionnez-la
		if (isSelectedter && selectedImageIdter == currentImageId) {
			isSelectedter = false; // Mettre à jour le statut de sélection
			selectedImageIdter = 0; // Aucune image sélectionnée
		} else {
			// Sinon, sélectionnez l'image actuelle
			image.setOpacity(0.5); // Réduire l'opacité pour indiquer la sélection
			isSelectedter = true; // Mettre à jour le statut de sélection
			selectedImageIdter = currentImageId; // Mettre à jour l'ID de l'image sélectionnée
		}

		System.out.println(selectedImageIdter);
	}
	public void ajouter(MouseEvent event) throws IOException {
	    tournement t = new tournement(org, name, cont, numteams, debut, fin, numplayers, selectedImageId, selectedImageIdter);
	    insertAndGetGeneratedId(t); // Appel à votre méthode d'insertion avec récupération de l'ID généré
	 // Obtenez la scène de l'événement ou du nœud parent actuel
	    Scene currentScene = ((Node) event.getSource()).getScene();

	    // Obtenez la fenêtre actuelle à partir de la scène
	    Stage currentStage = (Stage) currentScene.getWindow();

	    // Fermez la fenêtre actuelle
	    currentStage.close();

	    // Chargez et ouvrez la nouvelle fenêtre en mode maximisé
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("TOURNEMENTEXISTE.fxml"));
	    Parent root = loader.load();

	    Stage newStage = new Stage();
	    newStage.setScene(new Scene(root));
	    newStage.setMaximized(true); // Définit la fenêtre en mode maximisé
	    newStage.show();

	}

	public static void insertAndGetGeneratedId(tournement t) {
	    int generatedId = -1; // Initialiser l'ID généré à une valeur par défaut
	    try {
	        Connection connection = MysqlConnection.getDBConnection();
	        String sql = "INSERT INTO `tournoments` (`organisername`, `tournementName`, `infoscontact`, `numberofteams`, `stardate`, `enddate`, `numberPlayerTeam`,`IDlogo`,`idterrain`) VALUES (?,?,?,?,?,?,?,?,?)";

	        // Indiquer la récupération des clés générées
	        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        ps.setString(1, t.getOrganisername());
	        ps.setString(2, t.getTournementName());
	        ps.setString(3, t.getInfoscontact());
	        ps.setInt(4, t.getNumberofteams());
	        ps.setString(5, t.getStardate());
	        ps.setString(6, t.getEnddate());
	        ps.setInt(7, t.getNumberPlayerTeam());
	        ps.setInt(8, t.getIdlogo());
	        ps.setInt(9, t.getIdter());

	        ps.executeUpdate();

	        ResultSet generatedKeys = ps.getGeneratedKeys();
	        if (generatedKeys.next()) {
	            generatedId = generatedKeys.getInt(1); // Récupérer l'ID généré
	        }

	        generatedKeys.close();
	        ps.close();
	        connection.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	   
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}


	
}
