package com.example.daterequest.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.daterequest.Bean.Weather;
import com.example.daterequest.Bean.date;
import com.example.daterequest.R;
import com.example.daterequest.Adapter.weatherAdapter;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tv_txt;
    OkHttpClient okHttpClient = new OkHttpClient();
    private ListView lv_list_weather;
    private List<Weather> tingHao;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_txt = (TextView) findViewById(R.id.tv_txt);
        lv_list_weather =(ListView)findViewById(R.id.lv_list_weather);
    }

    public void doGet(View view) throws IOException {

        //1、拿到okhttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();

        //2、构造Request
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url("http://wthrcdn.etouch.cn/weather_mini?city=沈阳").build();

        //3、将Request封装到Call
        Call call = okHttpClient.newCall(request);

        //4、执行call
        //        Response response = call.execute();  直接执行
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                date.e("onFailure" + e.getMessage());
                date.printStackTrace();

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                date.e("onResponse");
                final String res = response.body().string();
                date.e(res);
                try {
                   JSONObject jsonObject=new JSONObject(res);
                   JSONObject json=jsonObject.getJSONObject("data");
                   JSONArray array1=json.getJSONArray("forecast");
                    tingHao = new ArrayList<>();
                   for (int i=0;i<array1.length();i++){
                       JSONObject list=array1.getJSONObject(i);
                       Weather weather = new Weather();
                       weather.setFl(list.getString("fengli"));
                       weather.setFx(list.getString("fengxiang"));
                       weather.setHigh(list.getString("high"));
                       weather.setLow(list.getString("low"));
                       weather.setType(list.getString("type"));
                       weather.setDate(list.getString("date"));
                       tingHao.add(weather);
                   }
                    lv_list_weather.post(new Runnable() {
                        @Override
                        public void run() {
                            lv_list_weather.setAdapter(new weatherAdapter(MainActivity.this, tingHao));
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }


}



