package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONArray
import org.json.JSONObject

const val listOsob = "czlek"

data class Osoba(
    val imie: String,
    val nazwisko: String,
    val wiek: Int,
    val wysokosc: Int,
    val waga: Int
) {
    // Convert Osoba to JSONObject
    fun toJson(): JSONObject {
        return JSONObject().apply {
            put("imie", imie)
            put("nazwisko", nazwisko)
            put("wiek", wiek)
            put("wysokosc", wysokosc)
            put("waga", waga)
        }
    }

    // Companion object to create Osoba from JSONObject
    companion object {
        fun fromJson(jsonObject: JSONObject): Osoba {
            return Osoba(
                imie = jsonObject.getString("imie"),
                nazwisko = jsonObject.getString("nazwisko"),
                wiek = jsonObject.getInt("wiek"),
                wysokosc = jsonObject.getInt("wysokosc"),
                waga = jsonObject.getInt("waga")
            )
        }
    }
}

fun saveData(
    context: Context,
    imie: String,
    nazwisko: String,
    wiek: Int,
    wysokosc: Int,
    waga: Int
) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    // Load existing list or create a new one if it doesn't exist
    val existingListJson = sharedPreferences.getString("personList", null)
    val personList: MutableList<Osoba> = if (existingListJson != null) {
        val jsonArray = JSONArray(existingListJson)
        MutableList(jsonArray.length()) { i ->
            Osoba.fromJson(jsonArray.getJSONObject(i))
        }
    } else {
        mutableListOf()
    }

    // Add the new person to the list
    val newPerson = Osoba(imie, nazwisko, wiek, wysokosc, waga)
    personList.add(newPerson)

    // Convert updated list to JSONArray and save
    val updatedListJsonArray = JSONArray()
    personList.forEach { person ->
        updatedListJsonArray.put(person.toJson())
    }
    editor.putString("personList", updatedListJsonArray.toString())
    editor.apply()
}

fun getPersonList(context: Context): List<Osoba> {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

    // Get existing list or return empty if it doesn't exist
    val existingListJson = sharedPreferences.getString("personList", null)
    return if (existingListJson != null) {
        val jsonArray = JSONArray(existingListJson)
        List(jsonArray.length()) { i ->
            Osoba.fromJson(jsonArray.getJSONObject(i))
        }
    } else {
        emptyList()
    }
}
