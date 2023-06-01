package com.team48.project4

import android.Manifest.permission.CAMERA
import android.Manifest.permission.RECORD_AUDIO
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.util.*
import android.widget.ImageView
import android.view.View
import android.view.animation.AnimationUtils

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    // permissions
    private val permissions = arrayOf(RECORD_AUDIO, CAMERA)
    private val PERMISSIONS_REQUEST = 0x0000001

    init{
        instance = this
    }

    companion object{
        private var instance:MainActivity? = null
        fun getInstance(): MainActivity? {
            return instance
        }
    }

    fun showImage() {
        Log.d(TAG, "showImage")
        val imageView: ImageView = findViewById(R.id.imageView)
        val anim = AnimationUtils.loadAnimation(this, R.anim.blink_animation)
        imageView.startAnimation(anim)
        imageView.visibility = View.VISIBLE
    }

    fun hideImage() {
        Log.d(TAG, "hideImage")
        val imageView: ImageView = findViewById(R.id.imageView)
        imageView.clearAnimation()
        imageView.visibility = View.INVISIBLE
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView: ImageView = findViewById(R.id.imageView)
        imageView.visibility = View.INVISIBLE

        checkPermissions() // check permissions
    }

    private fun checkPermissions() {
        if (permissions.all{ActivityCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED}){
            Log.d(TAG, "All Permission Granted")
        }
        else{
            requestPermissions(permissions, PERMISSIONS_REQUEST)
        }
    }
}