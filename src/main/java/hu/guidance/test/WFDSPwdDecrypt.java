package hu.guidance.test;

import java.math.BigInteger;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class WFDSPwdDecrypt {
	public static void main(String[] args) throws Exception {
        String secret = "-2dd70546b2d4394bd12bcc49c08bc775";
        String keyVal = "jaas is the way";
        SecretKeySpec key = new SecretKeySpec(keyVal.getBytes(), "Blowfish");
        BigInteger n = new BigInteger(secret, 16);
        byte[] encoding = n.toByteArray();
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(2, key);
        byte[] decode = cipher.doFinal(encoding);
        String decodedVal = new String(decode);
        System.out.println(decodedVal);
    }
}
