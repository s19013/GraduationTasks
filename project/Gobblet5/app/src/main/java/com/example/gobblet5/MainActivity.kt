package com.example.gobblet5

import android.content.Intent
import android.media.AudioAttributes
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    //音関係
    private lateinit var sp: SoundPool
    private var menuSelectSE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text =findViewById<View>(R.id.goPreGameWithComBtn)

        val logo  = findViewById<ImageView>(R.id.logoImg)


        if (Locale.getDefault().equals(Locale.JAPAN)){
            logo.setImageResource(R.drawable.logo_jp)
            Log.d("gobblet2", "jp")
        } else {
            logo.setImageResource(R.drawable.logo_en)
            Log.d("gobblet2", "en")
        }


        //共有プリファレンス
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        var SE =pref.getBoolean("SEOnOff", true)
        var BGM =pref.getBoolean("BGMOnOff", true)


        val audioAttributes = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build()
        } else { TODO("VERSION.SDK_INT < LOLLIPOP") }
        sp = SoundPool.Builder()
            .setAudioAttributes(audioAttributes)
            .setMaxStreams(1)
            .build()

        menuSelectSE = sp.load(this, R.raw.button, 1)

        fun playSound(status: Int){
            Log.d("gobblet2", "status:${status}")
            if (SE){
                when(status){
                    menuSelectSE -> sp.play(menuSelectSE, 1.0f, 1.0f, 1, 0, 1.0f)
                }
            }
        }


        goPreGameWithComBtn.setOnClickListener{
            playSound(menuSelectSE)
            val intent = Intent(this,preGameWithComActivity::class.java)
            startActivity(intent)
        }

        goPreGameWithManBtn.setOnClickListener {
            playSound(menuSelectSE)
            val intent = Intent(this,preGameWithManActivity::class.java)
            startActivity(intent)
        }

        goConfigBtn.setOnClickListener {
            playSound(menuSelectSE)
            val intent = Intent(this,ConfigActivity::class.java)
            startActivity(intent)
        }

        goTutorialBtn.setOnClickListener {
            playSound(menuSelectSE)
            val intent = Intent(this,SelectTutorialActivity::class.java)
            startActivity(intent)
        }

    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        Log.d("gobblet2", "KeyEvent:${event}")
        val code = event?.keyCode

        return super.dispatchKeyEvent(event)
    }
}