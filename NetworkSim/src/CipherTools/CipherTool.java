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
    
    public byte[] encryptB(String plainText) throws Exception {
        System.out.println("text "+plainText);
        int toAdd = 16-plainText.length()%16;
        for(int i=0;i<toAdd;i++){
            plainText+="*";
        }
        System.out.println("text "+plainText);

        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(getEncryptionKey().getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(getIV().getBytes("UTF-8")));
        byte[] returnVal = cipher.doFinal(plainText.getBytes("UTF-8"));
        printByteArr(returnVal);
        return returnVal;
    }

    public byte[] encryptB(byte[] plainTextOri) throws Exception {
        int toAddB = 16-plainTextOri.length%16;
        byte[] plainText = new byte[plainTextOri.length + toAddB];
        for(int i=0;i<plainText.length;i++){
            if(i<plainTextOri.length){
                plainText[i] = plainTextOri[i];
            }else{
                plainText[i] ="*".getBytes()[0];

            }
        }
        printByteArr(plainText);

        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(getEncryptionKey().getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(getIV().getBytes("UTF-8")));
        byte[] returnVal = cipher.doFinal(plainText);
        printByteArr(returnVal);

        return returnVal;
    }
    
    public String encryptS(byte[] plainTextOri) throws Exception {
        int toAddB = 16-plainTextOri.length%16;
        byte[] plainText = new byte[plainTextOri.length + toAddB];
        for(int i=0;i<plainText.length;i++){
            if(i<plainTextOri.length){
                plainText[i] = plainTextOri[i];
            }else{
                plainText[i] ="*".getBytes()[0];

            }
        }
        printByteArr(plainText);

        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(getEncryptionKey().getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(getIV().getBytes("UTF-8")));
        String returnStr = new String(cipher.doFinal(plainText),"UTF-8");
        System.out.println("text "+returnStr);

        return returnStr;
    }
    
    public String encryptS(String plainText) throws Exception {
        System.out.println("text "+plainText);

        int toAdd = 16-plainText.length()%16;
        for(int i=0;i<toAdd;i++){
            plainText+="*";
        }
        System.out.println("text "+plainText);

        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(getEncryptionKey().getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(getIV().getBytes("UTF-8")));
        String returnStr = new String(cipher.doFinal(plainText.getBytes("UTF-8")),"UTF-8");
        System.out.println("text "+returnStr);

        return returnStr;
    }
    
    public String decryptS(byte[] cipherText) throws Exception{
        printByteArr(cipherText);
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(getEncryptionKey().getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(getIV().getBytes("UTF-8")));
        String returnStr = new String(cipher.doFinal(cipherText),"UTF-8").replace("*", "");
        System.out.println("text "+returnStr);

        return returnStr;
    }


    public String decryptS(String cipherText) throws Exception{
        System.out.println("text "+cipherText);
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(getEncryptionKey().getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(getIV().getBytes("UTF-8")));
        String returnStr = new String(cipher.doFinal(cipherText.getBytes("UTF-8")),"UTF-8").replace("*", "");
        System.out.println("text "+returnStr);

        return returnStr;
    }

    public byte[] decryptB(byte[] cipherText) throws Exception{
        printByteArr(cipherText);
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(getEncryptionKey().getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(getIV().getBytes("UTF-8")));
        byte[] returnValTemp = cipher.doFinal(cipherText);
        printByteArr(returnValTemp);

        byte[] returnVal = new byte[returnValTemp.length-count(returnValTemp,"*".getBytes()[0])];
        for(int i=0;i<returnVal.length;i++){
            returnVal[i]=returnValTemp[i];
        }
        printByteArr(returnVal);

        return returnVal;
    }


    public byte[] decryptB(String cipherText) throws Exception{
        System.out.println("text "+cipherText);
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(getEncryptionKey().getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(getIV().getBytes("UTF-8")));
        byte[] returnValTemp = cipher.doFinal(cipherText.getBytes("UTF-8"));
        printByteArr(returnValTemp);

        byte[] returnVal = new byte[returnValTemp.length-count(returnValTemp,"*".getBytes()[0])];
        for(int i=0;i<returnVal.length;i++){
            returnVal[i]=returnValTemp[i];
        }
        printByteArr(returnVal);

        return returnVal;
    }
    
    public byte[] replace(byte[] arr, byte find, byte replace) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == find) {
                arr[i] = replace;
            }
        }
        return arr;
    }
    
    public int count(byte[] arr, byte find) {
        int count =0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == find) {
                count++;
            }
        }
        return count;
    }
    
    public void printByteArr(byte[] arr){
        System.out.print("arr:  ");
        for (int i=0; i<arr.length; i++)
          System.out.print(new Integer(arr[i])+" ");
        System.out.println("");
    }
    
}
