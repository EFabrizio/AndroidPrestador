package txsec.com.prestador;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;


public class Agregar extends AppCompatActivity {

    private Button button;
    private EditText objeto;
    private EditText persona;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        button = (Button)findViewById(R.id.button2);
        objeto = (EditText) findViewById(R.id.editText);
        persona = (EditText) findViewById(R.id.editText2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent();
                intent.putExtra("Objeto",objeto.getText().toString());
                intent.putExtra("Persona",persona.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }

        });
    }
}
