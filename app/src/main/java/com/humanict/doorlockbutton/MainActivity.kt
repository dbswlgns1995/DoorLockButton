package com.humanict.doorlockbutton

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var buttonList: List<Button>

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        randomBlink()
    }

    override fun onResume() {
        super.onResume()
        hideViews()
    }

    private fun hideViews() {
        window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun randomBlink(){
        val handler = Handler()
        val runnable = Runnable {
            while (true) {
                handler.postDelayed({
                    val randomNum = Random.nextInt(12)

                    buttonList[randomNum].setOnTouchListener { v, event ->
                        false
                    }

                    val downTime = SystemClock.uptimeMillis()
                    val eventTime = SystemClock.uptimeMillis()
                    val x = buttonList[randomNum].width /2f
                    val y = buttonList[randomNum].height /2f
                    val metaState = 0

                    val motionEvent = MotionEvent.obtain(
                        downTime,
                        eventTime,
                        MotionEvent.ACTION_DOWN,
                        x,
                        y,
                        metaState
                    )

                    val motionEvent2 = MotionEvent.obtain(
                        downTime + 800,
                        eventTime + 800,
                        MotionEvent.ACTION_UP,
                        x,
                        y,
                        metaState
                    )

                    buttonList[randomNum].dispatchTouchEvent(motionEvent)
                    buttonList[randomNum].dispatchTouchEvent(motionEvent2)
                }, 0)
                SystemClock.sleep(1000)
            }
        }
        Thread(runnable).start()
    }

    private fun initViews() {
        buttonList = listOf<Button>(
            bt1, bt2, bt3, bt4, bt5,
            bt6, bt7, bt8, bt9, bt10, bt0, bt11
        )
    }

}