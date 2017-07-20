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

import java.util.logging.Level;
import java.util.logging.Logger;


public class Main extends Application {

    Stage window;
    Button encryptButton, decryptButton;
    Label passLabel, plaintextLable, Encrypted_Lable;
    TextField inputPass;
    TextArea inputPlaintext,Encrypted_Text;

    @Override
    public void start(Stage primaryStage) throws Exception{
            window = primaryStage;
            window.setTitle("Encryption Application");

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10,10,10,10));
            grid.setVgap(8);
            grid.setHgap(10);

            // password label and Text field.
            passLabel = new Label("Password");
            GridPane.setConstraints(passLabel, 0, 0);

            inputPass = new TextField();
            inputPass.setPromptText("Example: xyzt1993");
            GridPane.setConstraints(inputPass, 1,0);


        // encryption label and text field;
            plaintextLable = new Label("Text");
            GridPane.setConstraints(plaintextLable, 0, 1);

            inputPlaintext = new TextArea();
            inputPlaintext.setPromptText("Example: Today is a sunny day");
            GridPane.setConstraints(inputPlaintext, 1, 1);



            //Creating the encrypt  button
            encryptButton = new Button("Encrypt");
            GridPane.setConstraints(encryptButton, 0, 2);


            // encryption label and text field;
            Encrypted_Lable = new Label("Encrypted Text");
            GridPane.setConstraints(Encrypted_Lable, 0, 3);

            Encrypted_Text = new TextArea();
            Encrypted_Text.setPromptText("Example: Today is a sunny day");
            GridPane.setConstraints(Encrypted_Text, 1, 3);

            // Handling the press of Encryption Button. encryptionEvent() can be found outside of start
            encryptButton.setOnAction(e-> {
                boolean validPass = isKeyValid(inputPass.getText()); //checks the input Key is valid

                if (validPass == true){
                    encryptionEvent();
                }

            });





            // Including elements to the Scene and showing the window.
            grid.getChildren().addAll(plaintextLable, inputPlaintext, passLabel, inputPass, encryptButton, Encrypted_Text, Encrypted_Lable);
            Scene scene = new Scene(grid, 600, 500);
            window.setScene(scene);
            window.show();



    }

    private void encryptionEvent() { // This function only works if the
        String text = inputPlaintext.getText();
        String pass = inputPass.getText();
        String newPass = validatingPassword(pass);

        try{
            AES_encryption aes = new AES_encryption(newPass);
            String encdata = aes.AESEncrypt(text);
            Encrypted_Text.setText(encdata);

            System.out.println("Encrypted data: "+ encdata);

            // String decdata = aes.AESDecrypt(encdata);
            //System.out.println("Decrypted data: " + decdata);

        } catch (Exception ex) {
            Logger.getLogger(AES_encryption.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    private String validatingPassword(String pass){

        while(pass.length() != 16){
            if (pass.length() >= 6 && pass.length() < 16){
                // padding is needed
                pass = pass + "#";
            }else if( pass.length() == 16) {
                //this is when key is exactly 16 characters long
                return  pass;
            }else {
                InvalidKeyAlert.display("Unexpected error: Please try a different Key.");
            }
        }

        return pass;
    }

    //returns true if the encryption Key is between 6 and 16 characters long
    private static boolean isKeyValid(String pass){
        String message;
        if( pass.length()< 6 ){
            message = "Key must be greater than 6 characters";
            InvalidKeyAlert.display(message);
            return false;
        }else if (pass.length() >= 6 && pass.length() <=16){
            return true;
        }else if (pass.length() > 16){
            message = "Key must be less than 16 characters long";
            InvalidKeyAlert.display(message);
            return false;
        }else {
            return false;
        }
    }


    public static void main(String[] args) { launch(args); }

}
