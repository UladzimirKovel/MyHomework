package com.example.myhomework

import android.util.Log

object ListAuto : Auto { //singleton

    private val listAuto = mutableListOf(
        Auto.User("Audi", "used auto, Hatchback"),
        Auto.Card("02/03/2013"),
        Auto.User("Volvo", "used   auto, Roadster"),
        Auto.Card("02/122015"),
        Auto.User("Toyota", "new   auto, Convertible"),
        Auto.Card("02/12/2015"),
        Auto.User("Audi", "new auto, Saloon"),
        Auto.Card("05/06/2012"),
        Auto.User("Volvo", "used auto, Hatchback"),
        Auto.Card("09/11/2009"),
        Auto.User("Toyota", "new auto, Pickup"),
        Auto.Card("11/11/2010"),
        Auto.User("Subaru", "used auto, Estate"),
        Auto.Card("25/09/2015"),
        Auto.User("Mercedes", "new auto"),
        Auto.Card("30/03/2017"),
        Auto.User("Opel", "used auto, Hatchback"),
        Auto.Card("03/01/2019"),
        Auto.User("Reno", "used   auto, Roadster"),
        Auto.Card("01/01/2015"),
        Auto.User("BMW", "new auto,Convertible"),
        Auto.Card("19/12/2021"),
        Auto.User("Peugeot", "new auto, Pickup"),
        Auto.Card("21/12/2017"),
        Auto.User("Skoda", "used auto, Saloon"),
        Auto.Card("09/08/2019"),
        Auto.User("Mazda", "new   auto, Convertible"),
        Auto.Card("09/01/2022")

    )

    /*init {
        listAuto.add(Auto("Audi", "used auto, Hatchback", "02/03/2013"))
        listAuto.add(Auto("Volvo", "used   auto, Roadster", "02/122015"))
        listAuto.add(Auto("Toyota", "new   auto, Convertible", "04/04/2023"))
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
    }*/
    fun getNotes(): List<Auto> {
        return listAuto
    }

    fun addNote(note: Auto) {
        listAuto.add(note)
    }

    fun removeNote(note: Auto) {
        if (listAuto.contains(note)) { // Проверяет, содержится ли элемент note в коллекции listAuto
            // (true-> найдет, false-> не найден )
            listAuto.remove(note) // Удаляет элемент note, если он там есть
            // (если удален -> true, если нет -> false)
        } else {
            Log.e("NoteRepository", "Заметка не найдена для удаления")
        }
    }
}