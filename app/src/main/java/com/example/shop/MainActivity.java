package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int quantity = 0;
    Spinner spinner;               /*Переменные*/
    ArrayList spinnerArrayList;    /*Переменные*/
    ArrayAdapter spinnerAdapter;    /*Переменные*/
    HashMap goodsMap;                /*Переменные*/
    String goodsName;             /*Переменные*/
    double price;
    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEditText = findViewById(R.id.nameEditTex);

        createSpinner();
        
        createMap();
        // setTitle("App");


    }
    void createSpinner(){
        spinner =findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList= new ArrayList();

        spinnerArrayList.add("apple");
        spinnerArrayList.add("orange");
        spinnerArrayList.add("banana");

        spinnerAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList); /*связки с spinner ом*/
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

    }
    void createMap(){
        goodsMap = new HashMap();
        goodsMap.put("apple", 500.0);
        goodsMap.put("orange", 1500.0);
        goodsMap.put("banana", 2000.0);

    }

    public void increaseQuantity(View view) {
        quantity = quantity + 1;
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        quantityTextView.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price);
    }

    public void decreaseQuantity(View view) {
        quantity = quantity - 1;
        if (quantity<0){
            quantity =0;
        }
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        quantityTextView.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price);

    }

    @Override  /*Методы*/
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price= (double)goodsMap.get(goodsName);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price);


        ImageView goodsImageView = findViewById(R.id.goodsImageView);

       switch (goodsName){        /*Переключатель*/
           case "apple":
               goodsImageView.setImageResource(R.drawable.apple);
               break;
           case "orange":
               goodsImageView.setImageResource(R.drawable.orange);
               break;
           case "banana":
               goodsImageView.setImageResource(R.drawable.banana);
               break;
           default:
               goodsImageView.setImageResource(R.drawable.apple);
               break;

       }

    }

    @Override /*Методы*/
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCard(View view) {
        Order order=new Order();
        order.userName=userNameEditText.getText().toString();

        order.goodsName = goodsName;

        order.quantity =   quantity;
        order.price = price;

        order.orderPrice = quantity * price;




        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);

        orderIntent.putExtra("userNameForIntent", order.userName);
        orderIntent.putExtra("goodsName", order.goodsName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("price", order.price);
        orderIntent.putExtra("orderPrice", order.orderPrice);

        startActivity(orderIntent);

    }
}