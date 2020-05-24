package com.sine.sineagol;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.sine.sineagol.models.Customer;
import com.sine.sineagol.models.Movie;
import com.sine.sineagol.models.Theatre;
import com.sine.sineagol.models.Ticket;

import java.util.List;

public class TicketActivity extends AppCompatActivity {


    Button[] btnseats = new Button[28];
    int colorFull,colorEmpty,colorEmptyText,colorFullText;
    Dialog dialog;
    private String key;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ticket);

        btnseats[0]=findViewById(R.id.btnseat1);
        btnseats[1]=findViewById(R.id.btnseat2);
        btnseats[2]=findViewById(R.id.btnseat3);
        btnseats[3]=findViewById(R.id.btnseat4);
        btnseats[4]=findViewById(R.id.btnseat5);
        btnseats[5]=findViewById(R.id.btnseat6);
        btnseats[6]=findViewById(R.id.btnseat7);
        btnseats[7]=findViewById(R.id.btnseat8);
        btnseats[8]=findViewById(R.id.btnseat9);
        btnseats[9]=findViewById(R.id.btnseat10);
        btnseats[10]=findViewById(R.id.btnseat11);
        btnseats[11]=findViewById(R.id.btnseat12);
        btnseats[12]=findViewById(R.id.btnseat13);
        btnseats[13]=findViewById(R.id.btnseat14);
        btnseats[14]=findViewById(R.id.btnseat15);
        btnseats[15]=findViewById(R.id.btnseat16);
        btnseats[16]=findViewById(R.id.btnseat17);
        btnseats[17]=findViewById(R.id.btnseat18);
        btnseats[18]=findViewById(R.id.btnseat19);
        btnseats[19]=findViewById(R.id.btnseat20);
        btnseats[20]=findViewById(R.id.btnseat21);
        btnseats[21]=findViewById(R.id.btnseat22);
        btnseats[22]=findViewById(R.id.btnseat23);
        btnseats[23]=findViewById(R.id.btnseat24);
        btnseats[24]=findViewById(R.id.btnseat25);
        btnseats[25]=findViewById(R.id.btnseat26);
        btnseats[26]=findViewById(R.id.btnseat27);
        btnseats[27]=findViewById(R.id.btnseat28);



        colorFull =Color.rgb(	33, 33, 33);
        colorEmpty=Color.rgb(	158, 158, 158);
        colorEmptyText=Color.rgb(	239, 235, 233);
        colorFullText=Color.rgb(221, 44, 0);



        key=getIntent().getStringExtra("key");

        /// Adding theatres just 1 time. So this code is not neccessary if the theatres already exist.

       /* for (int i = 0; i <=3; i++) {
            Theatre theatre = new Theatre("Theatre-"+i);
           new FirebaseDatabaseHelper("Theatre").addTheatre(theatre, new FirebaseDatabaseHelper.DataStatus() {
               @Override
               public void movieIsLoaded(List<Movie> movies, List<String> keys) {
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
               public void theatreIsLoaded(List<Theatre> theatres) {
               }
           });

        }*/

        ////////////////////////////////////////////////////////////////////////
        for (int i = 0; i <btnseats.length ; i++) {


            final int finalI = i;
            ////////////////////////



            new FirebaseDatabaseHelper("Theatre").readTheatre(new FirebaseDatabaseHelper.DataStatus() {
                @Override
                public void movieIsLoaded(List<Movie> movies, List<String> keys) {

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
                public void theatreIsLoaded(List<Theatre> theatres) {

                    if(theatres.get(Integer.valueOf(key)-1).getSoloSeat(finalI).isStatus()==true){
                        btnseats[finalI].setBackgroundColor(colorFull);
                        btnseats[finalI].setClickable(false);
                        btnseats[finalI].setText("Full");
                        btnseats[finalI].setTextColor(colorFullText);
                    }
                    else{
                        btnseats[finalI].setBackgroundColor(colorEmpty);
                        btnseats[finalI].setClickable(true);
                        btnseats[finalI].setText(theatres.get(Integer.valueOf(key)-1).getSoloSeat(finalI).getCode());
                        btnseats[finalI].setTextColor(colorEmptyText);
                    }

                }

            });




            ///////////////////////

            btnseats[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog =new Dialog(TicketActivity.this,R.style.Dialog);
                    dialog.setContentView(R.layout.custom_dialog);
                    dialog.setTitle("Book Seat");
                    TextView txtseatno=dialog.findViewById(R.id.txtseatNo);
                    txtseatno.setText(btnseats[finalI].getText().toString());
                    TextView txtprice =dialog.findViewById(R.id.txtprice);
                    txtprice.setText("50TL");
                    Button btncancel = dialog.findViewById(R.id.btncancel);
                    Button btnbook = dialog.findViewById(R.id.btnbook);
                    final EditText edtcstname = (EditText) dialog.findViewById(R.id.edtcstname);
                    final EditText edtcstsurname = (EditText) dialog.findViewById(R.id.edtcstsurname);
                    final EditText edtcstphone = (EditText) dialog.findViewById(R.id.edtcstph);
                    final EditText edtcstmail = (EditText) dialog.findViewById(R.id.edtcstmail);



                    btnbook.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            final Customer customer=new Customer();
                            if(!edtcstname.getText().toString().equals("") && !edtcstsurname.getText().toString().equals("") && !edtcstphone.getText().toString().equals("")
                                    && !edtcstmail.getText().toString().equals("") && edtcstphone.getText().toString().length()==10 && edtcstmail.getText().toString().contains("@")
                                    && edtcstmail.getText().toString().endsWith(".com")){

                                customer.setName(edtcstname.getText().toString());
                                customer.setSurname(edtcstsurname.getText().toString());
                                customer.setPhone(edtcstphone.getText().toString());
                                customer.setEmail(edtcstmail.getText().toString());

                                new FirebaseDatabaseHelper("Theatre").readTheatre(new FirebaseDatabaseHelper.DataStatus() {
                                    @Override
                                    public void movieIsLoaded(List<Movie> movies, List<String> keys) {

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
                                    public void theatreIsLoaded(List<Theatre> theatres) {


                                        if(theatres.get(Integer.valueOf(key)-1).getSoloSeat(finalI).isStatus()==false){

                                            theatres.get(Integer.valueOf(key)-1).getSoloSeat(finalI).setStatus(true);
                                            btnseats[finalI].setBackgroundColor(colorFull);
                                            btnseats[finalI].setClickable(false);

                                            ///Initializing ticket
                                            Ticket ticket = new Ticket(customer,theatres.get(Integer.valueOf(key)-1).getSoloSeat(finalI),50);

                                            //// Adding to firebase
                                            new FirebaseDatabaseHelper("ticket").addTicket(ticket, new FirebaseDatabaseHelper.DataStatus() {
                                                @Override
                                                public void movieIsLoaded(List<Movie> movies, List<String> keys) {

                                                }

                                                @Override
                                                public void movieIsInserted() {
                                                    Toast.makeText(TicketActivity.this,"Success ticket",Toast.LENGTH_LONG).show();
                                                }

                                                @Override
                                                public void movieUpdated() {

                                                }

                                                @Override
                                                public void movieDeleted() {

                                                }

                                                @Override
                                                public void theatreIsLoaded(List<Theatre> theatres) {

                                                }
                                            });
                                            ////Updating seats from theatre

                                            new FirebaseDatabaseHelper("Theatre").updateTheatreSeat(theatres.get(Integer.valueOf(key)-1).getId(),theatres.get(Integer.valueOf(key)-1).getSeats());

                                        }

                                    }

                                });

                                dialog.dismiss();

                            }
                            else if(edtcstphone.getText().toString().length()<10)
                                Toast.makeText(TicketActivity.this,"Check your phone",Toast.LENGTH_SHORT).show();
                            else if(!edtcstmail.getText().toString().contains("@") && !edtcstmail.getText().toString().endsWith(".com"))
                                Toast.makeText(TicketActivity.this,"Check your e-mail (exp.format: @blabla.com)",Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(TicketActivity.this,"Fill the blanks",Toast.LENGTH_SHORT).show();
                        }




                    });
                    btncancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });
                    dialog.show();
                };

            });





        }

    }
}
