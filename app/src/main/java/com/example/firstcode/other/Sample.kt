package com.example.firstcode.other

fun main() {
    val lambda = add({
        println(it)
    }, {
        it
    })
    }

inline fun add(block1: (String) -> Unit, noinline block2: (Int) -> Int): (Int) -> Int{
    block1("name")
    return block2
}