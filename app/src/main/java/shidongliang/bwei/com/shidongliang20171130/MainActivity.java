package shidongliang.bwei.com.shidongliang20171130;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import shidongliang.bwei.com.shidongliang20171130.adpter.MyAdapter;
import shidongliang.bwei.com.shidongliang20171130.bean.JsonBean;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private MyAdapter myAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getData();

    }

    private void getData() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://120.27.23.105/product/getProducts?pscid=1&page=1")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "没有数据", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                  final String json=response.body().string();
                  runOnUiThread(new Runnable() {


                      @Override
                      public void run() {
                          Gson gson = new Gson();
                          JsonBean jsonBean=null;
                          jsonBean=gson.fromJson(json,JsonBean.class);
                          List<JsonBean.DataBean> list = jsonBean.getData();
                          if (myAdapter==null){
                              myAdapter = new MyAdapter(MainActivity.this,list);
                              linearLayoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
                              recyclerview.setLayoutManager(linearLayoutManager);

                              recyclerview.setAdapter(myAdapter);
                          }else {
                              myAdapter.notifyDataSetChanged();
                          }

                      }
                  });
            }
        });

    }
}
