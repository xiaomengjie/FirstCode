package com.example.firstcode.other

import java.lang.StringBuilder

class Dependency {

    val libraries = ArrayList<String>()

    fun implementation(lis: String){
        libraries.add(lis)
    }
}

fun dependencies(block: Dependency.() -> Unit): List<String>{
    val dependency = Dependency()
    dependency.block()
    return dependency.libraries
}

fun main() {
    /**
     * DSL：领域特定语言，编写看似脱离语法结构的代码，构建专有的语法结构（看起来不符合该语言的语法结构）
     *  Kotlin页支持DSL，如infix函数
     */
    // TODO: 2021/9/1 0001 Kotlin高阶函数实现dependencies
    dependencies {
        implementation("com.squareup.retrofit2:retrofit:2.9.0")
        implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    }.forEach {
        println(it)
    }

    val table = Table()
    table.tr {
        td { "Apple" }
        td { "Graph" }
        td { "Orange" }
    }
    table.tr {
        td { "Pear" }
        td { "Banana" }
        td { "Watermelon" }
    }
    println(table.html())

    println(table {
        tr {
            td { "Apple" }
            td { "Graph" }
            td { "Orange" }
        }
        tr {
            td { "Pear" }
            td { "Banana" }
            td { "Watermelon" }
        }
    })
}

fun table(block: Table.() -> Unit): String{
    val table = Table()
    table.block()
    return table.html()
}


class Td{
    var content: String = ""

    // TODO: 2021/9/1 0001 转义符\n：换行，\t：缩进
    fun html() = "\n\t\t<td>$content</td>"
}

class Tr{
    private val children = ArrayList<Td>()

    fun td(block: Td.() -> String){
        val td = Td()
        td.content = td.block()
        children.add(td)
    }

    fun html(): String{
        val builder = StringBuilder()
        builder.append("\n\t<tr>")
        for (childTag in children){
            builder.append(childTag.html())
        }
        builder.append("\n\t</tr>")
        return builder.toString()
    }
}

class Table{
    private val children = ArrayList<Tr>()

    fun tr(block: Tr.() -> Unit){
        val tr = Tr()
        tr.block()
        children.add(tr)
    }

    fun html(): String{
        val builder = StringBuilder()
        builder.append("<table>")
        for (childTag in children){
            builder.append(childTag.html())
        }
        builder.append("\n</table>")
        return builder.toString()
    }
}