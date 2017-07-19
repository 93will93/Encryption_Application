package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import  javafx.scene.control.Label;
import  javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application {

    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
            window = primaryStage;
            window.setTitle("Encryption Application");

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10,10,10,10));
            grid.setVgap(8);
            grid.setHgap(10);

            // encryption label and text field;
            Label plaintextLable = new Label("plaintext");
            GridPane.setConstraints(plaintextLable, 0, 0);

            TextField inputPlaintext = new TextField();
            inputPlaintext.setPromptText("Example: Today is a sunny day");
            GridPane.setConstraints(inputPlaintext, 1, 0);

            // password label and Text field.
            Label passLabel = new Label("Password");
            GridPane.setConstraints(passLabel, 0, 1);

            TextField inputPass = new TextField();
            inputPass.setPromptText("Example: xyzt1993");
            GridPane.setConstraints(inputPass, 1,1);

            //Creating the encrypt  button
             Button encryptButton = new Button("Encrypt");
             GridPane.setConstraints(encryptButton, 0, 2);

            grid.getChildren().addAll(plaintextLable, inputPlaintext, passLabel, inputPass, encryptButton);
            Scene scene = new Scene(grid, 500, 500);
            window.setScene(scene);
            window.show();



    }


    public static void main(String[] args) {
        launch(args);
    }
}
