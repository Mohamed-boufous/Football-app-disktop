package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller1 {
	@FXML
	private Pane centerContent;
    @FXML
    private Label tcont;

    @FXML
    private Label tdebut;

    @FXML
    private Label tfin;

    @FXML
    private Label tname;

    @FXML
    private Label tnumplayers;

    @FXML
    private Label tnumteams;

    @FXML
    private Label torg;

    @FXML
    private TextField ttcont;

    @FXML
    private TextField ttdebut;

    @FXML
    private TextField ttfin;

    @FXML
    private TextField ttname;

    @FXML
    private TextField ttnumplayers;

    @FXML
    private TextField ttnumteams;

    @FXML
    private TextField ttorg;
    @FXML
    private BorderPane bp;

    @FXML
    void next(MouseEvent event) {
        // Obtenir le BorderPane à partir de la source de l'événement
        Node source = (Node) event.getSource();
        BorderPane borderPane = (BorderPane) source.getScene().getRoot();

        String org = ttorg.getText();
        String name = ttname.getText();
        String cont = ttcont.getText();
        int numteams = Integer.valueOf(ttnumteams.getText());
        String debut = ttdebut.getText();
        String fin = ttfin.getText();
        int numplayers = Integer.valueOf(ttnumplayers.getText());

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("helper.fxml"));
            Parent root = loader.load();
            Controller2 controller2 = loader.getController();
            controller2.setData(org, name, cont, numteams, debut, fin, numplayers);

            // Remplacer le contenu actuel par la nouvelle vue chargée dans le centre du BorderPane
            borderPane.setCenter(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  
	
   



}
