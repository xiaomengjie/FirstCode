package com.example.firstcode.other

sealed class Parent(val name: String)

class SonOne: Parent("one")

class SonTwo: Parent("two")

fun getName(parent: Parent) = when(parent){
    is SonOne -> "one"
    is SonTwo -> "two"
}

fun main() {
}