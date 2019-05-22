package com.qautomatron.kopi.sample

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    private var parentLinearLayout: ConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        parentLinearLayout = findViewById(R.id.parent_layout)

        // Set same id for texts
        val sameIdText2 = findViewById<TextView>(R.id.sameId2)
        sameIdText2.id = R.id.sameId1
    }

    fun changeText(view: View) {
        val textView = findViewById<TextView>(R.id.message_text)
        textView.apply { text = getString(R.string.text_changed) }
    }

    fun removeText(view: View) {
        val button = findViewById<Button>(R.id.button_remove_text)
        button.isEnabled = false
        LongOperation().execute()
    }

    fun hideButton(view: View) {
        val button = findViewById<Button>(R.id.button_hide)
        button.visibility = INVISIBLE
    }

    inner class LongOperation : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg params: String): String {
            for (i in 0..3) {
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    Thread.interrupted()
                }
            }
            return "Executed"
        }

        override fun onPostExecute(result: String) {
            val textView = findViewById<TextView>(R.id.message_text)
            parentLinearLayout?.removeView(textView)
        }

        override fun onPreExecute() {}

        override fun onProgressUpdate(vararg values: Void) {}
    }
}

