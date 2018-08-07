import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TinyUrl {
    String keyString;
    private Random r;
    private Map<String, String> longToShort;
    private Map<String, String> shortToLong;
    TinyUrl() {
        keyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        longToShort = new HashMap<String, String>();
        shortToLong = new HashMap<String, String>();
        r  = new Random();
    }
    // Encodes a URL to a shortened URL.
    private String randomUrl(int len) {
        StringBuilder sb = new StringBuilder();
        while(len-- > 0) {
            int idx = r.nextInt(62);
            sb.append(keyString.charAt(idx));
        }
        return sb.toString();
    }

    public String encode(String longUrl) {
        if (longToShort.containsKey(longUrl)) return longToShort.get(longUrl);
        String shortUrl = randomUrl(2);
        while (shortToLong.containsKey(shortUrl)) {
            shortUrl = randomUrl(2);
        }
        longToShort.put(longUrl, shortUrl);
        shortToLong.put(shortUrl, longUrl);
        return shortUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return shortToLong.get(shortUrl);
    }
}