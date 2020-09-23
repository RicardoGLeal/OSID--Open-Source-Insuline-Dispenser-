package ws.wolfsoft.cryptostar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CreatePinAcitivyt extends AppCompatActivity implements View.OnClickListener {

    TextView one,two,three,four,five,six,seven,eight,nine,zero;
    ImageView delete;
    EditText edit_txt;
    private StringBuilder builder;
    int current = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pin_acitivyt);

        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);
        delete = findViewById(R.id.delete);
        edit_txt = findViewById(R.id.edit_txt);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txt = edit_txt.getText().toString();
                if (txt != null && txt.length() > 0 ){
                    txt = txt.substring(0, txt.length() - 1);
                }
                edit_txt.setText(txt);
                edit_txt.setSelection(edit_txt.getText().length());
            }
        });


        builder = new StringBuilder();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.one:

                builder.append("1");
                edit_txt.append("1");

                break;

            case R.id.two:

                builder.append("2");
                edit_txt.append("2");

                break;

            case R.id.three:

                builder.append("3");
                edit_txt.append("3");

                break;

            case R.id.four:

                builder.append("4");
                edit_txt.append("4");

                break;

            case R.id.five:

                builder.append("5");
                edit_txt.append("5");

                break;

            case R.id.six:

                builder.append("6");
                edit_txt.append("6");

                break;



            case R.id.seven:

                builder.append("7");
                edit_txt.append("7");

                break;


            case R.id.eight:

                builder.append("8");
                edit_txt.append("8");

                break;

            case R.id.nine:

                builder.append("9");
                edit_txt.append("9");

                break;


            case R.id.zero:

                builder.append("0");
                edit_txt.append("0");

                break;

        }

    }
}
