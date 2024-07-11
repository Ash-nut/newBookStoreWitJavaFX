package cn.helloworld1999.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class SceneSwitcher {

    public static void switchScene(Stage stage, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource("/cn/helloworld1999/controller/" + fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Stage getStageFromEvent(ActionEvent event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }
}
