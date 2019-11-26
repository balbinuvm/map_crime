package com.example.map_crime;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import com.example.map_crime.AppExecutors;
import com.example.map_crime.CrimeEntity;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileReader;
import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.List;

@Database(entities = {CrimeEntity.class}, version = 2,  exportSchema = false)
@TypeConverters({Converters.class})
 public abstract class CrimeRoomDatabase extends RoomDatabase {
    private static CrimeRoomDatabase sInstance;

    public abstract CrimeDao crimeDao();

    public static final String DATABASE_NAME = "crime-database";
    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    private static volatile CrimeRoomDatabase INSTANCE;

    public static CrimeRoomDatabase getInstance(final Context context, final AppExecutors executors) {
        if (sInstance == null) {
            synchronized (CrimeRoomDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext(), executors);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     */
    private static CrimeRoomDatabase buildDatabase(final Context appContext,
                                                   final AppExecutors executors) {
        return Room.databaseBuilder(appContext, CrimeRoomDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.diskIO().execute(() -> {
                            // Add a delay to simulate a long-running operation
                            addDelay();
                            // Generate the data for pre-population
                            CrimeRoomDatabase database = CrimeRoomDatabase.getInstance(appContext, executors);
                            List<CrimeEntity> crimes = DataGenerator.generateCrimes();
                            //List<CommentEntity> comments =
                            //DataGenerator.generateCommentsForProducts(products);

                            insertData(database, crimes);
                            // notify that the database was created and it's ready to be used
                            database.setDatabaseCreated();
                        });
                    }
                })
                .addMigrations(MIGRATION_1_2)
                .build();
    }

    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true);
    }

    private static void insertData(final CrimeRoomDatabase database, final List<CrimeEntity> crimes) {
        // final List<CommentEntity> comments) {
        database.runInTransaction(() -> {
            database.crimeDao().insertAll(crimes);
        });
    }

    private static void addDelay() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored) {
        }
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }

    //Migration has error with insert where it can't find column crime type in crime table
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {

        @Override
        public void migrate(@NonNull SupportSQLiteDatabase crimedatabase) {
            crimedatabase.execSQL("CREATE VIRTUAL TABLE IF NOT EXISTS `crime_table` USING FTS4("
                    + "`rowid`,`Crime_Type` ,`Date_Occurred`,`Weapon`, `Latitude` ,`Longitude`)");
            crimedatabase.execSQL("INSERT INTO crime_table (`rowid`, `Crime_Type`, `Date_Occurred`, `Weapon`,`Latitude`,`Longitude`) "
                    + "SELECT `id`,`crime`, `date`, `weapon`,`latitude`,`longitude`FROM crime_table ");

        }
    };
}
//,INTEGER ,TEXT  ,DATE ,TEXT ,FLOAT FLOAT


    /* public class FeedReaderDbHelper extends SQLiteOpenHelper {
         // If you change the database schema, you must increment the database version.
         public static final int DATABASE_VERSION = 1;
         public static final String DATABASE_NAME = "FeedReader.db";
         private static final String SQL_CREATE_ENTRIES =
                 "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                         FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                         FeedReaderContract.FeedEntry.COLUMN_NAME_DATE + " TEXT," +
                         FeedReaderContract.FeedEntry.COLUMN_NAME_TYPE + " TEXT," +
                         FeedReaderContract.FeedEntry.COLUMN_NAME_WEAPON + " TEXT," +
                         FeedReaderContract.FeedEntry.COLUMN_NAME_LAT + " TEXT," +
                         FeedReaderContract.FeedEntry.COLUMN_NAME_LON + " TEXT)";

         private static final String SQL_DELETE_ENTRIES =
                 "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;

         public final class FeedReaderContract {
             // To prevent someone from accidentally instantiating the contract class,
             // make the constructor private.
             private FeedReaderContract() {
             }

              Inner class that defines the table contents
             public class FeedEntry implements BaseColumns {
                 public static final String TABLE_NAME = "Crimes";
                 public static final String COLUMN_NAME_DATE = "Date Occurred";
                 public static final String COLUMN_NAME_TYPE = "Crime Type";
                 public static final String COLUMN_NAME_WEAPON = "Weapon Desc";
                 public static final String COLUMN_NAME_LAT = "Latitude";
                 public static final String COLUMN_NAME_LON = "Longitude";
             }


         }


         public FeedReaderDbHelper(Context context) {
             super(context, DATABASE_NAME, null, DATABASE_VERSION);
         }

         public void onCreate(SQLiteDatabase db) {
             db.execSQL(SQL_CREATE_ENTRIES);
         }


         public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
             // This database is only a cache for online data, so its upgrade policy is
             // to simply to discard the data and start over
             db.execSQL(SQL_DELETE_ENTRIES);
             onCreate(db);
         }

         public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
             onUpgrade(db, oldVersion, newVersion);
         }
     } */





/*     FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(getContext());

     // Gets the data repository in write mode
     ;
     SQLiteDatabase db = dbHelper.getWritableDatabase();
     // Create a new map of values, where column names are the keys
     ContentValues values = new ContentValues();
    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE);
    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE);

     // Insert the new row, returning the primary key value of the new row
  //   long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values); */



/*
    //SQLiteDatabase db = dbHelper.getReadableDatabase();

    // Define a projection that specifies which columns from the database
// you will actually use after this query.
    String[] projection = {
            BaseColumns._ID,
            FeedReaderDbHelper.FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
            FeedReaderDbHelper.FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE
    };

    // Filter results WHERE "title" = 'My Title'
    String selection = FeedReaderDbHelper.FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " = ?";
    String[] selectionArgs = { "My Title" };

    // How you want the results sorted in the resulting Cursor
    String sortOrder =
            FeedReaderDbHelper.FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

    Cursor cursor = db.query(
            FeedReaderDbHelper.FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
    );

        List itemIds = new ArrayList<>();
    while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(FeedEntry._ID));
            itemIds.add(itemId);
        }
    cursor.close();*/



