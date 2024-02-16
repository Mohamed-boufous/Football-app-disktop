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


	
	private boolean isSelected = false; // Variable pour suivre l'�tat de s�lection

	private int selectedImageId = 0; // Variable pour stocker l'ID de l'image s�lectionn�e

	@FXML
	void handleLogo(MouseEvent event) {
		ImageView image = (ImageView) event.getSource(); // R�cup�rer l'image associ�e � l'�v�nement

		int currentImageId = 0; // ID de l'image actuellement s�lectionn�e

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

		// Si l'image actuelle est d�j� s�lectionn�e, d�selectionnez-la
		if (isSelected && selectedImageId == currentImageId) {
			image.setOpacity(1.0); // R�tablir l'opacit� initiale
			isSelected = false; // Mettre � jour le statut de s�lection
			selectedImageId = 0; // Aucune image s�lectionn�e
		} else {
			// Sinon, d�s�lectionnez toute autre image s�lectionn�e pr�c�demment
			img1.setOpacity(1.0);
			img2.setOpacity(1.0);
			img3.setOpacity(1.0);
			img4.setOpacity(1.0);

			// S�lectionnez l'image actuelle
			image.setOpacity(0.5); // R�duire l'opacit� pour indiquer la s�lection
			isSelected = true; // Mettre � jour le statut de s�lection
			selectedImageId = currentImageId; // Mettre � jour l'ID de l'image s�lectionn�e
		}

		System.out.println(selectedImageId);
	}

	private boolean isSelectedter = false; // Variable pour suivre l'�tat de s�lection
	private int selectedImageIdter = 0; // Variable pour stocker l'ID de l'image s�lectionn�e

	@FXML
	void handleter(MouseEvent event) {
		ImageView image = (ImageView) event.getSource(); // R�cup�rer l'image associ�e � l'�v�nement

		int currentImageId = 0; // ID de l'image actuellement s�lectionn�e

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

		// D�s�lectionnez toute autre image s�lectionn�e pr�c�demment
		imgter1.setOpacity(1.0);
		imgter2.setOpacity(1.0);
		imgter3.setOpacity(1.0);
		imgter4.setOpacity(1.0);

		// Si l'image actuelle est d�j� s�lectionn�e, d�selectionnez-la
		if (isSelectedter && selectedImageIdter == currentImageId) {
			isSelectedter = false; // Mettre � jour le statut de s�lection
			selectedImageIdter = 0; // Aucune image s�lectionn�e
		} else {
			// Sinon, s�lectionnez l'image actuelle
			image.setOpacity(0.5); // R�duire l'opacit� pour indiquer la s�lection
			isSelectedter = true; // Mettre � jour le statut de s�lection
			selectedImageIdter = currentImageId; // Mettre � jour l'ID de l'image s�lectionn�e
		}

		System.out.println(selectedImageIdter);
	}
	public void ajouter(MouseEvent event) throws IOException {
	    tournement t = new tournement(org, name, cont, numteams, debut, fin, numplayers, selectedImageId, selectedImageIdter);
	    insertAndGetGeneratedId(t); // Appel � votre m�thode d'insertion avec r�cup�ration de l'ID g�n�r�
	 // Obtenez la sc�ne de l'�v�nement ou du n�ud parent actuel
	    Scene currentScene = ((Node) event.getSource()).getScene();

	    // Obtenez la fen�tre actuelle � partir de la sc�ne
	    Stage currentStage = (Stage) currentScene.getWindow();

	    // Fermez la fen�tre actuelle
	    currentStage.close();

	    // Chargez et ouvrez la nouvelle fen�tre en mode maximis�
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("TOURNEMENTEXISTE.fxml"));
	    Parent root = loader.load();

	    Stage newStage = new Stage();
	    newStage.setScene(new Scene(root));
	    newStage.setMaximized(true); // D�finit la fen�tre en mode maximis�
	    newStage.show();

	}

	public static void insertAndGetGeneratedId(tournement t) {
	    int generatedId = -1; // Initialiser l'ID g�n�r� � une valeur par d�faut
	    try {
	        Connection connection = MysqlConnection.getDBConnection();
	        String sql = "INSERT INTO `tournoments` (`organisername`, `tournementName`, `infoscontact`, `numberofteams`, `stardate`, `enddate`, `numberPlayerTeam`,`IDlogo`,`idterrain`) VALUES (?,?,?,?,?,?,?,?,?)";

	        // Indiquer la r�cup�ration des cl�s g�n�r�es
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
	            generatedId = generatedKeys.getInt(1); // R�cup�rer l'ID g�n�r�
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
