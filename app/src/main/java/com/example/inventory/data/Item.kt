package com.example.inventory.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//classe de données @Entity et tableName pour définir item comme nom de table SQLite
@Entity(tableName = "item")

//Pour convertir la classe Item en classe de données ajouté le préfixe "data" :
data class Item(

    @PrimaryKey(autoGenerate = true)//Identifie comme clé primaire et Room génère l'id de chaque entité et unique
    val id : Int = 0,

    //ColumnInfo permet de personnaliser la colonne associée au champ spécifique
    // et avec lârgument "name" on spécifie un nom de colonne diff pour le champ plutôt q d'utiliser le nom de la variable
    @ColumnInfo(name = "name")
    val itemName : String,
    @ColumnInfo(name = "price")
    val itemPrice : Double,
    @ColumnInfo(name = "quantity")
    val quantityInStock : Int
)
