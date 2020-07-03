package com.example.vendedoresambulantes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Banco extends SQLiteOpenHelper {


    public Banco(@Nullable Context context) {
        super(context, "venda_ambulante", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql =
                "CREATE TABLE vendedores(" +
                "id integer primary key autoincrement," +
                "tipoProduto varchar(100) not null, " +
                "nomeVendedor varchar(100) not null," +
                "cidadeOrigem varchar(100) not null," +
                "diaSemana varchar(20) not null" +
                ")";
        try{
            sqLiteDatabase.execSQL(sql);
        } catch(Error e){
            System.out.println("Criando tabela: " + e.getMessage());
        }
    }

    public boolean insere(String tipoProduto, String nomeVendedor, String cidadeOrigem, String diaSemana) {
        boolean retorno = false;
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues registro = new ContentValues();
            registro.put("tipoProduto", tipoProduto);
            registro.put("nomeVendedor", nomeVendedor);
            registro.put("cidadeOrigem", cidadeOrigem);
            registro.put("diaSemana", diaSemana);
            long id = db.insert("vendedores", null, registro);
            db.close();

            if (id > 0) retorno = true;
        }catch(Error e){
            System.out.println("Inserindo registro: " + e.getMessage());
        }

        return retorno;

    }

    public String consulta(){

        String  resultado = "",
                tipoProd = "",
                nomeVendedor = "",
                cidadeOrigen = "",
                diaSemana = "";

        try{
            SQLiteDatabase db = this.getReadableDatabase();
            String sql = "SELECT * FROM vendedores";

            Cursor c = db.rawQuery(sql, null);

            if (c.moveToFirst()){
                do{
                    tipoProd = c.getString(1);
                    nomeVendedor = c.getString(2);
                    cidadeOrigen = c.getString(3);
                    diaSemana = c.getString(4);
                    resultado += tipoProd + "\n" + nomeVendedor + "\n" + cidadeOrigen + "\n" + diaSemana + "\n\n";
                }while(c.moveToNext());
            }
        }catch (Error e){
            e.getStackTrace();

        }
        return resultado;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
