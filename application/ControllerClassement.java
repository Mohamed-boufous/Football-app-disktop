package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerClassement {
    @FXML
    private TableView<Team> classementTable;
    @FXML
    private TableColumn<Team, Integer> rankColumn;
    @FXML
    private TableColumn<Team, String> teamColumn;
    @FXML
    private TableColumn<Team, Integer> playColumn;
    @FXML
    private TableColumn<Team, Integer> gdColumn;
    @FXML
    private TableColumn<Team, Integer> pointsColumn;

    private List<Team> classementEntries;

    int tournamentId;

    public void getid(int idt) {
        this.tournamentId = idt;
        System.out.println(tournamentId);
        loadDataFromDatabase();
    }

    @FXML
    public void initialize() {
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        teamColumn.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        playColumn.setCellValueFactory(new PropertyValueFactory<>("play"));
        gdColumn.setCellValueFactory(new PropertyValueFactory<>("gd"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));

        classementEntries = new ArrayList<>();
        classementTable.getItems().addAll(classementEntries);
    }

    private void loadDataFromDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/insertournemntsinfos";
            String user = "root";
            String password = "";

            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "SELECT e.teamName AS teamName,\r\n"
                    + "       COUNT(m.id) AS matches_played,\r\n"
                    + "       (SUM(CASE \r\n"
                    + "                 WHEN e.id = m.idwinner THEN m.sw \r\n"
                    + "                 ELSE m.sl \r\n"
                    + "             END) - SUM(CASE \r\n"
                    + "                             WHEN e.id = m.idlosser THEN m.sw \r\n"
                    + "                             ELSE m.sl \r\n"
                    + "                         END)) AS goal_difference,\r\n"
                    + "       SUM(CASE \r\n"
                    + "                 WHEN e.id = m.idwinner AND m.sw > m.sl THEN 3 \r\n"
                    + "                 WHEN e.id = m.idlosser AND m.sw < m.sl THEN 3 \r\n"
                    + "                 WHEN m.sw = m.sl THEN 1 \r\n"
                    + "                 ELSE 0 \r\n"
                    + "             END) AS points\r\n"
                    + "FROM teams e\r\n"
                    + "LEFT JOIN matches m ON e.id = m.idwinner OR e.id = m.idlosser\r\n"
                    + "WHERE m.idtournament = ?\r\n"
                    + "GROUP BY e.id\r\n"
                    + "ORDER BY points DESC, goal_difference DESC;\r\n";


            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, tournamentId);
            ResultSet resultSet = preparedStatement.executeQuery();

            int rank = 1;
            while (resultSet.next()) {
                String team = resultSet.getString("teamName");
                int play = resultSet.getInt("matches_played");
                int gd = resultSet.getInt("goal_difference");
                int points = resultSet.getInt("points");
                Team entry = new Team(tournamentId, team, play, gd, points, rank);
                classementEntries.add(entry);
                rank++;
            }

            classementTable.getItems().setAll(classementEntries);

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
