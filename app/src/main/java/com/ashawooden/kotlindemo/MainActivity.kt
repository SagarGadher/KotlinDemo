package com.ashawooden.kotlindemo

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var mMessage = ""
        val b1 = Button(this)
        val b2 = Button(this)
        val b3 = Button(this)
        b1.text = "Go to the next activity"
        b2.text = "Search"
        b3.text = "Start Work"
        if (ll1.childCount > 0) {
            ll1.removeAllViews()
        }
        ll1.addView(b1)
        ll1.addView(b2)
        ll1.addView(b3)

        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Alert")
        alertDialogBuilder.setMessage("You are new to kotlin so be careful")
        alertDialogBuilder.setCancelable(false)
        alertDialogBuilder.setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which ->
            if (mMessage.isBlank())
                Toast.makeText(this, "enterd messege !", Toast.LENGTH_LONG).show()
            else {
                Toast.makeText(this, "messege is ${mMessage}", Toast.LENGTH_LONG).show()
            }
            dialog.cancel()
        })
        val alert = alertDialogBuilder.create()

        btnToast.setOnClickListener {
            mMessage = etMessage.text.toString().trim()
            //Toast.makeText(this, "Your Url: ${mMessage}", Toast.LENGTH_LONG).show()
            val snack = Snackbar.make(it, "This is it.", Snackbar.LENGTH_LONG)
            snack.setAction("Dissmiss", View.OnClickListener { alert.show() })
            snack.setActionTextColor(resources.getColor(R.color.colorPrimary))
            snack.view.setBackgroundColor(resources.getColor(android.R.color.white))
            val tv = snack.view.findViewById(android.support.design.R.id.snackbar_text) as TextView
            tv.setTextColor(resources.getColor(android.R.color.black))
            snack.show()
            //alert.show()
        }

        b1.setOnClickListener {
            val i = Intent(this, SecondActivity::class.java)/*.apply { putExtra("","").apply { startActivity(this) } }*/
            startActivity(i)
        }
        b2.setOnClickListener {
            mMessage = etMessage.text.toString().trim()
            if (mMessage.isBlank()) {
                Toast.makeText(this, "Enter Ur to Search!!", Toast.LENGTH_LONG).show()
            } else {
                val i = Intent(Intent.ACTION_VIEW, Uri.parse(mMessage))
                startActivity(i)
            }

        }
        b3.setOnClickListener {
            Thread(Runnable {

                runOnUiThread { pb.visibility = View.VISIBLE }
                try {
                    for (i in 1..5) {
                        Thread.sleep(1000)
                        Log.e("LOGValue", "hello$i");
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                runOnUiThread { pb.visibility = View.GONE }
            }).start()
        }
    }
}
