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

import android.app.Application
import com.example.inventory.data.ItemRoomDataBase

class InventoryApplication : Application(){

    //instanciez databse en appelant get.. sur Item...
    //lazy : l'instance databse soit créer par nécessité
    //Créer la bdd physique s/le disque lors du prémier accès
    val database:ItemRoomDataBase by lazy { ItemRoomDataBase.getDatabase(this) }
}
