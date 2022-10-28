
package com.github.catvod.spider;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.github.catvod.crawler.Spider;
import com.github.catvod.crawler.SpiderDebug;
import com.github.catvod.spider.merge.AM;
import com.github.catvod.spider.merge.I;
import com.github.catvod.spider.merge.Qy;
import com.github.catvod.spider.merge.U;
import com.github.catvod.spider.merge.VR;
import com.github.catvod.spider.merge.g;
import com.github.catvod.spider.merge.q2;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Bdys01 extends Spider {
    private JSONObject B;
    private String rF = "";
    private String Mo = "";
    private String g = "";

    protected void B() {
        this.rF = "";
        HashMap hashMap = new HashMap();
        I.A("https://www.bdys01.com/zzzzz", s("https://www.bdys01.com/zzzzz", ""), hashMap);
        for (Map.Entry entry : hashMap.entrySet()) {
            if (((String) entry.getKey()).equals("set-cookie")) {
                this.rF = TextUtils.join(";", (Iterable) entry.getValue());
                return;
            }
        }
    }

    protected String Mo(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public String categoryContent(String str, String str2, boolean z, HashMap<String, String> hashMap) {
        String str3;
        String k;
        JSONArray jSONArray;
        if (hashMap != null) {
            try {
                if (hashMap.size() > 0) {
                    str3 = str.equals("a") ? "https://www.bdys01.com/s/{s}/" + str2 + "?&area={area}&year={year}&order={order}" : "https://www.bdys01.com/s/{s}/" + str2 + "?&type=" + str + "&area={area}&year={year}&order={order}";
                    for (String str4 : hashMap.keySet()) {
                        String str5 = hashMap.get(str4);
                        if (str5.length() > 0) {
                            str3 = str3.replace("{" + str4 + "}", URLEncoder.encode(str5));
                        }
                    }
                    for (int i = 0; i < 4; i++) {
                        if (str3.contains("{s}")) {
                            str3 = str3.replace("{s}", "all");
                        } else if (str3.contains("{area}")) {
                            str3 = str3.replace("&area={area}", "");
                        } else if (str3.contains("{year}")) {
                            str3 = str3.replace("&year={year}", "");
                        } else if (str3.contains("{order}")) {
                            str3 = str3.replace("{order}", "0");
                        }
                    }
                    this.Mo = "https://www.bdys01.com/";
                    k = I.k(str3, s(str3, "https://www.bdys01.com/"));
                    this.Mo = str3;
                    VR rF = q2.rF(k);
                    JSONObject jSONObject = new JSONObject();
                    jSONArray = new JSONArray();
                    if (!k.contains("没有找到您想要的结果哦")) {
                        AM TX = rF.TX("div.col-lg-8");
                        for (int i2 = 0; i2 < TX.size(); i2++) {
                            Qy qy = (Qy) TX.get(i2);
                            String YH = qy.m9("h3.mb-0").YH();
                            String g = qy.m9("img.w-100").g("src");
                            String YH2 = qy.m9("p.mb-0").YH();
                            String g2 = qy.m9("a.d-block").g("href");
                            if (g2.contains("JSESSIONID")) {
                                g2 = g2.substring(0, g2.indexOf(";"));
                            }
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("vod_id", g2);
                            jSONObject2.put("vod_name", YH);
                            jSONObject2.put("vod_pic", g);
                            jSONObject2.put("vod_remarks", YH2);
                            jSONArray.put(jSONObject2);
                        }
                    }
                    int parseInt = Integer.parseInt(str2);
                    jSONObject.put("page", parseInt);
                    if (jSONArray.length() == 24) {
                        parseInt++;
                    }
                    jSONObject.put("pagecount", parseInt);
                    jSONObject.put("limit", 24);
                    jSONObject.put("total", Integer.MAX_VALUE);
                    jSONObject.put("list", jSONArray);
                    return jSONObject.toString();
                }
            } catch (Exception e) {
                SpiderDebug.log(e);
                return "";
            }
        }
        str3 = str.equals("a") ? "https://www.bdys01.com/s/all/" + str2 + "?&order=0" : "https://www.bdys01.com/s/all/" + str2 + "?&type=" + str + "&order=0";
        this.Mo = "https://www.bdys01.com/";
        k = I.k(str3, s(str3, "https://www.bdys01.com/"));
        this.Mo = str3;
        VR rF2 = q2.rF(k);
        JSONObject jSONObject3 = new JSONObject();
        jSONArray = new JSONArray();
        if (!k.contains("没有找到您想要的结果哦")) {
        }
        int parseInt2 = Integer.parseInt(str2);
        jSONObject3.put("page", parseInt2);
        if (jSONArray.length() == 24) {
        }
        jSONObject3.put("pagecount", parseInt2);
        jSONObject3.put("limit", 24);
        jSONObject3.put("total", Integer.MAX_VALUE);
        jSONObject3.put("list", jSONArray);
        return jSONObject3.toString();
    }

    public String detailContent(List<String> list) {
        Exception e;
        Qy qy;
        String str = "类型：";
        String str2 = "";
        try {
            String str3 = "https://www.bdys01.com" + list.get(0);
            VR rF = q2.rF(I.k(str3, s(str3, this.Mo)));
            this.Mo = str3;
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            String g = rF.m9("div.col-md-auto img").g("src");
            String YH = rF.m9("h1.d-none.d-md-block").YH();
            String ZN = rF.TX("div.card.collapse > div.card-body").ZN();
            String str4 = rF.TX("span.badge.bg-purple-lt").ZN().replace("更新时间：", str2).split("-")[0];
            ArrayList TX = ((Qy) rF.TX("div.col.mb-2").get(0)).TX("p");
            String str5 = str2;
            String str6 = str5;
            String str7 = str6;
            String str8 = str7;
            String str9 = str8;
            int i = 0;
            while (i < TX.size()) {
                Qy qy2 = (Qy) TX.get(i);
                String YH2 = qy2.m9("strong").YH();
                str2 = str2;
                if (YH2.equals(str)) {
                    try {
                        ArrayList arrayList = new ArrayList();
                        AM TX2 = qy2.TX("a");
                        for (int i2 = 0; i2 < TX2.size(); i2++) {
                            arrayList.add(((Qy) TX2.get(i2)).YH());
                        }
                        str5 = str + TextUtils.join(",", arrayList);
                        str = str;
                    } catch (Exception e2) {
                        e = e2;
                        SpiderDebug.log(e);
                        return str2;
                    }
                } else {
                    str = str;
                    if (YH2.contains("地区")) {
                        str6 = qy2.YH().substring(qy2.YH().indexOf("[") + 1, qy2.YH().indexOf("]"));
                    } else if (YH2.contains("豆瓣")) {
                        str7 = "豆瓣:" + qy2.YH();
                    } else if (YH2.contains("导演：")) {
                        str9 = qy2.m9("a").YH();
                    } else if (YH2.contains("主演：")) {
                        ArrayList arrayList2 = new ArrayList();
                        AM TX3 = qy2.TX("a");
                        for (int i3 = 0; i3 < TX3.size(); i3++) {
                            arrayList2.add(((Qy) TX3.get(i3)).YH());
                        }
                        str8 = TextUtils.join(",", arrayList2);
                    }
                }
                i++;
                TX = TX;
                str2 = str2;
            }
            str2 = str2;
            jSONObject2.put("vod_id", list.get(0));
            jSONObject2.put("vod_name", YH);
            jSONObject2.put("vod_pic", g);
            jSONObject2.put("type_name", str5);
            jSONObject2.put("vod_year", str4);
            jSONObject2.put("vod_area", str6);
            jSONObject2.put("vod_remarks", str7);
            jSONObject2.put("vod_actor", str8);
            jSONObject2.put("vod_director", str9);
            jSONObject2.put("vod_content", ZN);
            TreeMap treeMap = new TreeMap();
            AM TX4 = rF.TX("a.btn.btn-square");
            ArrayList arrayList3 = new ArrayList();
            for (int i4 = 0; i4 < TX4.size(); i4++) {
                String g2 = ((Qy) TX4.get(i4)).g("href");
                if (g2.contains("JSESSIONID")) {
                    g2 = g2.substring(0, g2.indexOf(";"));
                }
                arrayList3.add(qy.YH() + "$" + g2);
            }
            treeMap.put("播放列表", arrayList3.size() > 0 ? TextUtils.join("#", arrayList3) : str2);
            if (treeMap.size() > 0) {
                String join = TextUtils.join("$$$", treeMap.keySet());
                String join2 = TextUtils.join("$$$", treeMap.values());
                jSONObject2.put("vod_play_from", join);
                jSONObject2.put("vod_play_url", join2);
            }
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject2);
            jSONObject.put("list", jSONArray);
            return jSONObject.toString();
        } catch (Exception e3) {
            e = e3;
        }
    }

    protected String g(String str, String str2) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
            cipher.init(1, new SecretKeySpec(str2.getBytes(), "AES"));
            return Mo(cipher.doFinal(str.getBytes())).toUpperCase();
        } catch (Exception e) {
            SpiderDebug.log(e);
            return null;
        }
    }

    public String homeContent(boolean z) {
        try {
            B();
            VR rF = q2.rF(I.k("https://www.bdys01.com", s("https://www.bdys01.com", this.Mo)));
            this.Mo = "https://www.bdys01.com/";
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject("{\"全部\": \"a\",\"电影\": \"0\",\"电视剧\": \"1\"}");
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                JSONObject jSONObject3 = new JSONObject();
                String next = keys.next();
                jSONObject3.put("type_name", next);
                jSONObject3.put("type_id", jSONObject2.getString(next));
                jSONArray.put(jSONObject3);
            }
            jSONObject.put("class", jSONArray);
            if (z) {
                jSONObject.put("filters", this.B);
            }
            try {
                AM TX = rF.TX("div.row.row-cards");
                AM TX2 = ((Qy) TX.get(0)).TX("div.col-4.rows-md-7");
                JSONArray jSONArray2 = new JSONArray();
                for (int i = 0; i < TX2.size(); i++) {
                    Qy qy = (Qy) TX2.get(i);
                    String YH = qy.m9("h3.card-title").YH();
                    String g = qy.m9("img.w-100").g("data-src");
                    String YH2 = qy.m9("p.text-muted").YH();
                    String g2 = qy.m9("a.d-block.cover").g("href");
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("vod_id", g2);
                    jSONObject4.put("vod_name", YH);
                    jSONObject4.put("vod_pic", g);
                    jSONObject4.put("vod_remarks", YH2);
                    jSONArray2.put(jSONObject4);
                }
                jSONObject.put("list", jSONArray2);
            } catch (Exception e) {
                SpiderDebug.log(e);
            }
            return jSONObject.toString();
        } catch (Exception e2) {
            SpiderDebug.log(e2);
            return "";
        }
    }

    public void init(Context context) {
        Bdys01.super.init(context);
        try {
            this.B = new JSONObject("{\"0\":[{\"key\":\"s\",\"name\":\"类型\",\"value\":[{\"n\":\"全部\",\"v\":\"all\"},{\"n\":\"动作\",\"v\":\"dongzuo\"},{\"n\":\"爱情\",\"v\":\"aiqing\"},{\"n\":\"喜剧\",\"v\":\"xiju\"},{\"n\":\"科幻\",\"v\":\"kehuan\"},{\"n\":\"恐怖\",\"v\":\"kongbu\"},{\"n\":\"战争\",\"v\":\"zhanzheng\"},{\"n\":\"武侠\",\"v\":\"wuxia\"},{\"n\":\"魔幻\",\"v\":\"mohuan\"},{\"n\":\"剧情\",\"v\":\"juqing\"},{\"n\":\"动画\",\"v\":\"donghua\"},{\"n\":\"惊悚\",\"v\":\"jingsong\"},{\"n\":\"3D\",\"v\":\"3D\"},{\"n\":\"灾难\",\"v\":\"zainan\"},{\"n\":\"悬疑\",\"v\":\"xuanyi\"},{\"n\":\"警匪\",\"v\":\"jingfei\"},{\"n\":\"文艺\",\"v\":\"wenyi\"},{\"n\":\"青春\",\"v\":\"qingchun\"},{\"n\":\"冒险\",\"v\":\"maoxian\"},{\"n\":\"犯罪\",\"v\":\"fanzui\"},{\"n\":\"纪录\",\"v\":\"jilu\"},{\"n\":\"古装\",\"v\":\"guzhuang\"},{\"n\":\"奇幻\",\"v\":\"qihuan\"},{\"n\":\"国语\",\"v\":\"guoyu\"},{\"n\":\"综艺\",\"v\":\"zongyi\"},{\"n\":\"历史\",\"v\":\"lishi\"},{\"n\":\"运动\",\"v\":\"yundong\"},{\"n\":\"原创压制\",\"v\":\"yuanchuang\"},{\"n\":\"美剧\",\"v\":\"meiju\"},{\"n\":\"韩剧\",\"v\":\"hanju\"},{\"n\":\"国产电视剧\",\"v\":\"guoju\"},{\"n\":\"日剧\",\"v\":\"riju\"},{\"n\":\"英剧\",\"v\":\"yingju\"},{\"n\":\"德剧\",\"v\":\"deju\"},{\"n\":\"俄剧\",\"v\":\"eju\"},{\"n\":\"巴剧\",\"v\":\"baju\"},{\"n\":\"加剧\",\"v\":\"jiaju\"},{\"n\":\"西剧\",\"v\":\"anish\"},{\"n\":\"意大利剧\",\"v\":\"yidaliju\"},{\"n\":\"泰剧\",\"v\":\"taiju\"},{\"n\":\"港台剧\",\"v\":\"gangtaiju\"},{\"n\":\"法剧\",\"v\":\"faju\"},{\"n\":\"澳剧\",\"v\":\"aoju\"}]},{\"key\":\"area\",\"name\":\"地区\",\"value\":[{\"n\":\"不限\",\"v\":\"\"},{\"n\":\"中国大陆\",\"v\":\"中国大陆\"},{\"n\":\"中国香港\",\"v\":\"中国香港\"},{\"n\":\"中国台湾\",\"v\":\"中国台湾\"},{\"n\":\"美国\",\"v\":\"美国\"},{\"n\":\"英国\",\"v\":\"英国\"},{\"n\":\"日本\",\"v\":\"日本\"},{\"n\":\"韩国\",\"v\":\"韩国\"},{\"n\":\"法国\",\"v\":\"法国\"},{\"n\":\"印度\",\"v\":\"印度\"},{\"n\":\"德国\",\"v\":\"德国\"},{\"n\":\"西班牙\",\"v\":\"西班牙\"},{\"n\":\"意大利\",\"v\":\"意大利\"},{\"n\":\"澳大利亚\",\"v\":\"澳大利亚\"},{\"n\":\"比利时\",\"v\":\"比利时\"},{\"n\":\"瑞典\",\"v\":\"瑞典\"},{\"n\":\"荷兰\",\"v\":\"荷兰\"},{\"n\":\"丹麦\",\"v\":\"丹麦\"},{\"n\":\"加拿大\",\"v\":\"加拿大\"},{\"n\":\"俄罗斯\",\"v\":\"俄罗斯\"}]},{\"key\":\"year\",\"name\":\"年份\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"2022\",\"v\":\"2022\"},{\"n\":\"2021\",\"v\":\"2021\"},{\"n\":\"2020\",\"v\":\"2020\"},{\"n\":\"2019\",\"v\":\"2019\"},{\"n\":\"2018\",\"v\":\"2018\"},{\"n\":\"2017\",\"v\":\"2017\"},{\"n\":\"2016\",\"v\":\"2016\"},{\"n\":\"2015\",\"v\":\"2015\"},{\"n\":\"2014\",\"v\":\"2014\"},{\"n\":\"2013\",\"v\":\"2013\"},{\"n\":\"2012\",\"v\":\"2012\"},{\"n\":\"2011\",\"v\":\"2011\"},{\"n\":\"2010\",\"v\":\"2010\"},{\"n\":\"2009\",\"v\":\"2009\"},{\"n\":\"2008\",\"v\":\"2008\"},{\"n\":\"2007\",\"v\":\"2007\"},{\"n\":\"2006\",\"v\":\"2006\"},{\"n\":\"2005\",\"v\":\"2005\"},{\"n\":\"2004\",\"v\":\"2004\"},{\"n\":\"2003\",\"v\":\"2003\"},{\"n\":\"2002\",\"v\":\"2002\"},{\"n\":\"2001\",\"v\":\"2001\"},{\"n\":\"2000\",\"v\":\"2000\"}]},{\"key\":\"order\",\"name\":\"排序\",\"value\":[{\"n\":\"更新时间\",\"v\":\"0\"},{\"n\":\"豆瓣评分\",\"v\":\"1\"}]}],\"1\":[{\"key\":\"s\",\"name\":\"类型\",\"value\":[{\"n\":\"全部\",\"v\":\"all\"},{\"n\":\"动作\",\"v\":\"dongzuo\"},{\"n\":\"爱情\",\"v\":\"aiqing\"},{\"n\":\"喜剧\",\"v\":\"xiju\"},{\"n\":\"科幻\",\"v\":\"kehuan\"},{\"n\":\"恐怖\",\"v\":\"kongbu\"},{\"n\":\"战争\",\"v\":\"zhanzheng\"},{\"n\":\"武侠\",\"v\":\"wuxia\"},{\"n\":\"魔幻\",\"v\":\"mohuan\"},{\"n\":\"剧情\",\"v\":\"juqing\"},{\"n\":\"动画\",\"v\":\"donghua\"},{\"n\":\"惊悚\",\"v\":\"jingsong\"},{\"n\":\"3D\",\"v\":\"3D\"},{\"n\":\"灾难\",\"v\":\"zainan\"},{\"n\":\"悬疑\",\"v\":\"xuanyi\"},{\"n\":\"警匪\",\"v\":\"jingfei\"},{\"n\":\"文艺\",\"v\":\"wenyi\"},{\"n\":\"青春\",\"v\":\"qingchun\"},{\"n\":\"冒险\",\"v\":\"maoxian\"},{\"n\":\"犯罪\",\"v\":\"fanzui\"},{\"n\":\"纪录\",\"v\":\"jilu\"},{\"n\":\"古装\",\"v\":\"guzhuang\"},{\"n\":\"奇幻\",\"v\":\"qihuan\"},{\"n\":\"国语\",\"v\":\"guoyu\"},{\"n\":\"综艺\",\"v\":\"zongyi\"},{\"n\":\"历史\",\"v\":\"lishi\"},{\"n\":\"运动\",\"v\":\"yundong\"},{\"n\":\"原创压制\",\"v\":\"yuanchuang\"},{\"n\":\"美剧\",\"v\":\"meiju\"},{\"n\":\"韩剧\",\"v\":\"hanju\"},{\"n\":\"国产电视剧\",\"v\":\"guoju\"},{\"n\":\"日剧\",\"v\":\"riju\"},{\"n\":\"英剧\",\"v\":\"yingju\"},{\"n\":\"德剧\",\"v\":\"deju\"},{\"n\":\"俄剧\",\"v\":\"eju\"},{\"n\":\"巴剧\",\"v\":\"baju\"},{\"n\":\"加剧\",\"v\":\"jiaju\"},{\"n\":\"西剧\",\"v\":\"anish\"},{\"n\":\"意大利剧\",\"v\":\"yidaliju\"},{\"n\":\"泰剧\",\"v\":\"taiju\"},{\"n\":\"港台剧\",\"v\":\"gangtaiju\"},{\"n\":\"法剧\",\"v\":\"faju\"},{\"n\":\"澳剧\",\"v\":\"aoju\"}]},{\"key\":\"area\",\"name\":\"地区\",\"value\":[{\"n\":\"不限\",\"v\":\"\"},{\"n\":\"中国大陆\",\"v\":\"中国大陆\"},{\"n\":\"中国香港\",\"v\":\"中国香港\"},{\"n\":\"中国台湾\",\"v\":\"中国台湾\"},{\"n\":\"美国\",\"v\":\"美国\"},{\"n\":\"英国\",\"v\":\"英国\"},{\"n\":\"日本\",\"v\":\"日本\"},{\"n\":\"韩国\",\"v\":\"韩国\"},{\"n\":\"法国\",\"v\":\"法国\"},{\"n\":\"印度\",\"v\":\"印度\"},{\"n\":\"德国\",\"v\":\"德国\"},{\"n\":\"西班牙\",\"v\":\"西班牙\"},{\"n\":\"意大利\",\"v\":\"意大利\"},{\"n\":\"澳大利亚\",\"v\":\"澳大利亚\"},{\"n\":\"比利时\",\"v\":\"比利时\"},{\"n\":\"瑞典\",\"v\":\"瑞典\"},{\"n\":\"荷兰\",\"v\":\"荷兰\"},{\"n\":\"丹麦\",\"v\":\"丹麦\"},{\"n\":\"加拿大\",\"v\":\"加拿大\"},{\"n\":\"俄罗斯\",\"v\":\"俄罗斯\"}]},{\"key\":\"year\",\"name\":\"年份\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"2022\",\"v\":\"2022\"},{\"n\":\"2021\",\"v\":\"2021\"},{\"n\":\"2020\",\"v\":\"2020\"},{\"n\":\"2019\",\"v\":\"2019\"},{\"n\":\"2018\",\"v\":\"2018\"},{\"n\":\"2017\",\"v\":\"2017\"},{\"n\":\"2016\",\"v\":\"2016\"},{\"n\":\"2015\",\"v\":\"2015\"},{\"n\":\"2014\",\"v\":\"2014\"},{\"n\":\"2013\",\"v\":\"2013\"},{\"n\":\"2012\",\"v\":\"2012\"},{\"n\":\"2011\",\"v\":\"2011\"},{\"n\":\"2010\",\"v\":\"2010\"},{\"n\":\"2009\",\"v\":\"2009\"},{\"n\":\"2008\",\"v\":\"2008\"},{\"n\":\"2007\",\"v\":\"2007\"},{\"n\":\"2006\",\"v\":\"2006\"},{\"n\":\"2005\",\"v\":\"2005\"},{\"n\":\"2004\",\"v\":\"2004\"},{\"n\":\"2003\",\"v\":\"2003\"},{\"n\":\"2002\",\"v\":\"2002\"},{\"n\":\"2001\",\"v\":\"2001\"},{\"n\":\"2000\",\"v\":\"2000\"}]},{\"key\":\"order\",\"name\":\"排序\",\"value\":[{\"n\":\"更新时间\",\"v\":\"0\"},{\"n\":\"豆瓣评分\",\"v\":\"1\"}]}],\"a\":[{\"key\":\"s\",\"name\":\"类型\",\"value\":[{\"n\":\"全部\",\"v\":\"all\"},{\"n\":\"动作\",\"v\":\"dongzuo\"},{\"n\":\"爱情\",\"v\":\"aiqing\"},{\"n\":\"喜剧\",\"v\":\"xiju\"},{\"n\":\"科幻\",\"v\":\"kehuan\"},{\"n\":\"恐怖\",\"v\":\"kongbu\"},{\"n\":\"战争\",\"v\":\"zhanzheng\"},{\"n\":\"武侠\",\"v\":\"wuxia\"},{\"n\":\"魔幻\",\"v\":\"mohuan\"},{\"n\":\"剧情\",\"v\":\"juqing\"},{\"n\":\"动画\",\"v\":\"donghua\"},{\"n\":\"惊悚\",\"v\":\"jingsong\"},{\"n\":\"3D\",\"v\":\"3D\"},{\"n\":\"灾难\",\"v\":\"zainan\"},{\"n\":\"悬疑\",\"v\":\"xuanyi\"},{\"n\":\"警匪\",\"v\":\"jingfei\"},{\"n\":\"文艺\",\"v\":\"wenyi\"},{\"n\":\"青春\",\"v\":\"qingchun\"},{\"n\":\"冒险\",\"v\":\"maoxian\"},{\"n\":\"犯罪\",\"v\":\"fanzui\"},{\"n\":\"纪录\",\"v\":\"jilu\"},{\"n\":\"古装\",\"v\":\"guzhuang\"},{\"n\":\"奇幻\",\"v\":\"qihuan\"},{\"n\":\"国语\",\"v\":\"guoyu\"},{\"n\":\"综艺\",\"v\":\"zongyi\"},{\"n\":\"历史\",\"v\":\"lishi\"},{\"n\":\"运动\",\"v\":\"yundong\"},{\"n\":\"原创压制\",\"v\":\"yuanchuang\"},{\"n\":\"美剧\",\"v\":\"meiju\"},{\"n\":\"韩剧\",\"v\":\"hanju\"},{\"n\":\"国产电视剧\",\"v\":\"guoju\"},{\"n\":\"日剧\",\"v\":\"riju\"},{\"n\":\"英剧\",\"v\":\"yingju\"},{\"n\":\"德剧\",\"v\":\"deju\"},{\"n\":\"俄剧\",\"v\":\"eju\"},{\"n\":\"巴剧\",\"v\":\"baju\"},{\"n\":\"加剧\",\"v\":\"jiaju\"},{\"n\":\"西剧\",\"v\":\"anish\"},{\"n\":\"意大利剧\",\"v\":\"yidaliju\"},{\"n\":\"泰剧\",\"v\":\"taiju\"},{\"n\":\"港台剧\",\"v\":\"gangtaiju\"},{\"n\":\"法剧\",\"v\":\"faju\"},{\"n\":\"澳剧\",\"v\":\"aoju\"}]},{\"key\":\"area\",\"name\":\"地区\",\"value\":[{\"n\":\"不限\",\"v\":\"\"},{\"n\":\"中国大陆\",\"v\":\"中国大陆\"},{\"n\":\"中国香港\",\"v\":\"中国香港\"},{\"n\":\"中国台湾\",\"v\":\"中国台湾\"},{\"n\":\"美国\",\"v\":\"美国\"},{\"n\":\"英国\",\"v\":\"英国\"},{\"n\":\"日本\",\"v\":\"日本\"},{\"n\":\"韩国\",\"v\":\"韩国\"},{\"n\":\"法国\",\"v\":\"法国\"},{\"n\":\"印度\",\"v\":\"印度\"},{\"n\":\"德国\",\"v\":\"德国\"},{\"n\":\"西班牙\",\"v\":\"西班牙\"},{\"n\":\"意大利\",\"v\":\"意大利\"},{\"n\":\"澳大利亚\",\"v\":\"澳大利亚\"},{\"n\":\"比利时\",\"v\":\"比利时\"},{\"n\":\"瑞典\",\"v\":\"瑞典\"},{\"n\":\"荷兰\",\"v\":\"荷兰\"},{\"n\":\"丹麦\",\"v\":\"丹麦\"},{\"n\":\"加拿大\",\"v\":\"加拿大\"},{\"n\":\"俄罗斯\",\"v\":\"俄罗斯\"}]},{\"key\":\"year\",\"name\":\"年份\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"2022\",\"v\":\"2022\"},{\"n\":\"2021\",\"v\":\"2021\"},{\"n\":\"2020\",\"v\":\"2020\"},{\"n\":\"2019\",\"v\":\"2019\"},{\"n\":\"2018\",\"v\":\"2018\"},{\"n\":\"2017\",\"v\":\"2017\"},{\"n\":\"2016\",\"v\":\"2016\"},{\"n\":\"2015\",\"v\":\"2015\"},{\"n\":\"2014\",\"v\":\"2014\"},{\"n\":\"2013\",\"v\":\"2013\"},{\"n\":\"2012\",\"v\":\"2012\"},{\"n\":\"2011\",\"v\":\"2011\"},{\"n\":\"2010\",\"v\":\"2010\"},{\"n\":\"2009\",\"v\":\"2009\"},{\"n\":\"2008\",\"v\":\"2008\"},{\"n\":\"2007\",\"v\":\"2007\"},{\"n\":\"2006\",\"v\":\"2006\"},{\"n\":\"2005\",\"v\":\"2005\"},{\"n\":\"2004\",\"v\":\"2004\"},{\"n\":\"2003\",\"v\":\"2003\"},{\"n\":\"2002\",\"v\":\"2002\"},{\"n\":\"2001\",\"v\":\"2001\"},{\"n\":\"2000\",\"v\":\"2000\"}]},{\"key\":\"order\",\"name\":\"排序\",\"value\":[{\"n\":\"更新时间\",\"v\":\"0\"},{\"n\":\"豆瓣评分\",\"v\":\"1\"}]}]}");
        } catch (JSONException e) {
            SpiderDebug.log(e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r16v3, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r16v4 */
    /* JADX WARN: Type inference failed for: r16v5 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7, types: [boolean] */
    @TargetApi(19)
    public String playerContent(String str, String str2, List<String> list) {
        String str3;
        Exception e;
        String str4;
        JSONObject jSONObject;
        String str5;
        String str6;
        String str7;
        ArrayList arrayList;
        String str8;
        String str9;
        try {
            String str10 = "https://www.bdys01.com" + str2;
            AM TX = q2.rF(I.k(str10, s(str10, this.Mo))).TX("script");
            JSONObject jSONObject2 = new JSONObject();
            int i = 0;
            while (true) {
                if (i >= TX.size()) {
                    str4 = "";
                    break;
                }
                String bOVar = ((Qy) TX.get(i)).toString();
                if (bOVar.contains("var pid =")) {
                    String substring = bOVar.substring(bOVar.indexOf("var pid ="), bOVar.lastIndexOf("var time") + 1);
                    str4 = substring.substring(substring.indexOf("=") + 2, substring.lastIndexOf(";"));
                    break;
                }
                i++;
            }
            long currentTimeMillis = System.currentTimeMillis();
            String str11 = "https://www.bdys01.com/lines?t=" + currentTimeMillis + "&sg=" + g(str4 + "-" + currentTimeMillis, U.rF(str4 + "-" + currentTimeMillis, StandardCharsets.UTF_8).substring(0, 16)) + "&pid=" + str4;
            JSONObject jSONObject3 = new JSONObject(I.k(str11, s(str11, ""))).getJSONObject("data");
            ArrayList arrayList2 = new ArrayList();
            this.g = "";
            String str12 = "playUrl";
            if (jSONObject3.isNull("tos") || !jSONObject3.optString("tos").equals("1")) {
                jSONObject = jSONObject3;
                str6 = "url";
                str5 = "header";
            } else {
                long currentTimeMillis2 = System.currentTimeMillis();
                jSONObject = jSONObject3;
                String g = g(str4 + "-" + currentTimeMillis2, U.rF(str4 + "-" + currentTimeMillis2, StandardCharsets.UTF_8).substring(0, 16));
                String str13 = "https://www.bdys01.com/god/" + str4 + "?type=1";
                HashMap hashMap = new HashMap();
                hashMap.put("t", Long.toString(currentTimeMillis2));
                hashMap.put("sg", g);
                hashMap.put("verifyCode", "888");
                I.ZN(I.g(), str13, hashMap, s(str13, "origin"), new Bdys01$1(this));
                if (this.g.length() > 0) {
                    String str14 = this.g;
                    jSONObject2.put("parse", 0);
                    jSONObject2.put(str12, "");
                    jSONObject2.put("url", str14);
                    jSONObject2.put("header", "");
                    return jSONObject2.toString();
                }
                str5 = "header";
                str6 = "url";
                str12 = str12;
            }
            if (!jSONObject.isNull("url3")) {
                str7 = str5;
                arrayList = arrayList2;
                arrayList.add(jSONObject.optString("url3"));
            } else {
                str7 = str5;
                arrayList = arrayList2;
            }
            if (!jSONObject.isNull("m3u8")) {
                arrayList.add(jSONObject.optString("m3u8").replace("www.bde4.cc", "www.bdys01.com"));
            }
            if (!jSONObject.isNull("m3u8_2")) {
                str3 = "";
                for (String str15 : jSONObject.optString("m3u8_2").split(",")) {
                    try {
                        arrayList.add(str15.replace("www.bde4.cc", "www.bdys01.com"));
                    } catch (Exception e2) {
                        e = e2;
                        SpiderDebug.log(e);
                        return str3;
                    }
                }
            } else {
                str3 = "";
            }
            str3 = arrayList.isEmpty();
            try {
                if (str3 != 0) {
                    long currentTimeMillis3 = System.currentTimeMillis();
                    String g2 = g(str4 + "-" + currentTimeMillis3, U.rF(str4 + "-" + currentTimeMillis3, StandardCharsets.UTF_8).substring(0, 16));
                    StringBuilder sb = new StringBuilder();
                    sb.append("https://www.bdys01.com/god/");
                    sb.append(str4);
                    String sb2 = sb.toString();
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put("t", Long.toString(currentTimeMillis3));
                    hashMap2.put("sg", g2);
                    hashMap2.put("verifyCode", "666");
                    I.ZN(I.g(), sb2, hashMap2, s(sb2, "origin"), new Bdys01$2(this));
                    if (this.g.contains("rkey")) {
                        str9 = this.g.replace("?rkey", System.currentTimeMillis() + ".mp4?ver=6010&rkey");
                    } else {
                        str9 = this.g.replace("http:", "https:") + "/" + System.currentTimeMillis() + ".mp4";
                    }
                    jSONObject2.put("parse", 0);
                    jSONObject2.put(str12, (Object) str3);
                    jSONObject2.put(str6, str9);
                    jSONObject2.put(str7, (Object) str3);
                    return jSONObject2.toString();
                }
                String str16 = (String) arrayList.get(new Random().nextInt(arrayList.size()));
                if (str16.contains("mp4")) {
                    jSONObject2.put("parse", 0);
                    jSONObject2.put(str12, (Object) str3);
                    jSONObject2.put(str6, str16);
                    jSONObject2.put(str7, (Object) str3);
                    return jSONObject2.toString();
                }
                HashMap hashMap3 = new HashMap();
                I.T(str16, s(str16, str3), hashMap3);
                String s = I.s(hashMap3);
                Bdys01$3 r7 = new Bdys01$3(this);
                I.B(I.g(), s, (Map) null, q(s), r7);
                if (((Response) r7.getResult()).code() == 200) {
                    InputStream byteStream = ((Response) r7.getResult()).body().byteStream();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr = new byte[4];
                    while (true) {
                        int read = byteStream.read(bArr, 0, 4);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    byteArrayOutputStream.flush();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteStream.close();
                    String[] split = g.rF(Arrays.copyOfRange(byteArray, 3354, byteArray.length)).split("\n");
                    for (int i2 = 0; i2 < split.length; i2++) {
                        String str17 = split[i2];
                        if (str17.contains(".ts")) {
                            split[i2] = "https://vod.bdys.me/" + str17;
                        }
                    }
                    str8 = TextUtils.join("\n", split);
                } else {
                    str8 = str3;
                }
                jSONObject2.put(str6, "data:application/vnd.apple.mpegurl;base64," + Base64.encodeToString(str8.getBytes(), 2));
                jSONObject2.put("parse", 0);
                jSONObject2.put(str12, (Object) str3);
                jSONObject2.put(str7, new JSONObject(q("https://vod.bdys.me/")).toString());
                return jSONObject2.toString();
            } catch (Exception e3) {
                e = e3;
                SpiderDebug.log(e);
                return str3;
            }
        } catch (Exception e4) {
            e = e4;
            str3 = "";
        }
    }

    protected HashMap<String, String> q(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        String str2 = str.replace("https://", "").split("/")[0];
        hashMap.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36");
        hashMap.put("Authority", str2);
        hashMap.put("Origin", "www.bdys01.com");
        hashMap.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        return hashMap;
    }

    protected HashMap<String, String> s(String str, String str2) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36");
        if (!str2.equals("google")) {
            hashMap.put("Authority", "www.bdys01.com");
            if (str2.length() > 0) {
                if (str2.equals("origin")) {
                    hashMap.put("Origin", "https://www.bdys01.com");
                } else {
                    hashMap.put("Referer", str2);
                }
            }
            if (this.rF.length() > 0) {
                hashMap.put("Cookie", this.rF);
            }
        }
        hashMap.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        return hashMap;
    }

    public String searchContent(String str, boolean z) {
        try {
            String str2 = "https://www.google.com/search?q=site%3Awww.bdys01.com+" + URLEncoder.encode(str);
            VR rF = q2.rF(I.k(str2, s(str2, "google")));
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            AM TX = rF.TX("div.yuRUbf a");
            if (TX.size() > 0) {
                for (int i = 0; i < 1; i++) {
                    Qy qy = (Qy) TX.get(i);
                    String ZN = qy.TX("h3.LC20lb.MBeuO.DKV0Md").ZN();
                    String g = qy.g("href");
                    if (!g.contains("/s/") && !g.contains("play") && !g.contains("performer") && !g.contains("search") && !g.contains("jsessionid") && ZN.contains(str)) {
                        VR rF2 = q2.rF(I.k(g, s(g, this.Mo)));
                        JSONObject jSONObject2 = new JSONObject();
                        String g2 = rF2.m9("div.col-md-auto img").g("src");
                        String YH = rF2.m9("h2.d-sm-block.d-md-none").YH();
                        String replace = g.replace("https://www.bdys01.com", "");
                        jSONObject2.put("vod_name", YH);
                        jSONObject2.put("vod_remarks", "");
                        jSONObject2.put("vod_id", replace);
                        jSONObject2.put("vod_pic", g2);
                        jSONArray.put(jSONObject2);
                    }
                }
                jSONObject.put("list", jSONArray);
                return jSONObject.toString();
            }
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return "";
    }
}
