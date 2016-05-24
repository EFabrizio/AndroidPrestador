package txsec.com.prestador;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class Agregar extends AppCompatActivity {

    private Button button;
    private EditText objeto;
    private EditText persona;
    private TextView fechaPrestamo;
    private TextView fechaDevolucion;
    private TextView horaUno;
    private TextView horaDos;

    private Calendar myCalendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        button = (Button)findViewById(R.id.button2);
        objeto = (EditText) findViewById(R.id.editText);
        persona = (EditText) findViewById(R.id.editText2);
        horaUno = (TextView) findViewById(R.id.horaUno);
        horaUno.setKeyListener(null);
        horaUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Agregar.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        horaUno.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }


        });
        horaDos = (TextView) findViewById(R.id.horaDos);
        horaDos.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           // TODO Auto-generated method stub
                                           // TODO Auto-generated method stub
                                           Calendar mcurrentTime = Calendar.getInstance();
                                           int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                                           int minute = mcurrentTime.get(Calendar.MINUTE);
                                           TimePickerDialog mTimePicker;
                                           mTimePicker = new TimePickerDialog(Agregar.this, new TimePickerDialog.OnTimeSetListener() {
                                               @Override
                                               public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                                   horaDos.setText(selectedHour + ":" + selectedMinute);
                                               }
                                           }, hour, minute, true);//Yes 24 hour time
                                           mTimePicker.setTitle("Select Time");
                                           mTimePicker.show();
                                       }
                                   });

        myCalendar = Calendar.getInstance();

       final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };


        final DatePickerDialog.OnDateSetListener dateTwo = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelTwo();
            }

        };

        fechaDevolucion = (TextView) findViewById(R.id.fechaEntrega);
        fechaDevolucion.setKeyListener(null);
        fechaDevolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Agregar.this, dateTwo, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }


        });

        fechaPrestamo = (TextView) findViewById(R.id.fechaPrestamo);
        fechaPrestamo.setKeyListener(null);
        fechaPrestamo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Agregar.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }


        });

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

    private void updateLabel() {g

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        fechaPrestamo.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateLabelTwo() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        fechaDevolucion.setText(sdf.format(myCalendar.getTime()));
    }
}
