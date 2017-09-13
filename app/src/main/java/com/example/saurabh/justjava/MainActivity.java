package com.example.saurabh.justjava;

import android.annotation.SuppressLint;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantity = 0;
    private int price = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whipdCream = (CheckBox) findViewById(R.id.Whipped_Cream);
        boolean WCreamFlag = whipdCream.isChecked();

        CheckBox chocolateCBox = (CheckBox) findViewById(R.id.Chocolate);
        boolean ChFlag = chocolateCBox.isChecked();

        EditText name = (EditText) findViewById(R.id.Name);

        String total = calculatePrice(quantity, WCreamFlag, ChFlag);
        String summary = createOrderSummary(name.getText().toString(),total, WCreamFlag, ChFlag);
        display(quantity);
        displaySummary(summary);
    }

    /**
     * This method is to build order summary to be displayed
     */
    public String createOrderSummary(String Name, String total, boolean wcFlag, boolean chFlag)
    {
       return "Name: " + Name +"\n"
               + "Quantity: " + quantity + "\n"
               + "Whipped Cream: " + wcFlag + "\n"
               + "Chocolate: " + chFlag +"\n"
               + "Total: " + total + "\n"
               + "Thank You!";
    }

    /**
     * This method returns the calculated Price.
     */
    @SuppressLint("NewApi")
    public String calculatePrice(int quantity, boolean WCFlag, boolean ChFlag)
    {
        float total = 0.0f;
        total += (WCFlag) ? 0.5 : 0;
        total += (ChFlag) ? 0.5 : 0;
        return NumberFormat.getCurrencyInstance().format(total);
    }


    /**
     * This method is called when the "+" button is clicked.
     */
    public void increment(View view) {
        quantity += 1;
        display(quantity);
//        displayPrice(quantity);
    }

    /**
     * This method is called when the "-" button is clicked.
     */
    public void decrement(View view) {
        if (quantity > 0)
        {
            quantity -= 1;
        }
        else
        {
            quantity = 0;
        }
        display(quantity);
//        displayPrice(quantity * price);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the order Summary.
     */
    @SuppressLint("NewApi")
    private void displaySummary(String Summary) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(Summary);
    }

    /**
     * This method displays the price after "+/-" is pressed
     */
//    private void displayPrice(int quantity)
//    {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(calculatePrice(quantity));
//    }
}
