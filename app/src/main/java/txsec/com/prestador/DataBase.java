package txsec.com.prestador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**@Autor Alexia Ariana Abrego Delgado
 * Created on 22/05/16.
 */
public class DataBase extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "Prestador.db";

    public DataBase(Context context){
        super(context,DATABASE_NAME,null,1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Objetos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre text,caracteristicas text," +
                        "persona text,fechaprestamo text,fechadevolucion text,entregado integer,horapresta text,horaentrega text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    /**
     *
     * @param tableName
     */
    public boolean insertData(String tableName,String nombre, String caracterisiticas, String persona, String fechaprestamo,
                              String fechaDevolucion, int entregado,String horapresta,String horaentrega){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
            contentValues.put("nombre",nombre);
        contentValues.put("caracteristicas",caracterisiticas);
        contentValues.put("persona",persona);
        contentValues.put("fechaprestamo",fechaprestamo);
        contentValues.put("fechadevolucion",fechaDevolucion);
        contentValues.put("entregado",entregado);
        contentValues.put("horapresta",horapresta);
        contentValues.put("horaentrega",horaentrega);

        long result = db.insert(tableName,null,contentValues);
        if(result == -1)
            return false;
            else
            return true;
    }

    /**
     *
     * @param tableName
     * @return
     */
    public Cursor selectData(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+tableName,null);
        return res;
    }

    public Cursor doQuery(String query){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(query,null);
        return res;
    }


    public void updateData(String id,String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("entregado", 1);
        db.update("Objetos", values, "id = ?", new String[]{id});
    }
}
