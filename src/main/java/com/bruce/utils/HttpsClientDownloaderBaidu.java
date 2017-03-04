package com.bruce.utils;

import com.google.common.collect.Sets;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.HttpClientDownloader;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * 百度专用下载器
 *
 * @author
 * @create 2017-02-15 16:32
 **/

public class HttpsClientDownloaderBaidu extends HttpClientDownloader {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getDefault().getInstance("SSLv3");
        //实现一个1X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        sc.init(null, new TrustManager[]{trustManager}, null);
        return sc;
    }

    private CloseableHttpClient getHttpClient(final Site site) {
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.custom().setSslcontext(createIgnoreVerifySSL())
                    .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.87 Safari/537.36")
                    .setRetryHandler(new HttpRequestRetryHandler() {
                        @Override
                        public boolean retryRequest(IOException e, int i, HttpContext httpContext) {
                            return false;
                        }
                    })
                    .build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return httpClient;
    }

    public Page download(Request request, Task task) {
        Site site = null;
        if (task != null) {
            site = task.getSite();
        }
        String charset = null;
        Map headers = null;
        Object acceptStatCode;
        if (site != null) {
            acceptStatCode = site.getAcceptStatCode();
            charset = site.getCharset();
            headers = site.getHeaders();
        } else {
            acceptStatCode = Sets.newHashSet(new Integer[]{
                    Integer.valueOf(200)
            });
        }

        CloseableHttpResponse httpResponse = null;
        int statusCode = 0;
        Page page;
        try {
            HttpUriRequest e = this.getHttpUriRequest(request, site, headers,new HttpHost("localshot"));
            httpResponse = this.getHttpClient(site).execute(e);
            statusCode = httpResponse.getStatusLine().getStatusCode();
            if (!request.getUrl().contains("www.baidu.com/s?wd") |
                    !request.getUrl().contains("www.baidu.com/link")) {
                logger.info("url={},code={}", request.getUrl(), statusCode);
            }
            request.putExtra("statusCode", Integer.valueOf(statusCode));
            page = this.handleResponse(request, charset, httpResponse, task);
            this.onSuccess(request);
        } catch (IOException var23) {

            if (site.getCycleRetryTimes() > 0) {
                page = this.addToCycleRetry(request, site);
                if (page.getTargetRequests().get(0).getExtra("_cycle_tried_times") != null) {
                    logger.error("download page " + request.getUrl() + " code = " + statusCode + " retrytime=" +
                            page.getTargetRequests().get(0).getExtra("_cycle_tried_times") + " error", var23);
                } else {
                    logger.error("download page " + request.getUrl() + " code = " + statusCode + " error", var23);
                }
                return page;
            }
            this.onError(request);
            page = null;
            return page;
        } finally {
            request.putExtra("statusCode", Integer.valueOf(statusCode));

            try {
                if (httpResponse != null) {
                    EntityUtils.consume(httpResponse.getEntity());
                }
            } catch (IOException var22) {
            }

        }
        return page;
    }
}
