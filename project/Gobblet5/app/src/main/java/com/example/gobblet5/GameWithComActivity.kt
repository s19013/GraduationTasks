package com.example.gobblet5

import android.os.Bundle
import android.graphics.Color
import android.util.Log
import kotlinx.android.synthetic.main.activity_game_with_com.*

class GameWithComActivity : GameBaseClass() {
    override var thisAct: Int = -1

    val insertTimerId = "insertTimer"

    //コンピューター宣言
    val com:Com=Com()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_with_com)

        //初期化
        //共通な初期化
        iniStandard()
        //コンピューターの初期化
        iniCom()

        //ゲームを始める
        startTurn()
        Log.d("gobblet2", "pF:${playFirst}")





//手持ちのボタンを触った時
        //1p手持ち
        buttonTemochiRedBig!!.setOnClickListener {
            if (turn == 1){
                //movingSourceが同じときやり直しができる
                if (movingSource == stringTemochiRedBig){ resetTemochi() }
                //移動元が手持ちの場合のみコマを
                else if (movingSource== null ||
                    movingSource==stringTemochiRedMiddle||
                    movingSource==stringTemochiRedSmall){
                    pickupTemochi(temochiRedBig)
                }
            }
            else{toastNotYourTurn()}
        }

        buttonTemochiRedMiddle!!.setOnClickListener {
            if (turn == 1){
                if (movingSource==stringTemochiRedMiddle){ resetTemochi() }
                else if (movingSource== null ||
                    movingSource==stringTemochiRedBig ||
                    movingSource==stringTemochiRedSmall){
                    pickupTemochi(temochiRedMiddle)
                }
            }
            else{toastNotYourTurn()}
        }

        buttonTemochiRedSmall!!.setOnClickListener {
            if (turn == 1){
                if (movingSource==stringTemochiRedSmall){ resetTemochi() }
                else if (movingSource== null ||
                    movingSource==stringTemochiRedBig ||
                    movingSource==stringTemochiRedMiddle){
                    pickupTemochi(temochiRedSmall)
                }
            }
            else{toastNotYourTurn()}
        }

        //2p手持ち
        buttonTemochiGreenBig!!.setOnClickListener {
            if (turn == -1){
                if (movingSource==stringTemochiGreenBig){ resetTemochi() }
                else if (movingSource== null ||
                    movingSource==stringTemochiGreenMiddle||
                    movingSource==stringTemochiGreenSmall) {
                    pickupTemochi(temochiGreenBig)
                }
            }
            else{toastNotYourTurn()}
        }

        buttonTemochiGreenMiddle!!.setOnClickListener {
            if (turn == -1){
                if (movingSource==stringTemochiGreenMiddle){ resetTemochi() }
                else if (movingSource== null ||
                    movingSource==stringTemochiGreenBig ||
                    movingSource==stringTemochiGreenSmall){
                    pickupTemochi(temochiGreenMiddle)
                }
            }
            else{toastNotYourTurn()}
        }

        buttonTemochiGreenSmall!!.setOnClickListener {
            if (turn == -1){
                if (movingSource==stringTemochiGreenSmall){ resetTemochi() }
                else if (movingSource==null||
                    movingSource==stringTemochiGreenBig ||
                    movingSource==stringTemochiGreenMiddle){ pickupTemochi(temochiGreenSmall) }
            }
            else{toastNotYourTurn()}
        }


        ////マスを触ったとき
        buttonA1.setOnClickListener {
            //ゲームが終わったらさわれないようにする,相手のターン中に触れないようにするためにこんなif文を書く
            if (turn != 0){ pushedMasButton(A1) } //nameGetterを使ってマスの名前を入れる
            else{toastNotYourTurn()}
        }

        buttonA2.setOnClickListener {
            if (turn != 0){ pushedMasButton(A2) }
            else{toastNotYourTurn()}
        }

        buttonA3.setOnClickListener {
            if (turn != 0){ pushedMasButton(A3) }
            else{toastNotYourTurn()}
        }

        buttonA4.setOnClickListener {
            if (turn != 0){ pushedMasButton(A4) }
            else{toastNotYourTurn()}
        }

        buttonB1.setOnClickListener {
            if (turn != 0){ pushedMasButton(B1) }
            else{toastNotYourTurn()}
        }

        buttonB2.setOnClickListener {
            if (turn != 0){ pushedMasButton(B2) }
            else{toastNotYourTurn()}
        }

        buttonB3.setOnClickListener {
            if (turn != 0){ pushedMasButton(B3) }
            else{toastNotYourTurn()}
        }

        buttonB4.setOnClickListener {
            if (turn != 0){ pushedMasButton(B4) }
            else{toastNotYourTurn()}
        }

        buttonC1.setOnClickListener {
            if (turn != 0){pushedMasButton(C1) }
            else{toastNotYourTurn()}
        }

        buttonC2.setOnClickListener {
            if (turn != 0){ pushedMasButton(C2) }
            else{toastNotYourTurn()}
        }

        buttonC3.setOnClickListener {
            if (turn != 0){ pushedMasButton(C3) }
            else{toastNotYourTurn()}
        }

        buttonC4.setOnClickListener {
            if (turn != 0){ pushedMasButton(C4) }
            else{toastNotYourTurn()}
        }

        buttonD1.setOnClickListener {
            if (turn != 0){ pushedMasButton(D1) }
            else{toastNotYourTurn()}
        }

        buttonD2.setOnClickListener {
            if (turn != 0){ pushedMasButton(D2) }
            else{toastNotYourTurn()}
        }

        buttonD3.setOnClickListener {
            if (turn != 0){ pushedMasButton(D3) }
            else{toastNotYourTurn()}
        }

        buttonD4.setOnClickListener {
            if (turn != 0){ pushedMasButton(D4) }
            else{toastNotYourTurn()}
        }

        // その他
        configButton.setOnClickListener {
            playSound(openSE)
            showConfigPopup()
        }

        resaltButton!!.setOnClickListener {
            playSound(openSE)
            showResultPopup()
        }

    }


    //コンピューター関係
    fun iniCom(){
        //コンピューターにわたすよう?

        com.iniLines(
            mutableListOf<Mas>(A1, B1, C1, D1),//l1
            mutableListOf<Mas>(A2, B2, C2, D2),//l2
            mutableListOf<Mas>(A3, B3, C3, D3),//l3
            mutableListOf<Mas>(A4, B4, C4, D4),//l4
            mutableListOf<Mas>(A1, A2, A3, A4),//lA
            mutableListOf<Mas>(B1, B2, B3, B4),//lB
            mutableListOf<Mas>(C1, C2, C3, C4),//lC
            mutableListOf<Mas>(D1, D2, D3, D4),//lD
            mutableListOf<Mas>(A1, B2, C3, D4),//lS
            mutableListOf<Mas>(A4, B3, C2, D1),//lBS
        )
        com.iniConcatLine()
        com.iniTemochi(temochiGreenBig,temochiGreenMiddle,temochiGreenSmall)
    }

    fun startCom(){
        com.start()
        pickUpCom()
        nowDoingTimerID = insertTimerId
        handler.post(insertTimer)
        com.debScore()
        resetCom()
    }

    fun pickUpCom(){
        val rv = com.movingSourceGetter()

        when{
            rv is Mas     -> { pushedMasButton(rv) } //移動元がマスだった場合
            rv is Temochi -> { pickupTemochi(rv) } //移動元が手持ちだった場合
        }
    }

    fun insertCom(){ pushedMasButton(com.destinationGetter()!!) }

    fun resetCom(){
        com.resetScore()
        com.resetLists()
    }

    //ターン開始の処理
    override fun startTurn(){
        if (finished){turn = 0}
        when(turn){
            1 -> {
                telop1p.setBackgroundColor(Color.rgb(255, 173, 173))
                telop2p.setBackgroundColor(Color.rgb(230, 230, 230))
            }
            -1 -> {
                startCom()
                telop1p.setBackgroundColor(Color.rgb(230, 230, 230))
                telop2p.setBackgroundColor(Color.rgb(188, 255, 173))
            }
            0 -> {
                telop1p.setBackgroundColor(Color.rgb(230, 230, 230))
                telop2p.setBackgroundColor(Color.rgb(230, 230, 230))
            }
        }
    }

    //このアクティビティ内のviewを取得?

    override fun iniView() {
        //テキスト
        textTemochiRedBig=findViewById(R.id.textTemochiRedBig)
        textTemochiRedMiddle=findViewById(R.id.textTemochiRedMiddle)
        textTemochiRedSmall=findViewById(R.id.textTemochiRedSmall)
        textTemochiGreenBig=findViewById(R.id.textTemochiGreenBig)
        textTemochiGreenMiddle=findViewById(R.id.textTemochiGreenMiddle)
        textTemochiGreenSmall=findViewById(R.id.textTemochiGreenSmall)

        //一部ボタン
        buttonTemochiRedBig=findViewById(R.id.buttonTemochiRedBig)
        buttonTemochiRedMiddle=findViewById(R.id.buttonTemochiRedMiddle)
        buttonTemochiRedSmall=findViewById(R.id.buttonTemochiRedSmall)
        buttonTemochiGreenBig=findViewById(R.id.buttonTemochiGreenBig)
        buttonTemochiGreenMiddle=findViewById(R.id.buttonTemochiGreenMiddle)
        buttonTemochiGreenSmall=findViewById(R.id.buttonTemochiGreenSmall)
        resaltButton=findViewById(R.id.resaltButton)
    }

    private val insertTimer: Runnable = object : Runnable{
        override fun run() {
            time += millisecond
            handler.postDelayed(this,millisecond)
            if (time>1000L){
                insertCom()
                handler.removeCallbacks(this)
                time = 0L
                nowDoingTimerID = null
            }
        }
    }

    //タイマー再開
    override fun onResume() {
        super.onResume()
        when (nowDoingTimerID){insertTimerId -> handler.post(insertTimer) }
    }

    //タイマーを止める
    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(insertTimer)
    }

    //タイマーを止める
    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(insertTimer)
    }
}