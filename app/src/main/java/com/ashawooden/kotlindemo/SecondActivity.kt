package com.ashawooden.kotlindemo

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    lateinit var fm: FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity_second)

        if (savedInstanceState == null) {
            val frag = FirstFragment.instance
            fm = supportFragmentManager
            fm.beginTransaction().add(R.id.fm1, frag, null).commit()
        }
    }
}
