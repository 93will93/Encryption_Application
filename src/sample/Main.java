package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
    This application was created by William Becerra Gonzalez.
    Feel free to use it for non commercial use.

*/
public class Main extends Application {

    private Stage window;
    private Button proceed_Button;
    private Label KeyLabel, inputText_Label, output_Label;
    private TextField inputKey;
    private TextArea input_Text,output_Text;
    private ComboBox<String> optionBox;

    @Override
    public void start(Stage primaryStage) throws Exception{
            window = primaryStage;
            window.setTitle("Encryption Application");

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10,10,10,10));
            grid.setVgap(8);
            grid.setHgap(10);

            // password label and Text field.
            KeyLabel = new Label("Encryption Key");
            GridPane.setConstraints(KeyLabel, 0, 0);

            inputKey = new TextField();
            inputKey.setPromptText("Example: xyzt1993");
            GridPane.setConstraints(inputKey, 1,0);


             // encryption label and text field;
            inputText_Label= new Label("Plain Text");
            GridPane.setConstraints(inputText_Label, 0, 1);

            input_Text = new TextArea();
            input_Text.setPromptText("Example: Today is a sunny day");
            GridPane.setConstraints(input_Text, 1, 1);



             //Creating the encrypt  button
            proceed_Button = new Button("Proceed");
            proceed_Button.setPrefWidth(100);

            //creating a comboBox
            optionBox = new ComboBox<>();
            optionBox.getItems().addAll("Encrypt", "Decrypt");
            optionBox.setPromptText("Choice?");
            optionBox.setPrefWidth(120);
            optionBox.setOnAction(e->{
                String choice = optionBox.getValue();
                if (choice.equals("Encrypt")){
                    inputText_Label.setText("Plain Text");
                    output_Label.setText("Encrypted Text");

                }else if (choice.equals("Decrypt")) {
                    inputText_Label.setText("Encrypted Text");
                    output_Label.setText("Decrypted Text");
                }
            });




            //Wrapping the button and Drop Down list into one HBox
            HBox buttonAndList = new HBox();
            buttonAndList.getChildren().addAll(optionBox, proceed_Button);
            //buttonAndList.getChildren().addAll(encDecChoice, proceed_Button);
            buttonAndList.setSpacing(5);
            GridPane.setConstraints(buttonAndList, 0, 2);


            // encryption label and text field;
            output_Label = new Label("Encrypted Text");
            GridPane.setConstraints(output_Label, 0, 3);

            output_Text = new TextArea();
            output_Text.setPromptText("Example: Today is a sunny day");
            GridPane.setConstraints(output_Text, 1, 3);



            // Handling the press of Encryption Button. encryptionEvent() can be found outside of start
            proceed_Button.setOnAction(e-> buttonPressEvent());







            // Including elements to the Scene and showing the window.
            grid.getChildren().addAll(buttonAndList, inputText_Label, input_Text, KeyLabel, inputKey, output_Text, output_Label);
            Scene scene = new Scene(grid, 600, 500);
            window.setScene(scene);
            window.show();



    }

    private void encryptionEvent() { // This function only works if the
        String text = input_Text.getText();
        String key = inputKey.getText();
        String newKey = AES_encryption.correctingKey(key);

        try{
            AES_encryption Aes = new AES_encryption(newKey);
            String encdata = Aes.AESEncrypt(text);
            output_Text.setText(encdata);

            // String decdata = aes.AESDecrypt(encdata);
            //System.out.println("Decrypted data: " + decdata);

        } catch (Exception ex) {
            Logger.getLogger(AES_encryption.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
    private void decryptionEvent(){
        String text = input_Text.getText();
        String key = inputKey.getText();
        String newKey = AES_encryption.correctingKey(key);

        try{
            AES_encryption Aes = new AES_encryption(newKey);

            String decdata = Aes.AESDecrypt(text);
            output_Text.setText(decdata);

        } catch (Exception ex) {
            Logger.getLogger(AES_encryption.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    private void buttonPressEvent(){
        boolean validKey = AES_encryption.isKeyValid(inputKey.getText()); //checks the input Key is valid

        String choice = optionBox.getValue();        // encrypt of decrypt choice
        try {
            if (validKey == true) {
                if (choice.equals("Encrypt")) {
                    encryptionEvent();
                } else if (choice.equals("Decrypt")) {
                    decryptionEvent();
                }

            }
        }catch(NullPointerException v){
            String message = "Error: Select one of the Encrypt/Decrypt choices.";
            //message = message + v;
            InvalidKeyAlert.display(message);
        }

    }



    public static void main(String[] args) { launch(args); }

}
