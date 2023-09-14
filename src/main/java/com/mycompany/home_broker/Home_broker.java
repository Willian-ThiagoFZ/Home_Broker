package com.mycompany.home_broker;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class Home_broker {

    public static void main(String[] args) {
        
        Double value = 100000000.2356;
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(5);
        System.out.println(format.format(value));
        
    }
}
