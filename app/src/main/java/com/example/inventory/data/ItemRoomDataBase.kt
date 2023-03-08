package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


//Pour q Room puisse créer la bdd, contenir plusieurs arguments
@Database(entities = [Item::class], version = 1, exportSchema = false)
//classe abstraite qui étend Roomdata...
abstract class ItemRoomDataBase : RoomDatabase() {

    //la bdd doit connaître le DAO, fonction abstraite
    abstract fun itemDao() : ItemDao

    //objet companion: permet accèder aux méthodes de création ou obtention de la bdd en utilisant le nom de la classe qualificatif
    companion object{
        //déclarion d'une instance
        //volatile: indique q la valeur de la variable est tjs à jour et identique pour tous les threads d'éxéc
        @Volatile
        private var INSTANCE : ItemRoomDataBase?=null

        //Méthode avec pramètre, dont l'outil de création de bdd aura besoin
        fun getDatabase(context: Context):ItemRoomDataBase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext, ItemRoomDataBase::class.java,"item_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
