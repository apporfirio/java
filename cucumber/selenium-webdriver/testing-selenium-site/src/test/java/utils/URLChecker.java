package utils;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class URLChecker {

    public static boolean checkStatusOk(String url) {
        try {
            int status = getResponseCode(url.trim());
            return status >= 200 && status <= 399;
        }
        catch (Exception e) {
            System.err.println("Não foi possível verificar status da URL '" + url + "': " + e);
            return false;
        }
    }

    private static int getResponseCode(String urlToCall) throws IOException {
        HttpURLConnection conn = getConnection(urlToCall);

        conn.setRequestMethod("GET");
        conn.connect();

        return conn.getResponseCode();
    }

    private static HttpURLConnection getConnection(String urlToCall) throws IOException {
        URL url = new URL(urlToCall);

        if (urlToCall.startsWith("https"))
            return (HttpsURLConnection) url.openConnection();
        else
            return (HttpURLConnection) url.openConnection();
    }
}
