/**
 * 
 */
package CipherTools;

import java.math.BigInteger;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author michaelAM
 *
 */
public class CipherTool {

    /**
     * @param args
     */
    
    private String IV;
    private String encryptionKey;
    
    public CipherTool(String IV,String encryptionKey){
        this.IV = IV;
        this.encryptionKey = encryptionKey;
    }
    
    public CipherTool(BigInteger IV,BigInteger encryptionKey){
        this.IV = IV.toString();
        this.encryptionKey = encryptionKey.toString();
    }
    
    public String getIV() {
        return IV;
    }

    public void setIV(String iV) {
        IV = iV;
    }

    public String getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    
    public byte[] encrypt(String plainText) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec key = new SecretKeySpec(getEncryptionKey().getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(getIV().getBytes("UTF-8")));
        return cipher.doFinal(plainText.getBytes("UTF-8"));
      }

      public String decrypt(byte[] cipherText) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec key = new SecretKeySpec(getEncryptionKey().getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(getIV().getBytes("UTF-8")));
        return new String(cipher.doFinal(cipherText),"UTF-8");
      }

}
