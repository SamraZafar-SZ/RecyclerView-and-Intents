package com.example.assignment2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RestaurantAdapter.ItemSelected {

    private static final int ADD_REQUEST_CODE = 100;
    RecyclerView recyclerView = this.<RecyclerView>findViewById(R.id.list);
    RecyclerView.Adapter myadapter;
    RecyclerView.LayoutManager manager;
    ArrayList<Restautant> restaurants;
    Button btnadd;
    private ActivityResultLauncher<Intent> addRestaurantLauncher; //Launcher responsible for launching new activity and receiving result from it

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);   //in-built
        setContentView(R.layout.activity_main);
        recyclerView.setHasFixedSize(true);
        manager=new LinearLayoutManager(this);
        restaurants=new ArrayList<>();  //make array list

        restaurants.add(new Restautant("Mei Kong","Block F Johar Town","03224099999","A chinese restaurant"));
        restaurants.add(new Restautant("La Cornucopia","Qasr-e-Noor, Cantt","03224088900","A floral restaurant"));
        restaurants.add(new Restautant("Cosa Nostra","Block H Garden Town","032240977779","An Italian restaurant"));
        restaurants.add(new Restautant("Monal","New Garden Town, Block A","0322404345699","A rooftop restaurant"));

        recyclerView.setLayoutManager(manager);

        myadapter=new RestaurantAdapter(this,restaurants);

        recyclerView.setAdapter(myadapter);

        //An instance of ActivityResultContracts.StartActivityForResult() to specify the contract for launching an activity for result.
        //A lambda expression defining the callback to handle the result returned by the launched activity.
        //In the lambda expression, we check if the result code is RESULT_OK, indicating that the AddActivity was successful in adding a new restaurant.

        addRestaurantLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        // Retrieve the new restaurant from AddActivity
                        Intent data = result.getData();
                        if (data != null) {
                            Restautant newRestaurant = data.getParcelableExtra("newRestaurant");
                            if (newRestaurant != null) {
                                // Add the new restaurant to the list and notify adapter
                                restaurants.add(newRestaurant);
                                myadapter.notifyDataSetChanged();
                                Toast.makeText(this, "Restaurant added successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

        btnadd=findViewById(R.id.btnadd);
        btnadd.setOnClickListener(new View.OnClickListener(){

            //Button click listener uses intent to call the Add Activity

            @Override
            public void onClick(View view) { //new intent is created for Add Activity with a launcher, to get back the added data
                Intent intent=new Intent(MainActivity.this,AddActivity.class);
                addRestaurantLauncher.launch(intent);
            }
        });

    }

    @SuppressLint("NotifyDataSetChanged")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //the new added data is passed from AddActivity back to MainActivity, added in arrayList and notified to adapter class
        super.onActivityResult(requestCode, resultCode, data);
        Restautant newrestaurant=data.getParcelableExtra("newrestaurant");
        if(newrestaurant!=null){
            restaurants.add(newrestaurant);
            myadapter.notifyDataSetChanged();
            Toast.makeText(this,"Restaurant added successfully!",Toast.LENGTH_SHORT).show();

        }
    }


    @Override
    public void onItemClicked(int index) {                   //makes a toast that shows the restaurant description
                                                             //and open a dial pad with the restaurant number
        Toast.makeText(this,restaurants.get(index).getDescription(),Toast.LENGTH_SHORT).show();
        String phone_=restaurants.get(index).getPhone();
        Intent newintent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone_));
    }
}