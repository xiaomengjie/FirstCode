package com.example.firstcode.chapter13.room

import androidx.room.*

// TODO: 2021/8/31 dao必须定义为接口
@Dao
interface PersonDao {
    
    @Insert
    fun insertPerson(person: Person): Long

    // TODO: 2021/8/31 与添加时的bean要为同一个
    @Update
    fun updatePerson(newPerson: Person)

    // TODO: 2021/8/31 query注解必须自己编写SQL语句
    //  如果使用非实体类参数增删改数据，也需要使用query注解自己编写SQL语句
    @Query("select * from Person")
    fun loadAllPerson(): List<Person>

    @Query("select * from Person where age > :age")
    fun loadPersonOlderThan(age: Int): List<Person>

    // TODO: 2021/8/31 与添加时的bean要为同一个
    @Delete
    fun deletePerson(person: Person)

    @Query("delete from Person where name = :name")
    fun deletePersonByName(name: String): Int

    @Query("delete from Person")
    fun deleteAllPerson()
}