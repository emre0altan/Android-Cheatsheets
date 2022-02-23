package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private String resultString = "";
    private char[] operatorCharacters = {'*','/','+','-'};
    private boolean needsClear = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTextView = (TextView) findViewById(R.id.textResult);

        ((Button) findViewById(R.id.btnDelete)).setOnClickListener(this::DeleteLastCharacter);
        ((Button) findViewById(R.id.btnDeleteAll)).setOnClickListener(this::DeleteAll);
        ((Button) findViewById(R.id.btnCalculate)).setOnClickListener(this::CalculateResult);
    }


    public void AddCharacter(View view) {
        try{
            if(needsClear){
                DeleteAll(view);
                needsClear = false;
            }
            char charToAdd = ((Button) view).getText().charAt(0);
            if((IsLastCharacterOperator() || resultString.length() == 0) && IsOperator(charToAdd)){
                DeleteLastCharacter(view);
            }
            resultString += charToAdd;
            UpdateResultString();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void UpdateResultString(){
        try{
            resultTextView.setText(resultString);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void DeleteLastCharacter(View view){
        if(resultString.length() > 0){
            resultString = resultString.substring(0,resultString.length()-1);
            UpdateResultString();
        }
    }

    private void DeleteAll(View view){
        resultString = "";
        UpdateResultString();
    }

    private void CalculateResult(View view){
        String[] ops = resultString.split("\\s*[0-9]+\\s*");
        String[] notops = resultString.split("\\s*[^0-9]+\\s*");
        String[] res = new String[ops.length+notops.length-1];
        for(int i=0; i<res.length; i++) res[i] = i%2==0 ? notops[i/2] : ops[i/2+1];

        ArrayList<String> b = FirstProcess(res);
        ArrayList<String> c = SecondProcess(b);
        resultString = c.toString();
        UpdateResultString();
        needsClear = true;
    }

    private ArrayList<String> FirstProcess(String[] a){
        ArrayList<String> b = new ArrayList<String>();

        for (int i=0;i<a.length;i++){
            if(a[i].charAt(0) == '*') {
                b.set(b.size()-1, String.valueOf(Float.valueOf(b.get(b.size()-1)) * Float.valueOf(a[i+1]))) ;
                i++;
            }
            else if(a[i].charAt(0) == '/'){
                b.set(b.size()-1, String.valueOf(Float.valueOf(b.get(b.size()-1)) / Float.valueOf(a[i+1]))) ;
                i++;
            }

            else
                b.add(a[i]);
        }

        return b;
    }

    private ArrayList<String> SecondProcess(ArrayList<String> b){
        ArrayList<String> c = new ArrayList<String>();

        for (int i=0;i<b.size();i++){
            if(b.get(i).charAt(0) == '+'){
                c.set(c.size()-1, String.valueOf(Float.valueOf(c.get(c.size()-1)) + Float.valueOf(b.get(i+1)))) ;
                i++;
            }
            else if(b.get(i).charAt(0) == '-'){
                c.set(c.size()-1, String.valueOf(Float.valueOf(c.get(c.size()-1)) - Float.valueOf(b.get(i+1)))) ;
                i++;
            }
            else
                c.add(b.get(i));
        }

        return c;
    }


    private boolean IsLastCharacterOperator(){
        if(resultString.length() == 0) return false;
        if(IsOperator(resultString.charAt(resultString.length()-1))) return true;
        return false;
    }

    private boolean IsOperator(char x){
        for (int i=0;i< operatorCharacters.length;i++){
            if(operatorCharacters[i] == x) return true;
        }
        return false;
    }
}