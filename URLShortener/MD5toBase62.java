package URLShortener;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class MD5toBase62 {

    private static final char[] BASE_62_DIGITS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final BigInteger SIXTY_TWO = BigInteger.valueOf(62L);

    public static void toBase62(String md5) {
        BigInteger value = new BigInteger(md5, 16);
         toBase62(value);
    }

    public String urlshortnerMD5Hash(String url, int shortnerLen) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(url.getBytes());
        String str;
        byte[] byteMD5Hash = messageDigest.digest();
        BigInteger bigInteger = new BigInteger(1, byteMD5Hash);
        /*String hash = bigInteger.toString(16);
        while (hash.length() < 32) {
            hash = "0" + hash;
        }
        return hash.substring(0, shortnerLen);*/
        str = toBase62(bigInteger);
        return str;
    }

    /*public static String fromBase62(String base62) {
        BigInteger value = fromBase62ToInteger(base62);
        String str = value.toString(16);
        int len = str.length();
        if (len < 32) {
            StringBuilder sb = new StringBuilder(32);
            int leadingZeroesCount = 32-len;
            for (int i = 0; i < leadingZeroesCount; ++i) {
                sb.append('0');
            }
            sb.append(str);
            str = sb.toString();
        }
        return str;
    }*/

    private static String toBase62(BigInteger value) {
        String base62 = new String();
        //if ((value.signum() < 0)) base62.append('-');
        BigInteger bal = value.abs();
        while (bal.compareTo(SIXTY_TWO) > 0) {
            int digit = bal.mod(SIXTY_TWO).intValue();
            base62 = base62+BASE_62_DIGITS[digit];
            bal = bal.divide(SIXTY_TWO);
        }
        int digit = bal.mod(SIXTY_TWO).intValue();
        base62 =base62+BASE_62_DIGITS[digit];
        base62=base62.substring(0,7);
        System.out.println(base62.toString());
        /*String result="https://www.shorturl"+ base62.toString();
        return result;*/
        return base62.toString();
    }

    /*private static BigInteger fromBase62ToInteger(String base62) {
        BigInteger value = BigInteger.ZERO;
        BigInteger multiplier = BigInteger.ONE;
        for (int i = 0; i < base62.length(); ++i) {
            char c = base62.charAt(i);
            BigInteger digit = digitValue(c);
            value = value.add(multiplier.multiply(digit));
            multiplier = multiplier.multiply(SIXTY_TWO);
        }
        return value;
    }

    private static BigInteger digitValue(char c) {
        for (int i = 0; i < BASE_62_DIGITS.length; ++i) {
            if (BASE_62_DIGITS[i] == c) {
                return BigInteger.valueOf(i);
            }
        }
        throw new NumberFormatException("Invalid base 62 character: " + c);
    }*/

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MD5toBase62 l = new MD5toBase62();
        String str;
        l.urlshortnerMD5Hash("https://www.javatpoint.com/java-md5-hashing-example",7);

    }
}
