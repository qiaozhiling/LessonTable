package com.jacky.lession.url;

import com.jacky.lession.err.LogInFailureException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLRequest {
    private static final Pattern cookieAnalysePattern = Pattern.compile("; ");
    private static final Pattern LessonTableUrlPattern = Pattern
            .compile("^[\\s\\S]+?<frame src=\"(right\\.aspx\\?id=\\d+)\".+/>[\\s\\S]+$");

    private static final String loginUrl = "http://59.77.226.32/logincheck.asp";
    private static final HashMap<String, String> headers = new HashMap<>();
    private static final HashMap<String, String> cookies = new HashMap<>();

    static {
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 "
                + "(KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36 Edg/87.0.664.66");
        headers.put("Referer", "http://jwch.fzu.edu.cn/");
        headers.put("Accept", "*/*");
        headers.put("Connection", "keep-alive");
        headers.put("Content-Type", "application/x-www-form-urlencoded");
    }

    private String lessonTableUrl;

    private boolean hasDone = false;

    private HttpURLConnection connection;

    public URLRequest() {

    }

    public void sendLogin(String UID, String passWord) throws LogInFailureException {
        HttpURLConnection connection = null;
        try {
            connection = generateConnection(RequestMethod.POST, loginUrl, headers, cookies);

            sendPostData(connection, generateLoginData(UID, passWord));

            // Map<String, List<String>> t = connection.getHeaderFields();
            // System.out.println(t.toString());
            // String html = getRespondHtml(connection);
            // System.out.println(html);
            if (needRedirect(connection)) {
                updataCookies(connection);
                String url = connection.getHeaderField("Location");
                connection.disconnect();
                connection = doRedirect(url, headers, cookies);
            }
            String html = getRespondHtml(connection);

            URL tempurl = connection.getURL();

            lessonTableUrl = String.format("%s://%s/%s", tempurl.getProtocol(), tempurl.getHost(),
                    analyseLessionTableUrl(html));

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (connection != null)
                connection.disconnect();

        }

    }

    private String analyseLessionTableUrl(String html) {
        Matcher matcher = LessonTableUrlPattern.matcher(html);
        if (matcher.matches()) {
            return matcher.group(1);
        } else
            return "";
    }

    private HttpURLConnection doRedirect(String Url, Map<String, String> headers, Map<String, String> cookies) {
        try {
            HttpURLConnection connection = generateConnection(RequestMethod.GET, Url, headers, cookies);
            connection.connect();

            if (needRedirect(connection)) {
                updataCookies(connection);
                String url = connection.getHeaderField("Location");
                connection.disconnect();

                connection = doRedirect(url, headers, cookies);
            }

            return connection;

        } catch (Exception exception) {
            exception.printStackTrace();
            if (connection != null)
                connection.disconnect();
            return connection;
        }

    }

    private boolean needRedirect(HttpURLConnection connection) throws IOException {
        int code = connection.getResponseCode();
        return code == HttpURLConnection.HTTP_MOVED_TEMP || code == HttpURLConnection.HTTP_MOVED_PERM
                || code == HttpURLConnection.HTTP_SEE_OTHER;
    }

    private void updataCookies(HttpURLConnection connection) {
        String appendCookies = connection.getHeaderField("Set-Cookie");
        String[] result = cookieAnalysePattern.split(appendCookies);
        for (String c : result) {
            String[] temp = c.split("=");
            if (temp.length == 2)
                cookies.put(temp[0], temp[1]);
        }

    }

    private void sendPostData(HttpURLConnection connection, String data) throws IOException {
        DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
        dos.writeBytes(data);
        dos.flush();
        dos.close();
    }

    private String getRespondHtml(HttpURLConnection connection) throws IOException {
        StringBuilder builder = new StringBuilder();
        int code = connection.getResponseCode();
        if (code == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                builder.append(line).append("\n");
            }
            in.close();
            // connection.
        }
        return builder.toString();
    }

    private String generateLoginData(String UID, String passWord) throws UnsupportedEncodingException {
        return String.format("muser=%s&passwd=%s", URLEncoder.encode(UID, "UTF-8"),
                URLEncoder.encode(passWord, "UTF-8"));

    }

    private String generateCookies(Map<String, String> cookie) {
        StringBuilder builder = new StringBuilder();
        boolean inFirst = true;
        for (String key : cookie.keySet()) {
            if (!inFirst) {
                builder.append("; ");
            }
            builder.append(String.format("%s=%s", key, cookie.get(key)));
            inFirst = false;
        }
        return builder.toString();
    }

    private HttpURLConnection generateConnection(
            RequestMethod method, String Url,
            Map<String, String> headers,
            Map<String, String> cookie) throws IOException {
        // 创建Url实例
        URL url = new URL(Url);
        // 创建
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        for (String key : headers.keySet()) {
            connection.addRequestProperty(key, headers.get(key));
        }
        connection.addRequestProperty("Cookie", generateCookies(cookie));

        connection.setRequestMethod(method.getName());
        connection.setConnectTimeout(15000);
        connection.setReadTimeout(15000);

        if (method == RequestMethod.POST) {
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
        }

        connection.setInstanceFollowRedirects(false);

        return connection;
    }

    public boolean isHasDone() {
        return hasDone;
    }
}
