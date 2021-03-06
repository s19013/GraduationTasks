package com.game.gobblet5

import android.widget.ImageView

class Mas(private val name:String, ) {
    private var list= mutableListOf<Int>(0,0,0) // [小､中､大]
    private var score = 0 //コンピューターが使う評価値みたいなもの

    private val humanPiece =  1
    private val comPiece   = -1
    private val empty = 0

    private val bigPiece   = 3
    private val middlePiece= 2
    private val smallPiece = 1

    private var view: ImageView? = null //描画する場所


    fun nameGetter():String{ return name }

    fun scoreGetter():Int{ return score }

    fun setView(v:ImageView){view=v}

    fun getView():ImageView?{return view}

    fun addScore(arg:Int){ score+=arg } //スコアに引数の値を足す

    //取り出す
    fun mPickup(turn:Int):Int{
        when(list.lastIndexOf(turn)){
            0-> if (list[1]== empty && list[2]== empty ){ return smallPiece } //中､大コマが被せて無い時だけ小さいコマを取れる
            1-> if (list[2]== empty ){return middlePiece } // 大きいコマが被せて無い時だけ中コマを取れる
            2-> return bigPiece
            else-> return empty
        }
        return empty
    }

    //入れる
    fun mInsert(size:Int?,turn: Int):Boolean{
        when(size){
            bigPiece ->
            {
                if (list[2] == empty) {
                    list[2] = turn //指定した場所が空っぽだったら入れる
                    return true
                }
            }
            middlePiece ->
            {
                if (list[2] == empty && list[1] == empty){
                    list[1] = turn //指定した場所と前のすべてが空っぽだったら入れる
                    return true
                }
            }
            smallPiece ->
            {
                if (list[2] == empty && list[1] == empty && list[0] == empty ) {
                    list[0] = turn //指定した場所と前のすべてが空っぽだったら入れる
                    return true
                }
            }
        }
        return false
    }

    fun returnLastElement():Int{
        //後ろからコマがあるかどうか調べる
        //とにかくこのマスの中にコマがあるか
        for (i in 2 downTo 0){
            if (list[i] != empty){ return list[i] } //最初に見つけた要素を返す
        }
        return empty //マスの中に何もなかったら空っぽと返す
    }

    //指定された大きさの部分を取り出して空にする
    fun resetList(size: Int){ list[size-1] = empty }

    //このマスは人間のものか答える
    fun OccupiedByTheHuman():Boolean{ return returnLastElement() == humanPiece }

    //このマスはコンピューターのものか答える
    fun OccupiedByTheCom():Boolean{ return returnLastElement() == comPiece }

    //スコアを0に戻す
    fun resetScore(){ score = 0 }

    fun funcForDisplay():MutableList<Int>{
        //大きさ 小:1 中:2 大:3
        //1p:1 2p:-1
        for (i in 2 downTo 0){
            if (list[i] != empty){ return mutableListOf(i+1,list[i]) } //[大きさ､1pのか2pのか]
        }
        return mutableListOf(0,0)
    }


}