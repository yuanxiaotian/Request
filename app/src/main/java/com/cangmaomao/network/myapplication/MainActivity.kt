package com.cangmaomao.network.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.cangmaomao.network.request.HttpManage

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        HttpManage.getInstance().downFile()
    }
}
