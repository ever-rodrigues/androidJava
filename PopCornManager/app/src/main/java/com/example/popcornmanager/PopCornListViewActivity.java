package com.example.popcornmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.popcornmanager.adapters.PopCornAdapter;
import com.example.popcornmanager.entity.PopCornModel;

import java.util.ArrayList;

public class PopCornListViewActivity extends AppCompatActivity {

    private ListView listViewPopCornNames;

    public static final String ID="ID";
    public static final String NAME ="NAME";
    public static final String PRICE ="PRICE";
    public static final String TYPE ="TYPE";

    Button btnCrudPopCorn;
    Button btnAbout;

    public static ArrayList<PopCornModel> myPopCornList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_corn_list_view);
        btnCrudPopCorn =findViewById(R.id.btnGoToCrud);
        btnAbout =findViewById(R.id.btnGoToAbout);


        Intent intent= getIntent();
        Bundle bundle= intent.getExtras();

        if(bundle!=null){
            PopCornModel popCornModel= new PopCornModel();
            popCornModel.setPrice(bundle.getString(PRICE));
            popCornModel.setPopCornName(bundle.getString(NAME));
            popCornModel.setPopCornType(bundle.getString(TYPE));
            popCornModel.setId(bundle.getString(ID));
            if(myPopCornList.isEmpty()) {
                myPopCornList.add(popCornModel);
            }
            else if (checkIfExists(popCornModel)){
                System.out.println("Already has one PopCorn with this name!");
            }else {
                myPopCornList.add(popCornModel);
                }
            }
        listViewPopCornNames= findViewById(R.id.listVName);

        listViewPopCornNames.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id){
                PopCornModel popCornModel=(PopCornModel) listViewPopCornNames.getItemAtPosition(position);

                Toast.makeText(PopCornListViewActivity.this,
                        popCornModel.getPopCornName()+" clicked", Toast.LENGTH_SHORT).show();
            }
        });  btnCrudPopCorn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCrud = new Intent(PopCornListViewActivity.this,MainActivity.class);
                startActivity(goToCrud);
            }
        });
        populatePopCornList();
        goToCrud();
        goToAbout();

    }

    public void goToCrud(){
        btnCrudPopCorn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCrud = new Intent(PopCornListViewActivity.this,MainActivity.class);
                startActivity(goToCrud);
            }
        });
    }

    public void goToAbout(){
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAbout = new Intent(PopCornListViewActivity.this, About.class);
                startActivity(goToAbout);
            }
        });
    }

    private void populatePopCornList() {
        System.out.println(myPopCornList);
        PopCornAdapter popCornAdapter = new PopCornAdapter(this, myPopCornList);
        listViewPopCornNames.setAdapter(popCornAdapter);
    }

    public boolean checkIfExists(PopCornModel popCornModel){
        boolean alreadyExist=false;
            for(int i=0;i<myPopCornList.size();i++){
                if(myPopCornList.get(i).getId().equals(popCornModel.getId())){
                    myPopCornList.get(i).setPopCornName(popCornModel.getPopCornName());
                    myPopCornList.get(i).setPopCornType(popCornModel.getPopCornType());
                    myPopCornList.get(i).setPrice(popCornModel.getPrice());
                    return true;
                }
            }
        return alreadyExist;
    }
}