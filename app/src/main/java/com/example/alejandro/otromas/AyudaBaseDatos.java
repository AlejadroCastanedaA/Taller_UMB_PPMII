package com.example.alejandro.otromas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vivis on 24/09/2016.
 */

public class AyudaBaseDatos extends SQLiteOpenHelper {

    private static final int VERSION_BASE = 1;

    private static final String NOMBRE_DE_LA_TABLA = "tabladatostitulo";

    private static final String _ID = "_ID";

    private static final String COLUMNA_DOCUMENTO="documento";

    private static final String COLUMNA_NOMBRE="nombre";

    private static final String COLUMNA_TELEFONO="telefono";

    //para crear la base mediante un string

    private final static String CREA_LA_BASE = "CREATE TABLE IF NOT EXISTS "+ NOMBRE_DE_LA_TABLA +"("
            //+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMNA_DOCUMENTO+" INTEGER, "
            +COLUMNA_NOMBRE+" TEXT, "
            +COLUMNA_TELEFONO+" INTEGER"
            +");";

    public AyudaBaseDatos(Context context) {
        super(context, NOMBRE_DE_LA_TABLA, null, VERSION_BASE);
    }

    @Override
    public void onCreate(SQLiteDatabase bd_Alejandro) {
        bd_Alejandro.execSQL(CREA_LA_BASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd_Alejandro, int i, int i1) {
        bd_Alejandro.execSQL("DROP TABLE IF EXISTS "+ NOMBRE_DE_LA_TABLA +";");
        onCreate(bd_Alejandro);
        bd_Alejandro.execSQL("INSERT INTO "+NOMBRE_DE_LA_TABLA+"("
                +COLUMNA_DOCUMENTO+", "+COLUMNA_NOMBRE+", "+COLUMNA_TELEFONO+") VALUES"
                +"(665473,'alejandro', 222222), "
                +"(256852,'juan', 555555), "
                +"(12345,'ivan', 4444444), "
                +"(465473,'daniela', 111111)"
                +";");
    }
}
