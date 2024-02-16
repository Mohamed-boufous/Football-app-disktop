package application;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class controller {
	  @FXML
	    private TableView<tournement> table;

	    @FXML
	    private TableColumn<tournement, String> tnoms;
	    @FXML
	    private TableColumn<tournement,Void> actionsColumn;

    @FXML
    private BorderPane bp;

    @FXML
    void creattournement(MouseEvent event) throws IOException {
    	Pane root=FXMLLoader.load(getClass().getResource("tournementinfospage1.fxml"));
    	bp.setCenter(root);
    }
    ObservableList<tournement> data;

    @FXML
    void initialize() {
        data = FXCollections.observableArrayList();
        
        tnoms.setCellValueFactory(new PropertyValueFactory<>("tournementName"));
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        actionsColumn.setCellFactory(param -> new TableCell<tournement, Void>() { // Modification ici
            private final StackPane pane = new StackPane();
            private final Button editButton = new Button("Editer");

            {
            	editButton.setId("Button");
            	editButton.getStyleClass().add("Button");
            	editButton.setOnAction((ActionEvent event) -> {
            	    tournement rowData = getTableView().getItems().get(getIndex());
            	    int tournamentId = rowData.getId();
                    
                    
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Side&topBarre.fxml"));
                        Parent root = loader.load();

                        Contscrool controller = loader.getController();
                        controller.loadTournamentInfo(tournamentId);

                        Stage newStage = new Stage();
                        newStage.setScene(new Scene(root));
                        newStage.setMaximized(true); // Définit la fenêtre en mode maximisé
                        newStage.show();

                        // Fermez la fenêtre actuelle si nécessaire
                        
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });

                pane.getChildren().add(editButton);
                StackPane.setAlignment(editButton, Pos.CENTER);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(pane);
                }
            }
        });

        table.setItems(data);
        getAll();
    }




    
    public void getAll() {
        data.clear();
        try {
            Connection connection = MysqlConnection.getDBConnection();
            
            String sql = "SELECT id, tournementName FROM `tournoments`";

            PreparedStatement ps = connection.prepareStatement(sql);
            
            ResultSet results = ps.executeQuery();
            while (results.next()) {
            	
                String nom = results.getString("tournementName");
                
                int id = results.getInt("id");
                data.add(new tournement(id ,nom));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    	

}
