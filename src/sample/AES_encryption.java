package sample;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * Created by Will on 2017-07-18.
 */


public class AES_encryption {

    private static final String ALGO = "AES";
    private byte[] keyValue;

    public AES_encryption(String key){
        keyValue = key.getBytes();
    }
    public String AESEncrypt(String Data) throws Exception{
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;

    }

    public String AESDecrypt( String encryptedData) throws Exception{
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decodedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    private Key generateKey() throws Exception{
        Key key = new SecretKeySpec(keyValue, ALGO);
        return  key;
    }


    public static String correctingKey(String key){

        while(key.length() != 16){
            if (key.length() >= 6 && key.length() < 16){
                // padding is needed
                key = key + "#";
            }else if( key.length() == 16) {
                //this is when key is exactly 16 characters long
                return  key;
            }else {
                InvalidKeyAlert.display("Unexpected error: Please try a different Key.");
            }
        }

        return key;
    }

    public static boolean isKeyValid(String key){
        String message;
        if( key.length()< 6 ){
            message = "Key must be greater than 6 characters";
            InvalidKeyAlert.display(message);
            return false;
        }else if (key.length() >= 6 && key.length() <=16){
            return true;
        }else if (key.length() > 16){
            message = "Key must be less than 16 characters long";
            InvalidKeyAlert.display(message);
            return false;
        }else {
            return false;
        }
    }


}
