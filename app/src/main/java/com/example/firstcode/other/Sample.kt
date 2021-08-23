package com.example.firstcode.other

fun main() {
    /**
     * infix函数：Kotlin提供的一种语法糖，可以用简便方式调用此函数
     * 自定义infix函数beginWith，它的调用方法是
     * 限制：
     *  1、infix必须是某个类的成员函数，可以使用扩展函数定义在某个类中
     *  2、infix必须且只能接收一个参数
     */
    //通常函数调用
    "data".beginWith("d")
    //infix函数的调用
    "data" beginWith "d"

    listOf("data") has "data"
}

infix fun String.beginWith(prefix: String) = startsWith(prefix, true)

infix fun <T> Collection<T>.has(element: T) = contains(element)