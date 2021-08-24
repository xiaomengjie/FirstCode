package com.example.firstcode.other

import kotlin.concurrent.thread

fun main() {
    /**
     * 泛型的逆变：
     *      泛型类Sample<T>，A是B的子类型，同时Sample<B>是Sample<A>的子类型
     *      那么Sample在T这个泛型上是逆变的
     * 如何支持逆变
     *      泛型类型T用in修饰，此时T只能出现在输入位，支持逆变
     */
    val transformer = object : Transformer<Person>{
        override fun transform(t: Person): String {
            return t.name
        }
    }
    handleTransformer(transformer)

    fill(arrayOfNulls<Person>(1), Teacher("Tom", 10))
}

interface Transformer<in T>{
    fun transform(t: T): String
}

fun handleTransformer(transformer: Transformer<Student>){
    transformer.transform(Student("Tom", 19))
}

open class Person(val name: String, val age: Int)
class Student(name: String, age: Int): Person(name, age)
class Teacher(name: String, age: Int): Person(name, age)

fun fill(array: Array<in Teacher>, teacher: Teacher){
    array[0] = teacher
}

fun <T> copy(out: Array<out T>, put: Array<in T>){
    out.forEachIndexed { index, t ->
        put[index] = t
    }
}


