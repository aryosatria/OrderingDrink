package com.aryosatria.orderingdrink;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int quantity=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() !=null)
            getSupportActionBar().setTitle("Teh Tarik");
    }

    public void increment(View view){//perintah tombol tambah
        if(quantity==100){
            Toast.makeText(this,"pesanan maximal 100",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity+1 ;
        display(quantity);
    }
    public void decrement(View view){//perintah tombol tambah
        if (quantity==1){
            Toast.makeText(this,"pesanan minimal 1",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity -1;
        display(quantity);
    }


    public void Submitorder(View view) {
        EditText nameEditText=(EditText)findViewById(R.id.edt_name);
        String name=nameEditText.getText().toString();
        Log.v("MainActivity","Nama:"+name);

        CheckBox whippedcreamChekBox= (CheckBox) findViewById(R.id.WhippedCream_checkbox);
        boolean haswhippedcream=whippedcreamChekBox.isChecked();//mengidentifikasi check
        Log.v("MainActivity","has whippedcream:"+haswhippedcream);

        CheckBox chocolateChekBox= (CheckBox) findViewById(R.id.Chocolate_checkbox);
        boolean hasWhippedchocolate=chocolateChekBox.isChecked();//mengidentifikasi check
        Log.v("MainActivity","has whippedchocolate:"+hasWhippedchocolate);

        CheckBox honeyChekBox= (CheckBox) findViewById(R.id.Honey_checkbox);
        boolean haswhippedhoney=honeyChekBox.isChecked();
        Log.v("MainActivity", "has whippedhoney"+haswhippedhoney);

        CheckBox oreoChekbox= (CheckBox) findViewById(R.id.WhippedOreo_checkbox);
        boolean haswhippedoreo=oreoChekbox.isChecked();
        Log.v("MainActivity", "whippedoreo:"+haswhippedoreo);

        int price=calculateprice(haswhippedcream,hasWhippedchocolate,haswhippedhoney,haswhippedoreo);//memanggil method jumlah harga
        String pricemessage=createOrderSummary(price,name,haswhippedcream,hasWhippedchocolate,haswhippedhoney,haswhippedoreo);


        displayMessage(pricemessage);

    }
    private int calculateprice(boolean addwhipedcream,boolean addchocolate,boolean addhoney,boolean addoreo){//jumlah pesanan * harga
        int harga=5000;

        if(addwhipedcream){
            harga=harga+2000;//harga tambahan toping
        }

        if (addchocolate){
            harga=harga+2000;
        }

        if (addhoney){
            harga=harga+3000;
        }

        if (addoreo){
            harga=harga+2000;
        }

        return quantity * harga;
    }
    private String createOrderSummary(int price, String name, boolean addWhippedChocolate, boolean addWhippedCream, boolean addWhippedHoney, boolean addWhippedOreo) {//hasil pemesanan
        String pricemessage=" Nama Pembeli = "+name;
        pricemessage+="\n Tambah Coklat =" +addWhippedCream;
        pricemessage+="\n Tambah Krim =" +addWhippedChocolate;
        pricemessage+="\n Tambah Madu =" +addWhippedHoney;
        pricemessage+="\n Tambah Oreo =" +addWhippedOreo;
        pricemessage+="\n Jumlah Pemesanan =" +quantity;
        pricemessage+="\n Total = Rp " +price;
        pricemessage+="\n Terimakasih";
        return  pricemessage;
    }

    //method ini untuk mencetak hasil perintah yang di tampilkan dengan inisial quantity_textview di textview 0
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_textview);
        priceTextView.setText(message);
    }
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_textview);
        quantityTextView.setText("" + number);
    }
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_textview);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}