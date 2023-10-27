package URLShortener;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Base62 {
    private static String BASE62_STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static int BASE62 = 62;
    private AtomicInteger atomicInteger = new AtomicInteger(1);
    private HashMap<String, String> map = new HashMap<String, String>();

    public String getShortnerUrl(String url, int limit) {
        if(map.containsKey(url)) {
            return map.get(url);
        } else {
            String shortUrl = getBase62(limit);
            map.put(url, shortUrl);
            return shortUrl;
        }
    }


    private String getBase62(int num) {
        StringBuffer sb = new StringBuffer();
        //int hashcode = atomicInteger.getAndIncrement();
        while (num / BASE62 > 0) {
            sb.append(BASE62_STR.charAt(num % BASE62));
             num /= BASE62;
            if(sb.length() >= 7) break;
        }
        /*sb.append(BASE62_STR.charAt());
        while(sb.length() < limit) {
            sb.append(BASE62_STR.charAt(0));
        }*/
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Base62 n = new Base62();
        String str;
        str=n.getShortnerUrl("https://www.geeksforgeeks.org/how-to-design-a-tiny-url-or-url-shortener/",7);
        System.out.println(str);
    }
}
