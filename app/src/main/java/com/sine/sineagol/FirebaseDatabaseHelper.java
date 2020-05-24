package com.sine.sineagol;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sine.sineagol.models.Customer;
import com.sine.sineagol.models.Movie;
import com.sine.sineagol.models.Seat;
import com.sine.sineagol.models.Theatre;
import com.sine.sineagol.models.Ticket;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper{


    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private List<Movie> movieList = new ArrayList<>();
    private List<Customer> customerList = new ArrayList<>();
    private List<Theatre> theatreList = new ArrayList<>();
    private List<List<Seat>> Allseats = new ArrayList<>();
    public interface DataStatus{
        void movieIsLoaded(List<Movie> movies,List<String> keys);
        void movieIsInserted();
        void movieUpdated();
        void movieDeleted();
        void theatreIsLoaded(List<Theatre> theatres);
    }

    public FirebaseDatabaseHelper(String tableName){
        mDatabase=FirebaseDatabase.getInstance();
        mReference=mDatabase.getReference(tableName);

    }
    public void readMovies(final DataStatus dataStatus){
             mReference.addValueEventListener(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                     movieList.clear();
                     List<String> keys = new ArrayList<String>();
                     for(DataSnapshot keyNode:dataSnapshot.getChildren()){
                         keys.add(keyNode.getKey());
                         Movie movie = keyNode.getValue(Movie.class);
                         movieList.add(movie);
                     }
                     dataStatus.movieIsLoaded(movieList,keys);
                 }

                 @Override
                 public void onCancelled(@NonNull DatabaseError databaseError) {

                 }
             });
    }
    public void updateMovies(String key,Movie movie,final DataStatus dataStatus){
        movie.setId(key);
        mReference.child(key).setValue(movie).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.movieUpdated();
            }
        });
    }
    public void addTicket(Ticket ticket, final DataStatus dataStatus){
        String key = mReference.push().getKey();
        mReference.child(key).setValue(ticket).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.movieIsInserted();
            }
        });
    }
    public void addCustomer(Customer customer,final DataStatus dataStatus){
        String key = mReference.push().getKey();
        mReference.child(key).setValue(customer).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.movieIsInserted();
            }
        });
        }

    public void addTheatre(Theatre theatre,final DataStatus dataStatus){
        String key = mReference.push().getKey();
        theatre.setId(key);
        mReference.child(key).setValue(theatre).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.movieIsInserted();
            }
        });

    }
    public void updateTheatreSeat(String key, List<Seat> seats){

        mReference.child(key).child("seats").setValue(seats);

    }
    public void readTheatre(final DataStatus dataStatus){

        mReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                theatreList.clear();
                for(DataSnapshot keyNode:dataSnapshot.getChildren()){

                    Theatre theatre = keyNode.getValue(Theatre.class);
                    theatre.setId(keyNode.getKey());
                    theatreList.add(theatre);
                }
                dataStatus.theatreIsLoaded(theatreList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                ///////
            }
        });
    }
   /* public List<List<Seat>> readSeats(final DataStatus dataStatus){
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot keyNode:dataSnapshot.getChildren()){

                    Theatre theatre = keyNode.getValue(Theatre.class);
                    theatre.setId(keyNode.getKey());
                    List<Seat>seats=theatre.getSeats();
                    for (int i = 0; i <seats.size() ; i++) {
                        Allseats.get(0).add(seats.get(i));
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/


}
