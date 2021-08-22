package com.example.firstcode.other

import kotlin.reflect.KProperty

fun main() {
    println(data)

}

val data by later {
    "data"
}

val value by lazy {

}

class Later<T>(val block: () -> T){

    private var value: Any? = null

    operator fun getValue(any: Any?, prop: KProperty<*>): T{
        if (value == null){
            value = block()
        }
        return value as T
    }
}

fun <T> later(block: () -> T) = Later(block)
