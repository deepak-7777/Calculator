package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton menuButton;
    private TextView tvDisplay;
    private String input = "";
    private boolean isResultDisplayed = false; // Flag to track result state

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);
        menuButton = findViewById(R.id.menu_button);

        menuButton.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(this, v, Gravity.END, 0, R.style.CustomPopupMenu);
            popupMenu.getMenuInflater().inflate(R.menu.my_menu, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                if (id == R.id.history) {
                    startActivity(new Intent(MainActivity.this, HistoryActivity.class));
                    return true;
                } else if (id == R.id.setting) {
                    startActivity(new Intent(MainActivity.this, SettingActivity.class));
                    return true;
                }
                return false;
            });

            popupMenu.show();
        });

        // Numbers
        int[] numberBtnIds = {
                R.id.btn0, R.id.btn00, R.id.btn1, R.id.btn2, R.id.btn3,
                R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8,
                R.id.btn9, R.id.btnDot
        };

        for (int id : numberBtnIds) {
            findViewById(id).setOnClickListener(this);
        }

        // Operators
        int[] operatorBtnIds = {
                R.id.btnPlus, R.id.btnMinus, R.id.btnMul, R.id.btnDiv, R.id.btnPercent
        };

        for (int id : operatorBtnIds) {
            findViewById(id).setOnClickListener(this);
        }

        // Special Buttons
        findViewById(R.id.btnAC).setOnClickListener(v -> {
            input = "";
            tvDisplay.setText("0");
            isResultDisplayed = false;
        });

        findViewById(R.id.btnDel).setOnClickListener(v -> {
            if (!input.isEmpty()) {
                input = input.substring(0, input.length() - 1);
                tvDisplay.setText(input.isEmpty() ? "0" : input);
            }
        });

        findViewById(R.id.btnPercent).setOnClickListener(v -> {
            if (!input.isEmpty()) {
                try {
                    double value = new ExpressionBuilder(input).build().evaluate();
                    value = value / 100;
                    input = String.valueOf(value);
                    tvDisplay.setText(input);
                } catch (Exception e) {
                    tvDisplay.setText("Error");
                }
            }
        });

        findViewById(R.id.btnEqual).setOnClickListener(v -> calculateResult());
    }

    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        String value = btn.getText().toString();

        // Replace symbols for exp4j
        switch (value) {
            case "×":
                value = "*";
                break;
            case "÷":
                value = "/";
                break;
            case "−":
                value = "-";
                break;
            case "%":
                value = "/100";
                break;
        }

        if (isResultDisplayed) {
            // Agar equal ke baad phir number dabaya to naya start kare
            if (Character.isDigit(value.charAt(0)) || value.equals(".")) {
                input = value;   // reset aur fresh input start
            } else {
                input += value;  // agar operator hai to continue kare
            }
            isResultDisplayed = false;
        } else {
            input += value;
        }

        tvDisplay.setText(input);
    }

    private void calculateResult() {
        try {
            Expression expression = new ExpressionBuilder(input).build();
            double result = expression.evaluate();

            String resultStr;
            if (result == (long) result) {
                resultStr = String.valueOf((long) result);
            } else {
                resultStr = String.valueOf(result);
            }

            tvDisplay.setText(resultStr);

            // History Save
            HistoryStorage.addHistory(input, resultStr);

            input = resultStr;
            isResultDisplayed = true;

        } catch (Exception e) {
            tvDisplay.setText("Error");
            input = "";
            isResultDisplayed = false;
        }
    }

}
