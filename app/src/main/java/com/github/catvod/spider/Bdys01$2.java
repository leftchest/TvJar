
package com.github.catvod.spider;

import com.github.catvod.spider.merge.P;
import okhttp3.Call;
import org.json.JSONException;
import org.json.JSONObject;

class Bdys01$2 extends P.kH {
    final Bdys01 Mo;

    Bdys01$2(Bdys01 bdys01) {
        this.Mo = bdys01;
    }

    protected void onFailure(Call call, Exception exc) {
    }

    public void onResponse(Object obj) {
        onResponse((String) obj);
    }

    public void onResponse(String str) {
        try {
            Bdys01.rF(this.Mo, new JSONObject(str).getString("url"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
