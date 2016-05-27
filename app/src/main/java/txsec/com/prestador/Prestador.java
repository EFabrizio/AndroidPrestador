package txsec.com.prestador;

import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;

import android.os.Bundle;


import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.TextView;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Prestador extends AppCompatActivity implements AdapterView.OnItemClickListener {


    private List<String> values;
    private ListView list;
    private List<String> dialogValue;
    private ArrayAdapter<String> adapter;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private DataBase base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestador);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        base = new DataBase(this);
        dialogValue = new ArrayList<String>();
        final Cursor cursor = base.selectData("Objetos");
        values = new ArrayList<String>();


        list = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                Calendar cal = Calendar.getInstance();
                // Here all your customization on the View;
                Cursor cursorOne = base.doQuery("SELECT * FROM Objetos");

                            while(cursorOne.moveToNext()){
                              String[] fechaSplit = cursorOne.getString(5).split("/");
                                String[] horaSPlit = cursorOne.getString(8).split(":");

                                if(cal.get(Calendar.DAY_OF_MONTH) >= Integer.parseInt(fechaSplit[1]) && cal.get(Calendar.HOUR) > Integer.parseInt(horaSPlit[0])
                                        && position == (Integer.parseInt(cursorOne.getString(0))-1))
                                view.setBackgroundColor(Color.RED);
                            }

                Cursor cursor = base.doQuery("SELECT * FROM Objetos WHERE entregado='1' ");
                while(cursor.moveToNext()){
                    if(position == (Integer.parseInt(cursor.getString(0))-1))
                        view.setBackgroundColor(Color.GREEN);
                }



                return view;
            }
        };
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

        final TextView totalText = (TextView) findViewById(R.id.texto);

        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.view2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Prestador.this, Agregar.class);
                final int result = 1;
                startActivityForResult(intent, result);
            }


        });

        int h = 0;
        while (cursor.moveToNext()){
            values.add("Objeto: " + cursor.getString(1) + ". Persona: " + cursor.getString(3));
            dialogValue.add(" Objecto: "+cursor.getString(1)+".\n Persona: "+cursor.getString(3)+".\n Caracteristicas: "+cursor.getString(2)+".\n " +
                    "Fecha Prestamo: " +cursor.getString(4)+".\n Hora Prestamo: "+cursor.getString(7)+".\n Fecha Entrega: "+cursor.getString(5)+".\n" +
                    " Hora Entrega: " +cursor.getString(8));
            h++;
        }



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String objeto = data.getStringExtra("Objeto");
        String persona = data.getStringExtra("Persona");
        String caracteristicas = data.getStringExtra("Caracteristicas");
        String fechaPrestamo = data.getStringExtra("fechaPrestamo");
        String fechaDevuelto = data.getStringExtra("fechaDevolucion");
        String horaPrestamo = data.getStringExtra("horaUno");
        String horaDevuelto = data.getStringExtra("horaDos");

        Calendar calendar = Calendar.getInstance();
        values.add("Objecto: "+objeto+". Persona: "+persona);
                       //" FechaPrestamo: "+fechaPrestamo+". HoraPrestamo: "+horaPrestamo+". FechaDevuelto: "+fechaDevuelto+". HoraDevuelto: "+horaDevuelto+".");

        adapter.notifyDataSetChanged();

    }
    //https://github.com/EFabrizio/AndroidPrestador.git
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_prestador, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, final View view, int position, final long id) {
        final int idItem = (int) id+1;
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setCancelable(true);
        dialog.setMessage(dialogValue.get(position));
        dialog.setPositiveButton("Finalizar Entrega "+idItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                view.setBackgroundColor(Color.GREEN);
                base.updateData(""+idItem,"Objetos");
            }
        });
        dialog.setNegativeButton("Cancelar", null);
        dialog.setTitle("Actividades");
                dialog.show();
    }


}


