package com.example.firstcode.chapter13.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

// TODO: 2021/8/31 database一定要定义成abstract
@Database(version = 3, entities = [Person::class, Book::class], exportSchema = false)
abstract class PersonDatabase: RoomDatabase() {

    // TODO: 2021/8/31 一定要定义获取dao的abstract方法
    //  具体实现由room底层实现
    abstract fun personDao(): PersonDao

    abstract fun bookDao(): BookDao

    companion object{

        private val MIGRATION_1_2 = object : Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("create table Book (id integer primary key" +
                        "autoincrement not null, name text not null, pages integer not null)")
            }
        }

        private val MIGRATION_2_3 = object : Migration(2, 3){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table Book add column author text not null default 'unknown'")
            }
        }

        private var instance: PersonDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): PersonDatabase{
            instance?.let {
                return it
            }
            // TODO: 2021/8/31 context一定要使用applicationContext 避免内存泄露
            return Room.databaseBuilder(
                context.applicationContext,
                PersonDatabase::class.java,
                "person_database")
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3).build().apply {
                    instance = this
                }
        }
    }
}