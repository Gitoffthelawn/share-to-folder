package lu.knaff.alain.share_to_folder.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Update
import androidx.room.Transaction

@Dao
interface Dao {
    @Query("SELECT * FROM share_target")
    fun getAll(): MutableList<ShareTarget>

    @Query("SELECT * FROM share_target WHERE uri = :uri")
    fun loadByUri(uri: String): ShareTarget?

    @Query("SELECT * FROM share_target WHERE uid = :uid")
    fun loadByUid(uid: Int): ShareTarget?

    @Insert
    fun insertAll(vararg targets: ShareTarget)

    @Update
    fun update(target: ShareTarget)

    @Delete
    fun delete(user: ShareTarget)

    @Transaction
    fun getOrCreate(uri: String): ShareTarget {
	var st = loadByUri(uri)
	if(st == null) {
	    st = ShareTarget(0, uri, false)
	    insertAll(st)
	}
	return st
    }

    @Transaction
    fun setAlways(uri: String, always: Boolean) {
	val st = loadByUri(uri)
	if(st != null) {
	    st.always = always
	    update(st)
	}
    }
}
