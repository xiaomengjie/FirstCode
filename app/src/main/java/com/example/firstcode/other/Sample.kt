package com.example.firstcode.other

fun main() {
    /**
     * 静态方法
     */
    show()
    Sample.show()
    Single.show()
}

fun show(){}

class Sample {
    companion object{
        @JvmStatic
        fun show(){}
    }
}

object Single{
    fun show(){}
}