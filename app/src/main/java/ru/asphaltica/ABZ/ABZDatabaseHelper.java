package ru.asphaltica.ABZ;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ABZDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Recepts"; //Имя базы данных
    private static final int DB_VERSION = 3; //Версия базы данных

    ABZDatabaseHelper (Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {

        updateMyDatabaze(MyDatabase, 0, DB_VERSION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int oldVersion, int newVersion) {

        MyDatabase.execSQL("DROP TABLE ZERN_SOSTAV");
        updateMyDatabaze(MyDatabase, oldVersion, newVersion);

    }

    private void updateMyDatabaze (SQLiteDatabase MyDatabase, int oldVersion, int newVersion) {

        MyDatabase.execSQL("CREATE TABLE ZERN_SOSTAV ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "BUNKER1SITO1 REAL, " //Бункер №1
                + "BUNKER1SITO2 REAL, "
                + "BUNKER1SITO3 REAL, "
                + "BUNKER1SITO4 REAL, "
                + "BUNKER1SITO5 REAL, "
                + "BUNKER1SITO6 REAL, "
                + "BUNKER1SITO7 REAL, "
                + "BUNKER1SITO8 REAL, "
                + "BUNKER1SITO9 REAL, "
                + "BUNKER1SITO10 REAL, "
                + "BUNKER1SITO11 REAL, "
                + "BUNKER1SITO12 REAL, "
                + "BUNKER2SITO1 REAL, " //Бункер №2
                + "BUNKER2SITO2 REAL, "
                + "BUNKER2SITO3 REAL, "
                + "BUNKER2SITO4 REAL, "
                + "BUNKER2SITO5 REAL, "
                + "BUNKER2SITO6 REAL, "
                + "BUNKER2SITO7 REAL, "
                + "BUNKER2SITO8 REAL, "
                + "BUNKER2SITO9 REAL, "
                + "BUNKER2SITO10 REAL, "
                + "BUNKER2SITO11 REAL, "
                + "BUNKER2SITO12 REAL, "
                + "BUNKER3SITO1 REAL, " //Бункер №3
                + "BUNKER3SITO2 REAL, "
                + "BUNKER3SITO3 REAL, "
                + "BUNKER3SITO4 REAL, "
                + "BUNKER3SITO5 REAL, "
                + "BUNKER3SITO6 REAL, "
                + "BUNKER3SITO7 REAL, "
                + "BUNKER3SITO8 REAL, "
                + "BUNKER3SITO9 REAL, "
                + "BUNKER3SITO10 REAL, "
                + "BUNKER3SITO11 REAL, "
                + "BUNKER3SITO12 REAL, "
                + "BUNKER4SITO1 REAL, " //Бункер №4
                + "BUNKER4SITO2 REAL, "
                + "BUNKER4SITO3 REAL, "
                + "BUNKER4SITO4 REAL, "
                + "BUNKER4SITO5 REAL, "
                + "BUNKER4SITO6 REAL, "
                + "BUNKER4SITO7 REAL, "
                + "BUNKER4SITO8 REAL, "
                + "BUNKER4SITO9 REAL, "
                + "BUNKER4SITO10 REAL, "
                + "BUNKER4SITO11 REAL, "
                + "BUNKER4SITO12 REAL, "
                + "BUNKER5SITO1 REAL, " //Бункер №5
                + "BUNKER5SITO2 REAL, "
                + "BUNKER5SITO3 REAL, "
                + "BUNKER5SITO4 REAL, "
                + "BUNKER5SITO5 REAL, "
                + "BUNKER5SITO6 REAL, "
                + "BUNKER5SITO7 REAL, "
                + "BUNKER5SITO8 REAL, "
                + "BUNKER5SITO9 REAL, "
                + "BUNKER5SITO10 REAL, "
                + "BUNKER5SITO11 REAL, "
                + "BUNKER5SITO12 REAL, "
                + "BUNKER6SITO1 REAL, " //Бункер №6
                + "BUNKER6SITO2 REAL, "
                + "BUNKER6SITO3 REAL, "
                + "BUNKER6SITO4 REAL, "
                + "BUNKER6SITO5 REAL, "
                + "BUNKER6SITO6 REAL, "
                + "BUNKER6SITO7 REAL, "
                + "BUNKER6SITO8 REAL, "
                + "BUNKER6SITO9 REAL, "
                + "BUNKER6SITO10 REAL, "
                + "BUNKER6SITO11 REAL, "
                + "BUNKER6SITO12 REAL, "
                + "BUNKER7SITO1 REAL, " //Бункер №7 - Минеральный порошок
                + "BUNKER7SITO2 REAL, "
                + "BUNKER7SITO3 REAL, "
                + "BUNKER7SITO4 REAL, "
                + "BUNKER7SITO5 REAL, "
                + "BUNKER7SITO6 REAL, "
                + "BUNKER8SITO1 REAL, " //Бункер №8 - Собственная пыль
                + "BUNKER8SITO2 REAL, "
                + "BUNKER8SITO3 REAL, "
                + "BUNKER8SITO4 REAL, "
                + "BUNKER8SITO5 REAL, "
                + "BUNKER8SITO6 REAL, "

                + "DOZA1 REAL, "
                + "DOZA2 REAL, "
                + "DOZA3 REAL, "
                + "DOZA4 REAL, "
                + "DOZA5 REAL, "
                + "DOZA6 REAL, "
                + "DOZAMP REAL, "
                + "DOZASZ REAL); "




                //+ "SOD1 REAL, " //85
               /* + "SOD2 REAL, "
                + "SOD3 REAL, "
                + "SOD4 REAL, "
                + "SOD5 REAL, "
                + "SOD6 REAL, "
                + "SODMP REAL, "
                + "SODSZ REAL, "
                + "TARGETSITO1 REAL, " //93
                + "TARGETSITO2 REAL, "
                + "TARGETSITO3 REAL, "
                + "TARGETSITO4 REAL, "
                + "TARGETSITO5 REAL, "
                + "TARGETSITO6 REAL, "
                + "TARGETSITO7 REAL, "
                + "TARGETSITO8 REAL, "
                + "TARGETSITO9 REAL, "
                + "TARGETSITO10 REAL, "*/
               // + "TARGETSITO11 REAL); "

        );

        //Блок вставки в базу данных начальных значений
        ContentValues StartValues = new ContentValues();

        StartValues.put("BUNKER1SITO1", 324);
        StartValues.put("BUNKER1SITO2", 123);
        StartValues.put("BUNKER1SITO3", 452);
        StartValues.put("BUNKER1SITO4", 231);
        StartValues.put("BUNKER1SITO5", 256);
        StartValues.put("BUNKER1SITO6", 278);
        StartValues.put("BUNKER1SITO7", 234);
        StartValues.put("BUNKER1SITO8", 261);
        StartValues.put("BUNKER1SITO9", 179);
        StartValues.put("BUNKER1SITO10", 125);
        StartValues.put("BUNKER1SITO11", 223);
        StartValues.put("BUNKER1SITO12", 177);

        StartValues.put("BUNKER2SITO1", 32);
        StartValues.put("BUNKER2SITO2", 12);
        StartValues.put("BUNKER2SITO3", 45);
        StartValues.put("BUNKER2SITO4", 23);
        StartValues.put("BUNKER2SITO5", 25);
        StartValues.put("BUNKER2SITO6", 27);
        StartValues.put("BUNKER2SITO7", 23);
        StartValues.put("BUNKER2SITO8", 26);
        StartValues.put("BUNKER2SITO9", 17);
        StartValues.put("BUNKER2SITO10", 12);
        StartValues.put("BUNKER2SITO11", 22);
        StartValues.put("BUNKER2SITO12", 17);

        StartValues.put("BUNKER3SITO1", 323);
        StartValues.put("BUNKER3SITO2", 123);
        StartValues.put("BUNKER3SITO3", 453);
        StartValues.put("BUNKER3SITO4", 233);
        StartValues.put("BUNKER3SITO5", 253);
        StartValues.put("BUNKER3SITO6", 273);
        StartValues.put("BUNKER3SITO7", 233);
        StartValues.put("BUNKER3SITO8", 263);
        StartValues.put("BUNKER3SITO9", 173);
        StartValues.put("BUNKER3SITO10", 123);
        StartValues.put("BUNKER3SITO11", 223);
        StartValues.put("BUNKER3SITO12", 173);

        StartValues.put("BUNKER4SITO1", 324);
        StartValues.put("BUNKER4SITO2", 124);
        StartValues.put("BUNKER4SITO3", 454);
        StartValues.put("BUNKER4SITO4", 234);
        StartValues.put("BUNKER4SITO5", 254);
        StartValues.put("BUNKER4SITO6", 274);
        StartValues.put("BUNKER4SITO7", 234);
        StartValues.put("BUNKER4SITO8", 264);
        StartValues.put("BUNKER4SITO9", 174);
        StartValues.put("BUNKER4SITO10", 124);
        StartValues.put("BUNKER4SITO11", 224);
        StartValues.put("BUNKER4SITO12", 174);

        StartValues.put("BUNKER5SITO1", 325);
        StartValues.put("BUNKER5SITO2", 125);
        StartValues.put("BUNKER5SITO3", 455);
        StartValues.put("BUNKER5SITO4", 235);
        StartValues.put("BUNKER5SITO5", 255);
        StartValues.put("BUNKER5SITO6", 275);
        StartValues.put("BUNKER5SITO7", 235);
        StartValues.put("BUNKER5SITO8", 265);
        StartValues.put("BUNKER5SITO9", 175);
        StartValues.put("BUNKER5SITO10", 125);
        StartValues.put("BUNKER5SITO11", 225);
        StartValues.put("BUNKER5SITO12", 175);

        StartValues.put("BUNKER6SITO1", 326);
        StartValues.put("BUNKER6SITO2", 126);
        StartValues.put("BUNKER6SITO3", 456);
        StartValues.put("BUNKER6SITO4", 236);
        StartValues.put("BUNKER6SITO5", 256);
        StartValues.put("BUNKER6SITO6", 276);
        StartValues.put("BUNKER6SITO7", 236);
        StartValues.put("BUNKER6SITO8", 266);
        StartValues.put("BUNKER6SITO9", 176);
        StartValues.put("BUNKER6SITO10", 126);
        StartValues.put("BUNKER6SITO11", 226);
        StartValues.put("BUNKER6SITO12", 176);

        StartValues.put("BUNKER7SITO1", 327);
        StartValues.put("BUNKER7SITO2", 127);
        StartValues.put("BUNKER7SITO3", 457);
        StartValues.put("BUNKER7SITO4", 237);
        StartValues.put("BUNKER7SITO5", 257);
        StartValues.put("BUNKER7SITO6", 277);

        StartValues.put("BUNKER8SITO1", 328);
        StartValues.put("BUNKER8SITO2", 128);
        StartValues.put("BUNKER8SITO3", 458);
        StartValues.put("BUNKER8SITO4", 238);
        StartValues.put("BUNKER8SITO5", 258);
        StartValues.put("BUNKER8SITO6", 278);
        StartValues.put("DOZA1", 1);
        StartValues.put("DOZA2", 2);
        StartValues.put("DOZA3", 3);
        StartValues.put("DOZA4", 4);
        StartValues.put("DOZA5", 5);
        StartValues.put("DOZA6", 6);
        StartValues.put("DOZAMP", 7);
        StartValues.put("DOZASZ", 8);






       // StartValues.put("SOD1", 1);
       /* StartValues.put("SOD2", 2);
        StartValues.put("SOD3", 3);
        StartValues.put("SOD4", 4);
        StartValues.put("SOD5", 5);
        StartValues.put("SOD6", 6);
        StartValues.put("SODMP", 7);
        StartValues.put("SODSZ", 8);

        StartValues.put("TARGETSITO1", 0);
        StartValues.put("TARGETSITO2", 0);
        StartValues.put("TARGETSITO3", 0);
        StartValues.put("TARGETSITO4", 0);
        StartValues.put("TARGETSITO5", 0);
        StartValues.put("TARGETSITO6", 0);
        StartValues.put("TARGETSITO7", 0);
        StartValues.put("TARGETSITO8", 0);
        StartValues.put("TARGETSITO9", 0);
        StartValues.put("TARGETSITO10", 0);
        StartValues.put("TARGETSITO11", 0);*/







        MyDatabase.insert("ZERN_SOSTAV", null, StartValues);
        //Конец блока вставки в базу данных начальных значений


    }
}
