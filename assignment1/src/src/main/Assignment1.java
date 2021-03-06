package main;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class Assignment1 {

	private String[] studentNumbers = {"s1227874", "s0138746"}; 

    private final static int AES_KEY_SIZE = 128;
    private final static int RSA_KEY_SIZE = 1024;
    
    private final static String MIXNET_HOSTNAME = "pets.ewi.utwente.nl";
    private final static int MIXNET_PORT = 57327;
    private final static int MIXNET_NODE_COUNT=4;

    private final static String[] pubKeys = {
    	"-----BEGIN PUBLIC KEY-----MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC12FPfdepBrzZc9oYrAQMutj/YDSHbVc+6kYMG2igq5aShYDkHUUa63l/u4D6w0d7FXCVvFShDKT9vawVJn8Qd1fyRINJrkufYRD4/n0e6JIGQ4FctpMMkNWAJsqWiNdA54dDrHEE210epDXIVI7e+mOVSme4vOmg1Gfqm7vdc5QIDAQAB-----END PUBLIC KEY-----",
    	"-----BEGIN PUBLIC KEY-----MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDVhJycScH1rIP6p/c6mMxrDmcKUqEWbXUYMdD2HXtl7tdc1giZaCHMLxNL2loC1CFePW4UbHUVkuI3HBoMHuCm6CiXl3/1nvpRglLw9bVJCU4yLn/DgyNYwOQBK25sj1DiG+mXgIvRpV7Rk44/FltMU1oLUmaBHozLAEcT/y5HJQIDAQAB-----END PUBLIC KEY-----",
    	"-----BEGIN PUBLIC KEY-----MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDWbUMbBFT9KdUYs5d/tWh7qR5ccBneQN6roVqqVKrxArV0UZMjmvDeyW2dJmmnbKaE6+AsicRWmVXzVWjb3cFHqfnXIkKIP+sskpquSkT7MrejL1IvgKQSy5JTp3EWmLs17fAeJF27bxCfPi0b9ccs1rMo1oEdTA+nuetGeXnCsQIDAQAB-----END PUBLIC KEY-----",
    	"-----BEGIN PUBLIC KEY-----MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDD4qir1SKdQDZhCNwM1eMIWwYBviWPc9BZtp/PZS08TEt4V9PhFyuGyZ4v/UiA15JIqNUaK51AUwyqhkDHwmB5zZ9VpiR8xs8Ij8dFpi5Pm/aE2gmSnkPwVL5FgzJKJRqtUeX+yusDOyC9fYDaL8f13BgXwkMx3NCZpSNev8KT8QIDAQAB-----END PUBLIC KEY-----"
    	};
    private Key[] symKeys = new Key[MIXNET_NODE_COUNT];
    

    public static void main(String args[]) {
    	Assignment1 ass1 = new Assignment1();

    }
    
    public Assignment1() {
    	System.out.println("Starting assignment1");
    	this.generateSymKeys();
    	
    	System.out.println("done");
    }

    private void generateSymKeys() {
    	try {
    		for (int i=0; i<MIXNET_NODE_COUNT; i++) {
    			this.symKeys[i] = this.generateAESKey();    		}
    	} catch (Exception ex) {
    		System.out.println("Exception generating AES keys: " + ex.getMessage());
    		ex.printStackTrace();
    	}
    }

    private Key generateAESKey() throws NoSuchAlgorithmException, NoSuchProviderException {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    	 KeyGenerator KeyGen = KeyGenerator.getInstance("AES", "CBC");
    	 KeyGen.init(Assignment1.AES_KEY_SIZE);
    	 Key key = KeyGen.generateKey();
        return key;
    }
    
    public String encryptForNode(String input, int targetNode) {
    	return "";
    }

    public void sendMessage(String msg) {
    	String result = "";
    	// encrypt for all nodes, starting with last one
    	for (int i=MIXNET_NODE_COUNT-1; i<=0; i--) {
    		result = this.encryptForNode(result, i);
    	}
    	
    	// @TODO create TCP connection to MIXNET and transmit data
    }
    
    public void sendStudentNumbers() {
    	this.sendMessage(this.studentNumbers[0] + " & " + this.studentNumbers[1]);
    }


}
