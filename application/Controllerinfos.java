package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class Controllerinfos implements Initializable {

    @FXML
    private Label tcontact;

    @FXML
    private Label tend;

    @FXML
    private Label tname;

    @FXML
    private Label tnumplayer;

    @FXML
    private Label tnumteams;

    @FXML
    private Label torganizer;

    @FXML
    private Label tstart;
    @FXML
    private Label idfootb;
    @FXML
    private ImageView imgeter;
    @FXML
    private ImageView imglogo;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	int generatedId =12;
        loadTournamentInfo( generatedId);
    }
    

    public void loadTournamentInfo(int generatedId) {
    	String query = "SELECT * FROM tournoments WHERE id = ?";
    	System.out.println(generatedId);
    	;

        try (Connection connection = MysqlConnection.getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1,generatedId);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                // Récupérer les informations depuis la base de données
                String organizerName = resultSet.getString("organisername");
                String tournamentName = resultSet.getString("tournementName");
                String contactInfo = resultSet.getString("infoscontact");
                int numberOfTeams = resultSet.getInt("numberofteams");
                String startDate = resultSet.getString("stardate");
                String endDate = resultSet.getString("enddate");
                int numberOfPlayers = resultSet.getInt("numberPlayerTeam");
                int idfoot = resultSet.getInt("idterrain");
                int idlogo=resultSet.getInt("IDlogo");
                String typefoot;
                Image image1;
                Image image2;

                switch (idfoot) {
                    case 1:
                        typefoot = "5vs5";
                        image1 = new Image("C:\\prj\\AYOUBEPROJECTEXEMPLE\\src\\application\\mini-futbol-sahasi-ozellikleri-ve-olculeri 1.png");
                        imgeter.setImage(image1);
                        break;
                    case 2:
                        typefoot = "mini soccer";
                        image1 = new Image("C:\\prj\\AYOUBEPROJECTEXEMPLE\\src\\application\\Cage-Summer-3-1024x768 1G.png");
                        imgeter.setImage(image1);
                        break;
                    case 3:
                        typefoot = "8vs8";
                        image1 = new Image("C:\\prj\\AYOUBEPROJECTEXEMPLE\\src\\application\\Frame 23G.png");
                        imgeter.setImage(image1);
                        break;
                    case 4:
                        typefoot = "11vs11";
                        image1 = new Image("C:\\prj\\AYOUBEPROJECTEXEMPLE\\src\\application\\161829345520210201_160214 G.png");
                        imgeter.setImage(image1);
                        break;
                    default:
                        typefoot = "inconnu";
                }

                switch (idlogo) {
                    case 1:
                        
                        image2 = new Image("C:\\prj\\AYOUBEPROJECTEXEMPLE\\src\\application\\Frame 25.png");
                        imglogo.setImage(image2);
                        break;
                    case 2:
                        
                        image2 = new Image("C:\\prj\\AYOUBEPROJECTEXEMPLE\\src\\application\\Frame 26.png");
                        imglogo.setImage(image2);
                        break;
                    case 3:
                        
                        image2 = new Image("C:\\prj\\AYOUBEPROJECTEXEMPLE\\src\\application\\Frame 27.png");
                        imglogo.setImage(image2);
                        break;
                    case 4:
                       
                        image2 = new Image("C:\\prj\\AYOUBEPROJECTEXEMPLE\\src\\application\\Frame 28.png");
                        imglogo.setImage(image2);
                        break;
                    default:
                        
                }

                // Mettre à jour les Labels avec les informations récupérées
                torganizer.setText(organizerName);
                tname.setText(tournamentName);
                tcontact.setText(contactInfo);
                tnumteams.setText(String.valueOf(numberOfTeams));
                tstart.setText(startDate);
                tend.setText(endDate);
                tnumplayer.setText(String.valueOf(numberOfPlayers));
                idfootb.setText(typefoot);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de connexion ou de requête SQL
        }
    }
}


