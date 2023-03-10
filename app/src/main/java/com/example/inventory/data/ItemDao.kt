//Auteur : Silvana Esquivel
//Création : 08.03.23
package com.example.inventory.data
import androidx.room.*
import com.example.inventory.InventoryViewModel
import kotlinx.coroutines.flow.Flow


@Dao
interface ItemDao {
    // fonction qui reçoit une instance de la classe appelée item
    //OnConflict indique à Room la marche a suivre en cas de conflit,
    //OnConflictStrategy.IGNORE=ignore le nouvel élément si sa clé primaire figure déjà dans la base de données.
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    //fonction update : màj à la même clé q l'entité
    @Update
    suspend fun update (item: Item)

    //Pour supprimer un ou des élément
    @Delete
    suspend fun delete(item: Item)

    //Requête SQL
    @Query("SELECT * from item WHERE id = :id")
    //fonction qui reçoit un argument int et renvoie Flow..
    //Flow : permettre d'être averti à chaque fois q des éléments de la bdd sont modifiés
    fun getItem(id: Int): Flow<Item>

    @Query("SELECT * from item ORDER BY name ASC")
    fun getItems():Flow<List<Item>>



}