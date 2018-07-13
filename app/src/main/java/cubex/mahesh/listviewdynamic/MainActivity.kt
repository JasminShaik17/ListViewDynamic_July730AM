package cubex.mahesh.listviewdynamic

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var status = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
        if(status==PackageManager.PERMISSION_GRANTED){
            readFiles()
        }else{
            ActivityCompat.requestPermissions(this,
              arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    123)
        }
    } // onCreate( )

    fun readFiles( )
    {
        var path = "/storage/sdcard0/"
        var file = File(path)
        if(!file.exists()){
            path = "/storage/emulated/0/"
            file=File(path)
        }
        var values = file.list()  // file.delete( )
        var myadapter = ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice,
                values)
        lview.adapter = myadapter
    } // readFiles

    override fun onRequestPermissionsResult(requestCode: Int,
              permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode,
                permissions, grantResults)
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
            readFiles()
        }else{
            Toast.makeText(this,"U can't continue with App",
                    Toast.LENGTH_LONG).show()
        }
    }

} // MainActivity
