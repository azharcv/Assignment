package com.events.assignment.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.events.assignment.R;
import com.events.assignment.adapter.RvAdapter;
import com.events.assignment.model.Results;
import com.events.assignment.model.Root;
import com.events.assignment.reposervice.ApiService;
import com.events.assignment.retrofit.RetroClient;
import com.events.assignment.utils.InternetConnection;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Main Activity
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    RecyclerView recyclerContacts;
    RvAdapter adapter;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerContacts = findViewById(R.id.recyclerview);


        mostViewedData();

    }

    private void mostViewedData() {
        /**
         * Checking Internet Connection
         */
        if (InternetConnection.checkConnection(getApplicationContext())) {

            final ProgressDialog dialog;

            /**
             * Progress Dialog for User Interaction
             */
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Please wait...");
            dialog.show();

            //Creating an object of our api interface
            ApiService api = RetroClient.getApiService();

            /**
             * Calling JSON
             */
            Call<Root> call = api.getMostViewed("Zfg8sSa4SZk3Ypf9ApjR23CisgJplc7G");

            /**
             * Enqueue Callback will be call when get response...
             */
            call.enqueue(new Callback<Root>() {
                @Override
                public void onResponse(Call<Root> call, Response<Root> response) {
                    dialog.dismiss();


                    if(response.isSuccessful()) {

                        generateNoticeList(response.body().getResults());

                    } else {

                        Toast.makeText(MainActivity.this, "Something going wrong!", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<Root> call, Throwable t) {
                    dialog.dismiss();
                    Log.d(TAG,"message :"+t.getMessage());
                }
            });

        } else {
            Toast.makeText(MainActivity.this, "Check your internet connection!", Toast.LENGTH_SHORT).show();

        }
    }

    /** Method to generate List of notice using RecyclerView with custom adapter*/
    private void generateNoticeList(ArrayList<Results> noticeArrayList) {

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerContacts.setLayoutManager(manager);
        recyclerContacts.setHasFixedSize(true);
        adapter = new RvAdapter(getApplicationContext(),noticeArrayList);
        recyclerContacts.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);



        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                adapter.getFilter().filter(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                adapter.getFilter().filter(query);

                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Обрабатываем меню здесь. Нажатия на Home/Up
        // будут автоматически обработаны так, как указано в
        // родительской activity в AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
