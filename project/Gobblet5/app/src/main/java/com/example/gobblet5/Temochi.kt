package com.example.gobblet5

class Temochi(val size:Int) {
    private var count:Int=3
    fun usePiece(){
        count-=1
    }

    fun plus(){
        if (count<3)
        count+=1
    }

    fun returnInf():Int{
        if (count>0){
            return size
        }
        return 0
    }

    fun returnCount():Int{
        return count
    }
}