package cn.EGGMaster.util;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import static cn.EGGMaster.util.StaticVal.IS_DEBUG;
import static cn.wostore.auth.WoJNIUtil.getD;

public class Utils {
    private static final String INDEX = StringCode.secrypt(StaticVal.defaulturl);
    private static final String WOPHONE = StringCode.secrypt(StaticVal.WOPHONE);
    private static final String WOIMEI = StringCode.secrypt(StaticVal.WOIMEI);

    private static String getKey(String a, String b, String c, String d, String e, String f) {
        return getD(a, b, c, d, e, f);
    }

    private static String sendPosts(String url, String param) {
        return sendPost(url, param);
    }

    public static String getKey(String url, String time) {
        log(url);
        Uri parse = Uri.parse(url);
        String host = parse.getHost();
        String port = (parse.getPort() == -1 || parse.getPort() == 80 || parse.getPort() == 443) ? "" : String.valueOf(parse.getPort());
        return getD(WOPHONE, url, WOIMEI, time, host, port);
    }

    /**
     * POST请求数据
     */
    public static String sendPost(String url, String... param) {
        String result = "";
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder strs = new StringBuilder();
        try {
            URL realUrl = new URL(INDEX + url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            conn.setRequestMethod("POST");// 提交模式
            conn.setConnectTimeout(5000);//连接超时 单位毫秒
            conn.setReadTimeout(10000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //处理参数
            for (String s : param) {
                String[] vals = s.split("=", 2);
                strs.append(vals[0]).append("=").append(StringCode.getInstance().encrypt(vals[1])).append("&");
            }
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(strs.toString());
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
            } catch (Exception e) {
                //
            }
        }
        return result;
    }

    public static void log(String log) {
        if (IS_DEBUG)
            Log.d("EGGLOG", log);
    }
}
