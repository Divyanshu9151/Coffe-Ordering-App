package com.example.android.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {
    int quantity=2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void submitOrder(View view) {

        EditText myEditText =  (EditText) findViewById(R.id.edit_view);
        String text = myEditText.getText().toString();
        CheckBox checkBox=(CheckBox)findViewById(R.id.checkbox);
        CheckBox chocolatebox=(CheckBox)findViewById(R.id.chocolate_checkbox);
        boolean whippedcream=checkBox.isChecked();
        boolean chocolate=chocolatebox.isChecked();
        String priceMessage=  createOrderSummary(quantity,whippedcream,chocolate,text);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just java app for "+text);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
    public void increment(View view) {


        if (quantity==100)
        {
            return;
        }
        quantity=quantity+1;
        displayQuantity(quantity);

    }
    public void decrement(View view) {

        if (quantity==1)
        {return;}
        quantity=quantity-1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }/**
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));

    }
*/


    private int calculatePrice(boolean text,boolean letter )
    { int totalprice;
     if (letter==true&&text==true){
        totalprice=(quantity*8);
    }
      else  if (text==true){
        totalprice=(quantity*6);
    }
     else if (letter==true)
        {
            totalprice=(quantity*7);
        }

     else
        {
            totalprice=quantity*5;
        }
     return totalprice;
    }

    private String createOrderSummary(int number,boolean text,boolean chocolate,String letter)
    {
        String name="Name:"+letter+"\nAdd Whipped Cream?"+text+"\nAdd Chocolate?"+chocolate+"\nQuantity:"+number+"\n"+"Total: ₹" +calculatePrice(text,chocolate)+"\n"+getString(R.string.thank_you);
        return name;

    }
}
