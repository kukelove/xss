package com.xp.brushms.util;


import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hzm on 2015/8/15.
 */
public class HttpUtils {
    public static HttpClient createHttp11Client() {
        return createHttpClient(HttpVersion.HTTP_1_1);
    }
    public static HttpClient createHttp10Client() {
        return createHttpClient(HttpVersion.HTTP_1_0);
    }
    private static HttpClient createHttpClient(HttpVersion version) {
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore
                    .getDefaultType());
            trustStore.load(null, null);

            SSLSocketFactory sf = new SSLSocketFactoryEx(trustStore);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            HttpParams params = new BasicHttpParams();
            HttpProtocolParams.setVersion(params, version);
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            registry.register(new Scheme("https", sf, 443));
            ConnManagerParams.setTimeout(params, 5000);
            HttpConnectionParams.setConnectionTimeout(params, 20000);
            HttpConnectionParams.setSoTimeout(params, 20000);
            ClientConnectionManager ccm = new ThreadSafeClientConnManager(
                    params, registry);

            return new DefaultHttpClient(ccm, params);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }

    public static class SSLSocketFactoryEx extends SSLSocketFactory {

        SSLContext sslContext = SSLContext.getInstance("TLS");

        public SSLSocketFactoryEx(KeyStore truststore)
                throws NoSuchAlgorithmException, KeyManagementException,
                KeyStoreException, UnrecoverableKeyException {
            super(truststore);

            TrustManager tm = new X509TrustManager() {

                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }


                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType)
                        throws java.security.cert.CertificateException {

                }


                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType)
                        throws java.security.cert.CertificateException {

                }
            };

            sslContext.init(null, new TrustManager[] { tm }, null);
        }

        @Override
        public Socket createSocket(Socket socket, String host, int port,
                                   boolean autoClose) throws IOException, UnknownHostException {
            return sslContext.getSocketFactory().createSocket(socket, host,
                    port, autoClose);
        }

        @Override
        public Socket createSocket() throws IOException {
            return sslContext.getSocketFactory().createSocket();
        }
    }


    public static boolean getAsFile(String urlString, String filepath) {
        try {
            HttpClient httpclient = createHttp11Client();
            HttpGet httpPost = new HttpGet(urlString);
            HttpResponse resp = httpclient.execute(httpPost);
            InputStream ins = null;
            if (resp == null || resp.getStatusLine().getStatusCode() != 200) {
                return false;
            }
            ins = resp.getEntity().getContent();
            FileOutputStream bout = new FileOutputStream(filepath);
            int BUFFER = 4096;
            int count;
            byte data[] = new byte[BUFFER];
            while ((count = ins.read(data, 0, BUFFER)) != -1) {
                bout.write(data, 0, count);
            }
            if (ins != null) {
                ins.close();
            }
            bout.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static String postJsonAsString(String urlString, String content) {
        StringBuffer result = new StringBuffer();
        int retry = 2;
        while (retry > 0) {
            int ret = postAsString(urlString, content, "application/json; charset=utf-8;", null, result);
            if (ret == 200) return result.toString();
            else retry--;
        }
        return null;
    }
    private static int postAsString(String urlString, String content, String contentType, String host, StringBuffer sb) {
        int status = -1;
        try {
            sb.setLength(0);
            HttpClient httpclient = createHttp11Client();
            HttpPost httpPost = new HttpPost(urlString);

            if (contentType != null)
                httpPost.addHeader("Content-Type", contentType);
            if (host != null) {
                httpPost.setHeader("Host", host);
            }
            httpPost.setEntity(new StringEntity(content, "utf-8"));
            HttpResponse resp = httpclient.execute(httpPost);
            status = resp.getStatusLine().getStatusCode();
            InputStream ins = null;
            HttpEntity entity = resp.getEntity();
            ins = entity.getContent();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            int BUFFER = 4096;
            int count;
            byte data[] = new byte[BUFFER];
            while ((count = ins.read(data, 0, BUFFER)) != -1) {
                bout.write(data, 0, count);
            }
            if (ins != null) {
                ins.close();
            }
            sb.append(new String(bout.toByteArray(), "utf-8"));
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return status;
    }


    public static byte[] getAsBytes(String url) {
        try {
            HttpClient httpclient = createHttp10Client();

            HttpGet httpPost = new HttpGet(url);
            HttpResponse resp = httpclient.execute(httpPost);
            InputStream ins = null;
            if (resp == null || resp.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            ins = resp.getEntity().getContent();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            int BUFFER = 4096;
            int count;
            byte data[] = new byte[BUFFER];
            while ((count = ins.read(data, 0, BUFFER)) != -1) {
                bout.write(data, 0, count);
            }
            if (ins != null) {
                ins.close();
            }
            return bout.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getAsString(String urlString) {
        try {
            HttpClient httpclient = createHttp11Client();
            HttpGet httpPost = new HttpGet(urlString);
            HttpResponse resp = httpclient.execute(httpPost);
            InputStream ins = null;
            if (resp == null || resp.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            Header contentType = resp.getFirstHeader("Content-Type");
            String charset = null;
            if (contentType != null) {
                String v = contentType.getValue();
                int i = v.indexOf("charset=");
                if (i > 0) {
                    charset = v.substring(i + 8, v.length());
                }
            }
            ins = resp.getEntity().getContent();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            int BUFFER = 4096;
            int count;
            byte data[] = new byte[BUFFER];
            while ((count = ins.read(data, 0, BUFFER)) != -1) {
                bout.write(data, 0, count);
            }
            if (ins != null) {
                ins.close();
            }
            String content = null;
            if (charset != null) {
                try {
                    content = new String(bout.toByteArray(), charset);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            if (content == null) {
                content = new String(bout.toByteArray(), "utf-8");
            }
            return content;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String postAndGetString(String urlString,
                                          Map<String, String> params) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(urlString);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for(Map.Entry<String, String> kv : params.entrySet()) {
                nvps.add(new BasicNameValuePair(kv.getKey(), kv.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response2 = httpclient.execute(httpPost);
            try {
                HttpEntity entity = response2.getEntity();
                InputStream ins = entity.getContent();
                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                int BUFFER = 4096;
                int count;
                byte data[] = new byte[BUFFER];
                while ((count = ins.read(data, 0, BUFFER)) != -1) {
                    bout.write(data, 0, count);
                }
                return new String(bout.toByteArray(),"utf-8");
            } finally {
                response2.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
