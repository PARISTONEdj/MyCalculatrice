package com.example.mycalculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.ValidationResult;
import net.objecthunter.exp4j.operator.Operator;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView input, signBox, data1;
    String sign, value1, value2;
    Double num1, num2, result;
    boolean hasDot;
    private DatabaseHelper databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (TextView) findViewById(R.id.input);
        signBox = (TextView) findViewById(R.id.sign);
        data1 = (TextView) findViewById(R.id.rep1);

        databaseHelper = new DatabaseHelper(this);

        hasDot = false;

        displayData();
    }

    @SuppressLint("SetTextI18n")
    public void btnClick_0(View view) {
        input.append("0");
    }

    @SuppressLint("SetTextI18n")
    public void btnClick_1(View view) {
        input.append("1");
        ;
    }

    @SuppressLint("SetTextI18n")
    public void btnClick_2(View view) {
        input.append("2");

    }

    @SuppressLint("SetTextI18n")
    public void btnClick_3(View view) {
        input.append("3");

    }

    @SuppressLint("SetTextI18n")
    public void btnClick_4(View view) {
        input.append("4");

    }

    @SuppressLint("SetTextI18n")
    public void btnClick_5(View view) {
        input.append("5");

    }

    @SuppressLint("SetTextI18n")
    public void btnClick_6(View view) {
        input.append("6");

    }

    @SuppressLint("SetTextI18n")
    public void btnClick_7(View view) {
        input.append("7");

    }

    @SuppressLint("SetTextI18n")
    public void btnClick_8(View view) {
        input.append("8");

    }

    @SuppressLint("SetTextI18n")
    public void btnClick_9(View view) {
        input.append("9");

    }

    @SuppressLint("SetTextI18n")
    public void btnClick_dot(View view) {
        if (!hasDot) {
            if (input.getText().equals("")) {

                input.setText("0.");
            } else {

                input.setText(input.getText() + ".");
            }

            hasDot = true;
        }

    }

    public void btnClick_add(View view) {
        sign = "+";
        if (value1 == null) {
            value1 = input.getText().toString();
        }
        input.setText(input.getText() + "+");
        signBox.setText("+");
        hasDot = false;
    }

    public void btnClick_subtract(View view) {
        sign = "-";
        if (value1 == null) {
            value1 = input.getText().toString();
        }
        input.setText(input.getText() + "-");
        signBox.setText("-");
        hasDot = false;
    }

    public void btnClick_multiply(View view) {
        sign = "*";
        if (value1 == null) {
            value1 = input.getText().toString();
        }
        input.setText(input.getText() + "×");
        signBox.setText("×");
        hasDot = false;
    }

    public void btnClick_divide(View view) {
        sign = "/";
        if (value1 == null) {
            value1 = input.getText().toString();
        }
        input.setText(input.getText() + "÷");
        signBox.setText("÷");
        hasDot = false;
    }


    public void btnClick_power(View view) {
        sign = "power";
        if (value1 == null) {
            value1 = input.getText().toString();
        }
        input.setText(input.getText() + "xⁿ");
        signBox.setText("xⁿ");
        hasDot = false;
    }

    public void btnClick_root(View view) {
        sign = "root";
        input.setText(null);
        hasDot = false;
        signBox.setText("√");
    }

    public void btnClick_delete(View view) {
        if (input.getText().equals("")) {
            input.setText(null);
        } else {
            int len = input.getText().length();
            String s = input.getText().toString();
            if (s.charAt(len - 1) == '.') {
                hasDot = false;
                input.setText(input.getText().subSequence(0, input.getText().length() - 1));

            } else {
                input.setText(input.getText().subSequence(0, input.getText().length() - 1));
            }
        }
    }

    public void btnClick_clear(View view) {

        input.setText(null);
        signBox.setText(null);
        value1 = null;
        value2 = null;
        sign = null;
        hasDot = false;
    }

    @SuppressLint("SetTextI18n")
    public void btnClick_equal(View view) {


        if (input.getText().equals("")) {
            signBox.setText("Erreur!");
            return;
        }

        String expression = input.getText().toString();
        expression = expression.replace("×", "*");

        expression = expression.replace("÷", "/");

        System.out.println(expression);
        Toast.makeText(this, "ok"+expression, Toast.LENGTH_SHORT).show();


        try {
            Expression e = new ExpressionBuilder(expression)
                    .build();

            double result = e.evaluate();
            signBox.setText(String.valueOf(result));

            databaseHelper.insertData(input.getText().toString(), signBox.getText().toString());
            Toast.makeText(this, "insertion effectuer", Toast.LENGTH_SHORT).show();
            displayData();

        } catch (Exception ex) {
            signBox.setText("Erreur!");
        }


    }

   /* private void displayData() {
        List<String> dataList = databaseHelper.getAllData();
        StringBuilder stringBuilder = new StringBuilder();
        for (String data : dataList) {
            stringBuilder.append(data).append("\n");
        }
        data1.setText(stringBuilder.toString());

    }*/

    private void displayData() {
        List<String> dataList = databaseHelper.getAllData();
        StringBuilder stringBuilder = new StringBuilder();
        for (String data : dataList) {
            stringBuilder.append(data).append("\n");

            // Ajouter le trait de séparation après chaque élément
            stringBuilder.append("----------------------------------------------------------").append("\n");
        }
        data1.setText(stringBuilder.toString());
    }


}