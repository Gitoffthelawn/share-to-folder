package lu.knaff.alain.share_to_folder.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "share_target")
data class ShareTarget(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "uri") val uri: String?,
    @ColumnInfo(name = "always") var always: Boolean
)
