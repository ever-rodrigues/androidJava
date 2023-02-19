package com.example.popcornmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.popcornmanager.entity.PopCornModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Spinner spinnerSpecials;
    CheckBox cbBaconValid;
    CheckBox cbCheeseValid;
    CheckBox cbButterValid;
    TextView tvPopCornName;
    TextView tvPrice;
    RadioButton rbSpecial;
    RadioButton rbNormal;
    Spinner spSpecials;
    Button btnSaveData;

    List<PopCornModel> listOfPopCorn= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerSpecials=findViewById(R.id.spinnerSpecials);
        populateSpinner();
        cbCheeseValid= findViewById(R.id.cBCheese);
        cbButterValid= findViewById(R.id.cBButter);
        rbSpecial =findViewById(R.id.rBSpecial);
        rbNormal =findViewById(R.id.rBNormal);
        tvPopCornName = findViewById(R.id.eTPopCornName);
        tvPrice=findViewById(R.id.eTPrice);
        spSpecials = findViewById(R.id.spinnerSpecials);
        cbBaconValid=findViewById(R.id.cBBacon);

        btnSaveData= findViewById(R.id.btnSave);

        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirmAlldata(v)){
                    PopCornModel savedPopCornModel=savePopCornModel();
                    sendObjectToNextActivity(savedPopCornModel);
                }
                else {
                    String message="";
                    Toast.makeText(MainActivity.this,
                            "Please fill all required fields!",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void sendObjectToNextActivity(PopCornModel popCornModel){
        Intent mainIntent = new Intent(MainActivity.this,PopCornListViewActivity.class);
        mainIntent.putExtra(PopCornListViewActivity.NAME, popCornModel.getPopCornName());
        mainIntent.putExtra(PopCornListViewActivity.ID,popCornModel.getId());
        mainIntent.putExtra(PopCornListViewActivity.PRICE, popCornModel.getPrice());
        mainIntent.putExtra(PopCornListViewActivity.TYPE,popCornModel.getPopCornType());

        startActivity(mainIntent);
    }

    public PopCornModel savePopCornModel(){
        PopCornModel popCornModel= new PopCornModel();
        popCornModel.setId(tvPopCornName.getText().toString()+"-POPCORN");
        popCornModel.setPopCornName(tvPopCornName.getText().toString());
        if(rbNormal.isChecked()){
            popCornModel.setPopCornType(rbNormal.getText().toString());
        }else{
            popCornModel.setPopCornType(rbSpecial.getText().toString());
        }
        popCornModel.setPrice(tvPrice.getText().toString());
        System.out.println(popCornModel.getPopCornName());
        return popCornModel;
    }

    public void populateSpinner(){
        List<String> spinnerList = new ArrayList<>();
        spinnerList.add("powered milk");
        spinnerList.add("chocolate");
        spinnerList.add("canned milk");
        spinnerList.add("banoffee");

        ArrayAdapter<String> adapter=
                new ArrayAdapter<>(this
                , android.R.layout.simple_list_item_1,
                        spinnerList);
        spinnerSpecials.setAdapter(adapter);
    }


    public boolean isValidName(){
        if(tvPopCornName.getText().length()==0){
            return false;
        }
        return true;
    }

    public boolean isValidIngredient(){
        if(!cbBaconValid.isChecked() &&
                !cbCheeseValid.isChecked() &&
                !cbButterValid.isChecked()){
            return false;
        }
        return true;
    }

    public boolean isValidType(){
        if(!rbSpecial.isChecked() &&
                !rbNormal.isChecked()){
            return false;
        }
        return true;
    }

    public boolean confirmAlldata(View view){
        String message="";
        if(!isValidName()){
            message="Please write a PopCorn name";
            tvPopCornName.requestFocus();
            return false;
        } else if (!isValidIngredient()){
            message="Please select one ingredient";
            return false;
        } else if (!isValidType()) {
            message="Please select one Type";
            return false;
        } else {
            message="Saving on Database";
        }
        Toast.makeText(this,
                message,
                Toast.LENGTH_LONG).show();
        return true;
    }

    public void cleanAll(View view){
        tvPopCornName.setText("");
        cbBaconValid.setChecked(false);
        cbButterValid.setChecked(false);
        cbCheeseValid.setChecked(false);
        rbNormal.setChecked(false);
        rbSpecial.setChecked(false);
    }

    public void selectSpecial(View view){
        if(rbSpecial.isChecked()){
            rbNormal.setChecked(false);
        }
    }

    public void selectNormal(View view){
        if(rbNormal.isChecked()){
            rbSpecial.setChecked(false);
        }
    }

}