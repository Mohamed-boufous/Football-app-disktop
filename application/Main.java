package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("hi.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true); // Maximiser la premi�re fen�tre
            
            // Charger une image pour l'ic�ne de la fen�tre
            Image icon = new Image(getClass().getResourceAsStream("Frame 1 (1) 2.png"));
            primaryStage.getIcons().add(icon); // D�finir l'ic�ne de la fen�tre principale
            
            primaryStage.show();

            // Cr�er une Timeline pour changer la sc�ne apr�s 5 secondes
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
                try {
                    // Charger le nouveau fichier FXML apr�s 5 secondes
                    Parent newRoot = FXMLLoader.load(getClass().getResource("TOURNEMENTEXISTE.fxml"));
                    Scene newScene = new Scene(newRoot);
                    newScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

                    Stage secondStage = new Stage(); // Cr�er un nouveau stage pour la seconde fen�tre
                    secondStage.setScene(newScene);
                    secondStage.setMaximized(true); // Maximiser la seconde fen�tre
                    
                    // D�finir l'ic�ne pour la seconde fen�tre (si n�cessaire)
                    secondStage.getIcons().add(icon);

                    secondStage.show(); // Afficher la seconde fen�tre

                    primaryStage.close(); // Fermer la premi�re fen�tre
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }));
            timeline.setCycleCount(1); // Ex�cute une seule fois
            timeline.play(); // Lancer le d�lai
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
