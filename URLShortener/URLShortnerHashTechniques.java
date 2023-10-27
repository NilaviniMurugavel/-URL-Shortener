package URLShortener;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class URLShortnerHashTechniques {

    public String urlshortnerMD5Hash(String url, int shortnerLen) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(url.getBytes());
        byte[] byteMD5Hash = messageDigest.digest();
        BigInteger bigInteger = new BigInteger(1, byteMD5Hash);
        String hash = bigInteger.toString(16);
        while (hash.length() < 32) {
            hash = "0" + hash;
        }
        return hash.substring(0, shortnerLen);
    }

    public String urlShortnerSH1Hash(String url, int shortnerLen) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        messageDigest.update(url.getBytes());
        byte[] byteMD5Hash = messageDigest.digest();
        BigInteger bigInteger = new BigInteger(1, byteMD5Hash);
        String hash = bigInteger.toString(16);
        while (hash.length() < 32) {
            hash = "0" + hash;
        }
        return hash.substring(0, shortnerLen);
    }

    public String urlShortnerSH256Hash(String url, int shortnerLen) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(url.getBytes());
        byte[] byteMD5Hash = messageDigest.digest();
        BigInteger bigInteger = new BigInteger(1, byteMD5Hash);
        String hash = bigInteger.toString(16);
        while (hash.length() < 32) {
            hash = "0" + hash;
        }
        return hash.substring(0, shortnerLen);
    }


}