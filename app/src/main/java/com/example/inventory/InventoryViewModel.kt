package com.example.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.ItemDao
import kotlinx.coroutines.launch
import com.example.inventory.data.Item


//Transmettre l'objet ItemDao en tant q paramètre au constructeur
class InventoryViewModel(private val itemDao: ItemDao): ViewModel() {


    //fonction pour ajouter des infos à la bdd de manière non bloquante
    private fun insertItem(item: Item) {
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }

    //Fonctin privée reçoit 3 chaînes de caractère et renvoie une instance Item
    private fun getNewItemEntry(itemName: String, itemPrice: String, itemCount: String):Item{
        return Item(
            itemName = itemName,
            itemPrice = itemPrice.toDouble(),
            quantityInStock = itemCount.toInt()
        )
    }


    //fonction publique reçcoit 3 chaînes
    fun addNewItem(itemName: String, itemPrice: String, itemCount: String){
        //On transmette ses infos à la fonctio get...
        val newItem = getNewItemEntry(itemName,itemPrice,itemCount)
        //Appel au fonct. insert.. pour ajouer à la bdd
        insertItem(newItem)
    }

    //fonction pour vérifier q les champs ne sont pas vides
    fun isEntryValid(itemName: String, itemPrice: String, itemCount: String):Boolean{
        if(itemName.isBlank() || itemPrice.isBlank() || itemCount.isBlank()){
            return false
        }
        return true
    }

}




//POur instancier la class InventoryViewModel
class InventoryViewModelFactory(private val itemDao: ItemDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //vérifier si modelclass est identique à la classe InventoryViewModel et renvoie une isntance sinon génére une exception
        if (modelClass.isAssignableFrom(InventoryViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return InventoryViewModel(itemDao)as T
        }
        throw IllegalAccessException("Unknown ViewModel Class")
    }
}