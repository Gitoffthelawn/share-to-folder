package lu.knaff.alain.share_to_folder

import java.io.InputStream
import java.io.OutputStream

import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.documentfile.provider.DocumentFile
import androidx.activity.result.contract.ActivityResultContracts

/**
 * This activity receives share requests from other applications
 */
class ShareReceiver : AppCompatActivity() {
    private val TAG="ShareReceiver"

    fun processPickDirectory(treeUri:Uri?) {
	val shareIntent:Intent=getIntent()
	val srcUri:Uri = shareIntent.getData()!!
	var filename:String? = srcUri.getLastPathSegment()
	if(filename != null) {
	    val pos=filename.lastIndexOf('/')
	    if(pos >= 0)
		filename=filename.substring(pos+1)
	} else {
	    filename="file"
	}
	var mimeType:String? = shareIntent.type
	if(mimeType=="null")
	    mimeType="text/plain"
	val inStream:InputStream =  contentResolver.openInputStream(srcUri)!!

	val dir:DocumentFile = DocumentFile.fromTreeUri(this, treeUri!!)!!
	val destFile:DocumentFile = dir
	    .createFile(mimeType!!,filename)!!

	val destUri:Uri = destFile.getUri()
	val outStream:OutputStream =
	    contentResolver.openOutputStream(destUri)!!
	
	// copy input to output
	inStream.transferTo(outStream);

	finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

	val launcher = registerForActivityResult(ActivityResultContracts.OpenDocumentTree()) {
	    result-> processPickDirectory(result)
	}
	launcher.launch(null)
    }
}
