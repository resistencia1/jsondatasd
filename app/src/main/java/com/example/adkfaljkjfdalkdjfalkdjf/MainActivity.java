package com.example.adkfaljkjfdalkdjfalkdjf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.adkfaljkjfdalkdjfalkdjf.Interface.JsonPlaceHolderApi;
import com.example.adkfaljkjfdalkdjfalkdjf.Model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

private TextView mJsonTxtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        TextView textView =findViewById(R.id.text_view);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setSelected(true);
*/


        mJsonTxtView=findViewById(R.id.jsonText);
       //
        mJsonTxtView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        mJsonTxtView.setSelected(true);
        //
        getPosts();


    }

    private void getPosts(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Posts>>call =jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {

                if(!response.isSuccessful()){
                    mJsonTxtView.setText("Codigo; "+response.code());
                    return;
                }

                List<Posts>poatsList=response.body();

                for(Posts posts:poatsList){
                    String content ="";
                 /*    content += "userId: " + posts.getUserId() + "\n"; */
                   content += "id: " + posts.getId() + "\n";
              /*       content += "title: " + posts.getTitle() + "\n";
                    content += "body: " + posts.getBody() + "\n\n";
*/
                    mJsonTxtView.append(content);

                }

            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                    mJsonTxtView.setText(t.getMessage());
            }
        });

    }
}
