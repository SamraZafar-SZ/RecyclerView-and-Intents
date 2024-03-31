package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddActivity extends AppCompatActivity {

    EditText etname, etphone, etlocation, etdescription;
    Button btnadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        btnadd=findViewById(R.id.btnadd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name,loc,phn,desc;
                name=etname.getText().toString().trim();
                phn=etphone.getText().toString().trim();
                desc=etdescription.getText().toString().trim();
                loc=etlocation.getText().toString().trim();

                //checks that none of the fields is left empty

                if(name.isEmpty()){
                    etname.setError("Empty");
                }
                else if(phn.isEmpty()){
                    etphone.setError("Empty");
                }
                if(desc.isEmpty()){
                    etdescription.setError("Empty");
                }
                if(loc.isEmpty()){
                    etlocation.setError("Empty");
                }

                //makes a new Restaurant item and passes it back to Main Activity using .putExtra() method and setResult()

                Restautant restaurant=new Restautant(name,loc,phn,desc);
                Intent intent=new Intent(AddActivity.this,MainActivity.class);
                intent.putExtra("newrestaurant", (Parcelable) restaurant);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    //method to retrieve the entered data in Edit Text fields
    public void init(){
        etname=findViewById(R.id.etname);
        etlocation=findViewById(R.id.etlocation);
        etphone=findViewById(R.id.etphone);
        etdescription=findViewById(R.id.etdescription);
    }

}