package utils;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;

public class URLChecker {

    public static boolean checkStatusOk(String url) {
        try {
            int status = getResponseCode(url);
            return status == 200;
        }
        catch (Exception e) {
            System.err.println("Não foi possível verificar status da URL '" + url + "': " + e);
            return false;
        }
    }

    private static int getResponseCode(String urlToCall) throws IOException {
        URL url = new URL(urlToCall);
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();

        httpsURLConnection.setRequestMethod("HEAD");
        httpsURLConnection.connect();
        return httpsURLConnection.getResponseCode();
    }
}
