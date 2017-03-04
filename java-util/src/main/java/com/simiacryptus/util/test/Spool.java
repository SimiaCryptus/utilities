package com.simiacryptus.util.test;

import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;


public class Spool extends InputStream {

  public static InputStream load(String url, String file) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
    if (new File(file).exists()) {
      return new FileInputStream(file);
    } else {
      TrustManager[] trustManagers = new TrustManager[] {
              new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                  return new X509Certificate[0];
                }
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
              }
      };
      SSLContext ctx = SSLContext.getInstance("TLS");
      ctx.init(null, trustManagers, null);
      SSLSocketFactory sslFactory = ctx.getSocketFactory();
      URLConnection urlConnection = new URL(url).openConnection();
      if(urlConnection instanceof javax.net.ssl.HttpsURLConnection) {
        HttpsURLConnection conn = (HttpsURLConnection) urlConnection;
        conn.setSSLSocketFactory(sslFactory);
        conn.setRequestMethod("GET");
      }
      InputStream inputStream = urlConnection.getInputStream();
      FileOutputStream cache = new FileOutputStream(file);
      return new Spool(inputStream, cache);
    }
  }


  private final InputStream inputStream;
  private final FileOutputStream cache;

  public Spool(InputStream inputStream, FileOutputStream cache) {
    this.inputStream = inputStream;
    this.cache = cache;
  }

  @Override
  public int read() throws IOException {
    int read = inputStream.read();
    cache.write(read);
    return read;
  }

  @Override
  public int read(byte[] b) throws IOException {
    int read = inputStream.read(b);
    if (read > 0) cache.write(b);
    return read;
  }

  @Override
  public int read(byte[] b, int off, int len) throws IOException {
    int read = inputStream.read(b, off, len);
    if (read > 0) cache.write(b, off, read);
    return read;
  }

  @Override
  public int available() throws IOException {
    return inputStream.available();
  }

  @Override
  public void close() throws IOException {
    inputStream.close();
    cache.close();
  }
}
