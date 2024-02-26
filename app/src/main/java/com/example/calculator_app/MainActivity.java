package com.example.calculator_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView res, sol;
    Button btn_c, btn_open_br, btn_close_br;
    Button btn_divide, btn_mul, btn_add, btn_minus, btn_equals;
    Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9;
    Button btn_ac, btn_dot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res= findViewById(R.id.res);
        sol= findViewById(R.id.sol);

        assignId(btn_c, R.id.btn_c);
        assignId(btn_open_br, R.id.btn_open_br);
        assignId(btn_close_br, R.id.btn_close_br);
        assignId(btn_divide, R.id.btn_divide);
        assignId(btn_mul, R.id.btn_mul);
        assignId(btn_add, R.id.btn_add);
        assignId(btn_minus, R.id.btn_minus);
        assignId(btn_equals, R.id.btn_equals);
        assignId(btn_0, R.id.btn_0);
        assignId(btn_1, R.id.btn_1);
        assignId(btn_2, R.id.btn_2);
        assignId(btn_3, R.id.btn_3);
        assignId(btn_4, R.id.btn_4);
        assignId(btn_5, R.id.btn_5);
        assignId(btn_6, R.id.btn_6);
        assignId(btn_7, R.id.btn_7);
        assignId(btn_8, R.id.btn_8);
        assignId(btn_9, R.id.btn_9);
        assignId(btn_ac, R.id.btn_ac);
        assignId(btn_dot, R.id.btn_dot);



    }

    void assignId(Button btn, int id)
    {
        btn= findViewById(id);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        Button btn= (Button) view;
        String btn_text = btn.getText().toString();
        String data_to_cal = sol.getText().toString();

        if(btn_text.equals("AC"))
        {
            sol.setText("");
            res.setText("0");
            return;
        }
        if(btn_text.equals("="))
        {
            sol.setText(res.getText());
            return;
        }
        if(btn_text.equals("C"))
        {
            data_to_cal = data_to_cal.substring(0, data_to_cal.length()-1);
        }
        else {
            data_to_cal = data_to_cal+btn_text;
        }
        sol.setText(data_to_cal);

        String final_res = get_res(data_to_cal);

        if(!final_res.equals("Err"))
        {
            res.setText(final_res);
        }
    }

    String get_res(String data)
    {
        try
        {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String final_res = context.evaluateString(scriptable,data,"Javascript", 1, null).toString();
            if(final_res.endsWith(".0"))
            {
                final_res = final_res.replace(".0", "");
            }
            return final_res;
        }
        catch (Exception e)
        {
            return "Err";
        }
    }
}