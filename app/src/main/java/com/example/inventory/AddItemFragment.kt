//Auteur : Silvana Esquivel
//Création : 08.03.23
/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.inventory

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.inventory.data.Item
import com.example.inventory.databinding.FragmentAddItemBinding

/**
 * Fragment to add or update an item in the Inventory database.
 */
class AddItemFragment : Fragment() {

    private val viewModel : InventoryViewModel by activityViewModels {
        //constructeur et trasmis l'insance Item.., utilisé l'intsance dataBase quôn adéjà créé dnas une tâche précedente pour appeler le constructeur itemDao
        InventoryViewModelFactory(
            (activity?.application as InventoryApplication).database.itemDao())
    }
    lateinit var item : Item



    private val navigationArgs: ItemDetailFragmentArgs by navArgs()

    // Binding object instance corresponding to the fragment_add_item.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment
    private var _binding: FragmentAddItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    //fonction privée qui renvoie un booleen
    private fun isEntryValid():Boolean{
        //appel de la fonction isEntryValid sur l'instance viewModel en transmis le texte issu des champs et on renvoie
        return viewModel.isEntryValid(
            binding.itemName.text.toString(),
            binding.itemPrice.text.toString(),
            binding.itemCount.text.toString()
        )
    }

    //fonnction privé
    private fun addNEwItem(){
        //on appel la fonction isEntry..dnans la condition if
        if (isEntryValid()) {
            //on appel la méthode addNew.. sur l'instance viewModel et on trasmis les infos s/l'élément saisies par l'utilisateur et on utilise l'instance binding pour les lire
            viewModel.addNewItem(
                binding.itemName.text.toString(),
                binding.itemPrice.text.toString(),
                binding.itemCount.text.toString()
            )

            //variable pour revenir à ItemListFragment
            val action = AddItemFragmentDirections.actionAddItemFragmentToItemListFragment()
            //on transmis l'action
            findNavController().navigate(action)
        }
    }

    /**
     * Called before fragment is destroyed.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        // Hide keyboard.
        val inputMethodManager = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveAction.setOnClickListener{
            addNEwItem()
        }
    }
}
