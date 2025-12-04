package lu.knaff.alain.share_to_folder

import java.io.InputStreamReader
import java.io.BufferedReader

import android.os.Bundle
import android.content.Intent;
import android.util.Log;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import lu.knaff.alain.share_to_folder.databinding.ActivityMainBinding

class ShareReceiver : AppCompatActivity() {
    private val TAG="ShareReceiver"

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    //val contentResolver = applicationContext.contentResolver
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStart() {
	super.onStart()

	val i:Intent = getIntent()
	val uri:Uri?  = i.getData()
	if(uri != null) {
	    Log.i(TAG, "Received an intent with Uri "+i.getData());
	    val stringBuilder = StringBuilder()
	    contentResolver.openInputStream(uri)?.use { inputStream ->
		BufferedReader(InputStreamReader(inputStream)).use { reader ->
		    var line: String? = reader.readLine()
		    while (line != null) {
			stringBuilder.append(line)
			line = reader.readLine()
		    }
		}
	    }
	    Log.i(TAG, "Received string "+stringBuilder.toString())
	}
	// finish
	finish();
    }
}
