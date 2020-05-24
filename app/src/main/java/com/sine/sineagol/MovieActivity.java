package com.sine.sineagol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sine.sineagol.models.Movie;
import com.sine.sineagol.models.Theatre;

import java.util.List;

public class MovieActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    private static FirebaseUser user;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        mAuth=FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();

        recyclerView = findViewById(R.id.myrecyler);
        LinearLayout lndlogin = findViewById(R.id.lnidlogin);
        if(user!=null)lndlogin.setVisibility(View.INVISIBLE);
        else lndlogin.setVisibility(View.VISIBLE);

        new FirebaseDatabaseHelper("Movie").readMovies(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void movieIsLoaded(List<Movie> movies, List<String> keys) {
                new RecyclerView_Config().setConfig(recyclerView,MovieActivity.this,movies,keys);
            }

            @Override
            public void movieIsInserted() {

            }

            @Override
            public void movieUpdated() {

            }

            @Override
            public void movieDeleted() {

            }
            @Override
            public void theatreIsLoaded(List<Theatre> theatres){

            }
        });
        lndlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MovieActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
