package com.example.firstcode.other

fun main() {
    /**
     * 运算符重载语法
     * class Obj{
     *     operator fun 运算符函数(param: 参数类型): 返回值类型{}
     * }
     */
    println(Sample("10") + Sample("20"))
    println(Sample("10") + "20")
}

class Sample(val name: String) {

    //Sample 加法运算
    operator fun plus(sample: Sample): String{
        return "$name, ${sample.name}"
    }

    operator fun plus(string: String): String{
        return "$name, $string"
    }
}