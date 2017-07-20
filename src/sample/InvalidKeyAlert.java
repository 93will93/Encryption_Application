package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import  javafx.scene.control.Label;
import javafx.scene.control.Button;


import java.awt.*;

/**
 * Created by Will on 2017-07-20.
 */
public class InvalidKeyAlert {
    public static void display(String message){
        Stage window = new Stage();
        window.setTitle("Invalid Key");
        window.initModality(Modality.APPLICATION_MODAL); // This prevents the user form using other windows of the app.
        window.setMinWidth(250);

        Label label = new Label(message);
        Button okButton = new Button("OK");
        okButton.setOnAction(e -> window.close());


        //Setting up layout

        VBox layout = new VBox(10); // 10 pixel spacing.
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, okButton);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();

    }
}
