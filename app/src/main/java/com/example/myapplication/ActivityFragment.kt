package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ActivityFragment : AppCompatActivity() {
    private val fragmentManager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
    }

    fun onClickFirst(view: View) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frameLayout, FragmentA())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    fun onClickSecond(view: View) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frameLayout, FragmentB())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}