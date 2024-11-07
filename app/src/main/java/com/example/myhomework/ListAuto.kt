package com.example.myhomework

import android.util.Log

object ListAuto { //singleton

private val listAuto = mutableListOf<Auto>()

    init {
        listAuto.add(Auto("Audi", "used auto, Hatchback", "02/03/2013"))
        listAuto.add(Auto("Volvo", "used   auto, Roadster", "02/122015"))
        listAuto.add(Auto("Toyota", "new   auto, Convertible", "04/042023"))
        listAuto.add(Auto("Audi", "new auto, Saloon", "05/06/2012"))
        listAuto.add(Auto("Volvo", "used auto, Hatchback", "09/11/2009"))
        listAuto.add(Auto("Toyota", "new auto, Pickup", "11/11/2010"))
        listAuto.add(Auto("Subaru", "used auto, Estate", "25/09/2015"))
        listAuto.add(Auto("Mercedes", "new auto", "30/03/2017"))
        listAuto.add(Auto("Opel", "used auto, Hatchback",  "03/01/2019"))
        listAuto.add(Auto("Reno", "used   auto, Roadster", "01/01/2015"))
        listAuto.add(Auto("BMW", "new auto,Convertible", "19/12/2021"))
        listAuto.add(Auto("Peugeot", "new auto, Pickup", "21/12/2017"))
        listAuto.add(Auto("Skoda", "used auto, Saloon", "09/08/2019"))
        listAuto.add(Auto("Mazda", "new   auto, Convertible", "09/01/2022"))
    }
    fun getNotes(): List<Auto> {
        return listAuto
    }

    fun addNote(note: Auto) {
        listAuto.add(note)
    }

    fun removeNote(note: Auto) {
        if (listAuto.contains(note)) {
            listAuto.remove(note)
        } else {
            Log.e("NoteRepository", "Заметка не найдена для удаления")
        }
    }
}