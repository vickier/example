package com.example.daterequest.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.daterequest.Adapter.BookAdapter;
import com.example.daterequest.Bean.Book;
import com.example.daterequest.Bean.date;
import com.example.daterequest.R;

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

public class BookActivity extends AppCompatActivity {

    OkHttpClient okHttpClient=new OkHttpClient();
    private ListView lv_list_book;
    private Button btn_get;
    private List<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        initView();

    }

    private void initView() {
        lv_list_book =(ListView)findViewById(R.id.lv_list_book);
        btn_get =(Button)findViewById(R.id.btn_get);
    }
    public void doGet(View view){

        OkHttpClient okHttpClient=new OkHttpClient();

        Request.Builder builder=new Request.Builder();

        final Request request=builder.get().url("http://apis.juhe.cn/goodbook/catalog?key=8022fc13c88a24492824e3e47eb1fae0&dtype=json").build();

        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                date.e("onFailure"+e.getMessage());
                date.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                date.e("onResponse");
                final String res=response.body().string();
                date.e(res);

                try {
                    JSONObject jsonObject=new JSONObject(res);
                    JSONArray result=jsonObject.getJSONArray("result");
                    books = new ArrayList<>();
                    for (int i=0;i<result.length();i++){
                       JSONObject reList=result.getJSONObject(i);
                        Book book=new Book();
                        book.setId(reList.getString("id"));
                        book.setCatalog(reList.getString("catalog"));
//                        book.setL_id(Integer.parseInt(reList.getString("id")));
                        books.add(book);
                    }
                    lv_list_book.post(new Runnable() {
                        @Override
                        public void run() {
                            lv_list_book.setAdapter(new BookAdapter(BookActivity.this,books));
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
