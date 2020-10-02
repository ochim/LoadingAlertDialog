package com.example.sweetalertdialog2

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.library.SweetAlertDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.go_button).setOnClickListener({

            val dialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                    .apply {
                        getProgressHelper().setBarColor(Color.parseColor("#A5DC86"))
                        setTitleText("Loading")
                    }
            dialog.show()

            scope.launch {
                Thread.sleep(3 * 1000)
                runOnUiThread { dialog.cancel() }
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}