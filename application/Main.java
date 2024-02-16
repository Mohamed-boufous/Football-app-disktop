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
            primaryStage.setMaximized(true); // Maximiser la première fenêtre
            
            // Charger une image pour l'icône de la fenêtre
            Image icon = new Image(getClass().getResourceAsStream("Frame 1 (1) 2.png"));
            primaryStage.getIcons().add(icon); // Définir l'icône de la fenêtre principale
            
            primaryStage.show();

            // Créer une Timeline pour changer la scène après 5 secondes
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
                try {
                    // Charger le nouveau fichier FXML après 5 secondes
                    Parent newRoot = FXMLLoader.load(getClass().getResource("TOURNEMENTEXISTE.fxml"));
                    Scene newScene = new Scene(newRoot);
                    newScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

                    Stage secondStage = new Stage(); // Créer un nouveau stage pour la seconde fenêtre
                    secondStage.setScene(newScene);
                    secondStage.setMaximized(true); // Maximiser la seconde fenêtre
                    
                    // Définir l'icône pour la seconde fenêtre (si nécessaire)
                    secondStage.getIcons().add(icon);

                    secondStage.show(); // Afficher la seconde fenêtre

                    primaryStage.close(); // Fermer la première fenêtre
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }));
            timeline.setCycleCount(1); // Exécute une seule fois
            timeline.play(); // Lancer le délai
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
