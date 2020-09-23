package com.example.osid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.osid.DB.DBCONTROLLER;
import com.example.osid.GLOBAL.GLOBAL;
import com.example.osid.POJOs.User;

import java.util.ArrayList;

public class informacion_personal extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    ArrayList<EditText> editFields;
    EditText nameEdit;
    EditText lastName1Edit;
    EditText lastName2Edit;
    EditText ageEdit;
    EditText weightEdit;
    EditText basalEdit;
    EditText PGPUtxt;
    Button registerInfo;
    RadioGroup genderGroup;
    String gender;
    DBCONTROLLER dbcontroller;

    Boolean errors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbcontroller = new DBCONTROLLER(this);

        if(dbcontroller.GetUser()!=null)
        {
            Intent mainActivity = new Intent(this,MainActivity.class);
            startActivity(mainActivity);
        }

        setContentView(R.layout.infomacion_personal);
        nameEdit = findViewById(R.id.name_lbl);
        lastName1Edit = findViewById(R.id.lastName1_lbl);
        lastName2Edit = findViewById(R.id.lastName2_lbl);
        ageEdit = findViewById(R.id.age_lbl);
        weightEdit = findViewById(R.id.weight_lbl);
        basalEdit = findViewById(R.id.basal_lbl);
        PGPUtxt = findViewById(R.id.pgpu_lbl);


        //--------------GENERO-------------------------//
        genderGroup = findViewById(R.id.radioGroupID);
        genderGroup.setOnCheckedChangeListener(this);
        //---------------------------------------------//


        //----------------EDITFIELDS-------------------//
        editFields = new ArrayList<EditText>();
        editFields.add(nameEdit);
        editFields.add(lastName1Edit);
        editFields.add(lastName2Edit);
        editFields.add(ageEdit);
        editFields.add(weightEdit);
        editFields.add(basalEdit);
        //----------------------------------------------//
        registerInfo = findViewById(R.id.registerInfo_btn);



        registerInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                errors=false;
                for (int i=0; i<= editFields.size()-1; i++)
                {
                    if(isEmpty(editFields.get(i)))
                    {
                        setError(editFields.get(i), "Falta llenar este campo");
                        errors = true;
                    }
                }
                if(!errors)
                {
                    RegisterUser();
                    //Toast.makeText(informacion_personal.this, "vientos", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void RegisterUser()
    {
        User user = new User();
        user.setNombre(nameEdit.getText().toString());
        user.setEdad(Integer.parseInt(ageEdit.getText().toString()));
        user.setPeso(Integer.parseInt(weightEdit.getText().toString()));
        user.setPrimerApellido(lastName1Edit.getText().toString());
        user.setSegundoApellido(lastName2Edit.getText().toString());
        user.setBasal(Integer.parseInt(basalEdit.getText().toString()));
        user.setInsulinaRestante(300);
        user.setPGPU(Float.parseFloat(PGPUtxt.getText().toString()));

        if(gender == "Masculino")
            user.setGender(true);
        if(gender == "Femenino")
            user.setGender(false);

        dbcontroller.InsertUser(user);

        Intent mainActivity = new Intent(this,MainActivity.class);
        startActivity(mainActivity);
    }

    @Override
    public void onCheckedChanged(RadioGroup genderGroup, int i) {
        switch (i)
        {
            case R.id.masculinoButtonID:
                gender="Masculino";
                break;

            case R.id.femeninoButtonID:
                gender="Femenino";
                break;
        }
    }



    public static boolean isEmpty(EditText editText) {

        String input = editText.getText().toString().trim();
        return input.length() == 0;
    }

    public static void setError(EditText editText, String errorString) {
        editText.setError(errorString);
    }
}
