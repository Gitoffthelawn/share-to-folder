package lu.knaff.alain.share_to_folder.db

import androidx.room.Room
import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [ShareTarget::class], version = 1, exportSchema = true)
abstract class TheDatabase : RoomDatabase() {
    abstract fun dao(): Dao

    // https://stackoverflow.com/questions/72048899/how-can-you-use-android-room-in-an-app-with-several-activities
    companion object {
        private var instance: TheDatabase? = null
        fun getInstance(context: Context): TheDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context,
						TheDatabase::class.java,
						"database.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return instance as TheDatabase
        }

	fun getDao(context: Context): Dao {
	    return getInstance(context).dao()
	}
    }
}
