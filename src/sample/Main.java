package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import  javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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

            // password label and Text field.
            Label passLabel = new Label("Password");
            GridPane.setConstraints(passLabel, 0, 0);

            TextField inputPass = new TextField();
            inputPass.setPromptText("Example: xyzt1993");
            GridPane.setConstraints(inputPass, 1,0);


        // encryption label and text field;
            Label plaintextLable = new Label("Text");
            GridPane.setConstraints(plaintextLable, 0, 1);

            TextArea inputPlaintext = new TextArea();
            inputPlaintext.setPromptText("Example: Today is a sunny day");
            GridPane.setConstraints(inputPlaintext, 1, 1);


            //Creating the encrypt  button
             Button encryptButton = new Button("Encrypt");
             GridPane.setConstraints(encryptButton, 0, 2);


            // encryption label and text field;
            Label Encrypted_Lable = new Label("Text");
            GridPane.setConstraints(Encrypted_Lable, 0, 3);

            TextArea Encrypted_Text = new TextArea();
            Encrypted_Text.setPromptText("Example: Today is a sunny day");
            GridPane.setConstraints(Encrypted_Text, 1, 3);


            grid.getChildren().addAll(plaintextLable, inputPlaintext, passLabel, inputPass, encryptButton, Encrypted_Text, Encrypted_Lable);
            Scene scene = new Scene(grid, 600, 600);
            window.setScene(scene);
            window.show();



    }


    public static void main(String[] args) {
        launch(args);
    }
}
