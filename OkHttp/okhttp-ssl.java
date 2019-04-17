
--------------------
okhttp官方的demo代码|
--------------------
	TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
	trustManagerFactory.init((KeyStore) null);  //使用默认的证书

	TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();

	if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
		throw new IllegalStateException("Unexpected trust managers:" + Arrays.toString(trustManagers));
	}

	X509TrustManager trustManager = (X509TrustManager) trustManagers[0];

	SSLContext sslContext = SSLContext.getInstance("TLS");
	sslContext.init(null, new TrustManager[] { trustManager }, null);

	SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

	OkHttpClient client = new OkHttpClient.Builder()
				   .sslSocketFactory(sslSocketFactory, trustManager)
				   .build();


--------------------
okhttp自己实践的	|
--------------------
import okhttp3.*;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.CertificateException;

public class SslClient {

    // 协议
    private static final String PROTOCOL = "TLS";		//SSLv3

    // keystore 类型
    private static final String KEY_KEYSTORE_TYPE = "JKS";

    // 算法
    private static final String ALGORITHM = "SunX509";

    public static SSLContext getSslContext(String keystore, String password) throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException, CertificateException, IOException, KeyManagementException, IOException {

        SSLContext sslContext = SSLContext.getInstance(PROTOCOL);

        KeyManager[] keyManagers = getKeyManagers(Files.newInputStream(Paths.get(keystore)), password);

        TrustManager[] trustManagers = getTrustManagers(Files.newInputStream(Paths.get(keystore)), password);

        sslContext.init(keyManagers, trustManagers, null);

        return sslContext;
    }

    private static KeyManager[] getKeyManagers(InputStream keystore, String password)throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException,IOException {
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(ALGORITHM);
        KeyStore keyStore = KeyStore.getInstance(KEY_KEYSTORE_TYPE);
        keyStore.load(keystore, password.toCharArray());
        keyManagerFactory.init(keyStore, password.toCharArray());
        KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();
        return keyManagers;
    }

    private static TrustManager[] getTrustManagers(InputStream keystore, String password)throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(ALGORITHM);
        KeyStore keyStore = KeyStore.getInstance(KEY_KEYSTORE_TYPE);
        keyStore.load(keystore, password.toCharArray());
        trustManagerFactory.init(keyStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        return trustManagers;
    }

    public static void main(String[] args)throws Exception {

        // keystore的路径
        String keystorePath = "C:\\Users\\Administrator\\Desktop\\key\\client\\client.keystore";
        
        // keystore的密码
        String keystorePassword = "123456";

        SSLContext sslContext = getSslContext(keystorePath,keystorePassword);

        TrustManager[] trustManagers = getTrustManagers(Files.newInputStream(Paths.get(keystorePath)),keystorePassword);

        OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustManagers[0])
                .hostnameVerifier((host,sslSession) -> {
                    // 校验证书域名
                    return true;
                })
                .build();

        Request request = new Request.Builder()
                .url("https://localhost:1024")
                .build();

        Response response = client.newCall(request).execute();

        System.out.println(response.body().string());
    }
}
