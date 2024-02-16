package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

public class Contscrool implements Initializable {

    @FXML
    private BorderPane bp;
    private Node initialScrollPane; // Stocker le contenu initial du ScrollPane
    @FXML
    private Label tcontact;
    @FXML
    private Label winnerLabel;

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
    @FXML
    private Button CLASSER;
  
    

 

    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int tournamentIdToLoad = 18; // Remplacez ceci par l'ID du tournoi souhaité
        loadTournamentInfo(tournamentIdToLoad);
        initialScrollPane = bp.getCenter();
        // Remplacez ceci par l'ID du tournoi souhaité
       
    }

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;
    @FXML
    private Button addButtonMatch;
    @FXML
    void add(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addteams.fxml"));
            Parent root = loader.load();

            // Accéder au contrôleur du fichier FXML chargé
            controlladd controller = loader.getController();
            
            // Passer l'ID au contrôleur controlladd
            controller.getid(idt);

            bp.setCenter(root);

            // Changer la couleur de fond du bouton 'Add' en bleu
            addButton.setStyle("-fx-background-color: blue;");
            

            // Réinitialiser la couleur de fond du bouton 'Back' à sa valeur initiale
       	 addButtonMatch.setStyle("");
            backButton.setStyle("");
            CLASSER.setStyle("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void addMatch(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addmatch.fxml"));
            Parent root = loader.load();

            // Accéder au contrôleur du fichier FXML chargé
            Controllermatch controller = loader.getController();
            
            // Passer l'ID au contrôleur controlladd
            controller.getid(idt);

            bp.setCenter(root);

            // Changer la couleur de fond du bouton 'Add' en bleu
            addButtonMatch.setStyle("-fx-background-color: blue;");

            // Réinitialiser la couleur de fond du bouton 'Back' à sa valeur initiale
            backButton.setStyle("");
            addButton.setStyle("");
            CLASSER.setStyle("");
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void Classement(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("layout.fxml"));
            Parent root = loader.load();

            // Accéder au contrôleur du fichier FXML chargé
            ControllerClassement controller = loader.getController();
            
            // Passer l'ID au contrôleur controlladd
            controller.getid(idt);

            bp.setCenter(root);

            // Changer la couleur de fond du bouton 'Add' en bleu
            CLASSER.setStyle("-fx-background-color: blue;");

            // Réinitialiser la couleur de fond du bouton 'Back' à sa valeur initiale
            backButton.setStyle("");
            addButton.setStyle("");
            addButtonMatch.setStyle("");
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void back(ActionEvent event) {
        // Restaurer le contenu initial du ScrollPane
        bp.setCenter(initialScrollPane);
        
        // Changer la couleur de fond du bouton 'Back' en bleu
        backButton.setStyle("-fx-background-color: blue;");

        // Réinitialiser la couleur de fond des autres boutons à leur valeur initiale
        addButtonMatch.setStyle("");
        addButton.setStyle("");
        CLASSER.setStyle("");

        // Recharger les informations du tournoi en appelant loadTournamentInfo avec l'ID du tournoi actuel
        loadTournamentInfo(idt);
    }
    int idt;
    public void loadTournamentInfo(int tournamentId) {
        String query = "SELECT * FROM tournoments WHERE id = ?";
        
        try (Connection connection = MysqlConnection.getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
        	preparedStatement.setInt(1, tournamentId); // Remplacer 6 par l'ID souhaité
idt= tournamentId;
calculateTotalGoals(tournamentId);
setWinnerLabel(tournamentId);

            
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
                        typefoot = "inconnu";
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

    @FXML
    private Label totgoals; // Assurez-vous que le Label dans votre FXML est correctement lié à cette variable

    // Méthode pour calculer et afficher le total des buts pour un tournoi donné
    public void calculateTotalGoals(int tournamentId) {
        try {
            Connection connection = MysqlConnection.getDBConnection();
            
            String sql = "SELECT SUM(sw) + SUM(sl) AS totalGoals FROM Matches WHERE idtournament = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, tournamentId);
            System.out.println(idt);
            
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                int total = resultSet.getInt("totalGoals");
                totgoals.setText(String.valueOf(total));
                System.out.println(total);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les exceptions liées à la connexion ou à la requête SQL
        }
    }
    public void setWinnerLabel(int tournamentId) {
        try {
            Connection connection = MysqlConnection.getDBConnection();
            String sqlCheck = "SELECT COUNT(*) AS countMatches FROM matches WHERE idtournament = ?";
            PreparedStatement psCheck = connection.prepareStatement(sqlCheck);
            psCheck.setInt(1, tournamentId);

            ResultSet rsCheck = psCheck.executeQuery();
            if (rsCheck.next()) {
                int countMatches = rsCheck.getInt("countMatches");
                if (countMatches > 0) {
                    // Si des matchs existent pour ce tournoi
                    String sql = "SELECT t.teamName FROM teams t " +
                            "INNER JOIN (SELECT idwinner, SUM(CASE WHEN sw > sl THEN 3 WHEN sw = sl THEN 1 ELSE 0 END) AS Pointes " +
                            "FROM matches WHERE idtournament = ? GROUP BY idwinner) AS p " +
                            "ON t.id = p.idwinner ORDER BY Pointes DESC LIMIT 1";

                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setInt(1, tournamentId);

                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        String winnerName = rs.getString("teamName");
                        // Mettre le nom de l'équipe gagnante dans le label "winner"
                        winnerLabel.setText(winnerName);
                        
                    } else {
                        // Aucune équipe trouvée pour le tournoi
                        winnerLabel.setText("Pas encore de gagnant");
                    }

                    rs.close();
                    ps.close();
                } else {
                    // Aucun match pour le tournoi
                    winnerLabel.setText("Pas encore de matchs");
                }
                
            }

            rsCheck.close();
            psCheck.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    

	   

    
    }
