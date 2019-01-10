package com.example.anusha.newsapi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    ProgressBar pb;
    RecyclerView rv;
    ArrayList<NewsModel> newsModels;
    final static int ID=123;
    String news="https://newsapi.org/v2/everything?q=bitcoin&apiKey=1503739161854d7b9d37ed4de68142b5";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=findViewById(R.id.recycler);
        pb = findViewById(R.id.progressBar);
        newsModels=new ArrayList<>();

        rv.setLayoutManager(new GridLayoutManager(this,2));
        rv.setAdapter(new NewsAdapter(this,newsModels) );
       // new NewsTasks().execute();

        getSupportLoaderManager().initLoader(ID,null,this);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new AsyncTaskLoader<String>(this) {
            @Nullable
            @Override
            public String loadInBackground() {
                try {
                    URL url = new URL(news);
                    Log.i("newsapi",url.toString());
                    HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.connect();
                    InputStream is =urlConnection.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(is));
                    StringBuilder builder = new StringBuilder();
                    String l="";
                    while ((l=reader.readLine())!=null){
                        builder.append(l);

                    }
                    return builder.toString();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                return null;
            }

            @Override
            protected void onStartLoading() {
                super.onStartLoading();

                forceLoad();
                pb.setVisibility(View.VISIBLE);
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        pb.setVisibility(View.GONE);
        //newsModels = new ArrayList<>();
        Toast.makeText(MainActivity.this, ""+data, Toast.LENGTH_SHORT).show();
        if (data!=null){
            try {
                JSONObject jsonObject = new JSONObject(data);
                JSONArray jsonArray = jsonObject.getJSONArray("articles");

                for (int i=0;i<jsonArray.length();i++){
                    JSONObject newsinfo=jsonArray.getJSONObject(i);

                    String title=newsinfo.getString("title");
                    Log.i("newsapi",title);
                    String desc=newsinfo.getString("description");
                    Log.i("newsapi",desc);
                    String img=newsinfo.getString("urlToImage");
                    Log.i("newsapi",img);
                    Toast.makeText(MainActivity.this, title+"\n"+desc+"\n"+img, Toast.LENGTH_SHORT).show();
                    // Toast.makeText(MainActivity.this, BookTitle+"\n"+BookDescription+"\n"+BookImage, Toast.LENGTH_SHORT).show();

                    newsModels.add(new NewsModel(title,img,desc));


                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            // rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            //rv.setAdapter(new NewsAdapter(this,newsModels) );
            //rv.setAdapter(new NewsAdapter(getApplicationContext(),newsModels));
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

   /* public class NewsTasks extends AsyncTask<String,Void,String>{
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(news);
                Log.i("newsapi",url.toString());
                HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                InputStream is =urlConnection.getInputStream();
                BufferedReader reader=new BufferedReader(new InputStreamReader(is));
                StringBuilder builder = new StringBuilder();
                String l="";
                while ((l=reader.readLine())!=null){
                    builder.append(l);

                }
                return builder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pb.setVisibility(View.GONE);
            //newsModels = new ArrayList<>();
            Toast.makeText(MainActivity.this, ""+s, Toast.LENGTH_SHORT).show();
            if (s!=null){
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("articles");

                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject newsinfo=jsonArray.getJSONObject(i);

                        String title=newsinfo.getString("title");
                        Log.i("newsapi",title);
                        String desc=newsinfo.getString("description");
                        Log.i("newsapi",desc);
                        String img=newsinfo.getString("urlToImage");
                        Log.i("newsapi",img);
                        Toast.makeText(MainActivity.this, title+"\n"+desc+"\n"+img, Toast.LENGTH_SHORT).show();
                        // Toast.makeText(MainActivity.this, BookTitle+"\n"+BookDescription+"\n"+BookImage, Toast.LENGTH_SHORT).show();

                        newsModels.add(new NewsModel(title,img,desc));


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                //rv.setAdapter(new NewsAdapter(this,newsModels) );
                //rv.setAdapter(new NewsAdapter(getApplicationContext(),newsModels));
            }
        }
    }
*/
}
