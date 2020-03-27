package com.theo.ortapp2020.utils


import com.google.gson.Gson
import com.theo.ortapp2020.beans.ApodBeans
import com.theo.ortapp2020.beans.Item
import com.theo.ortapp2020.beans.NasaSearch


const val URL_WS_APOD =
    "https://api.nasa.gov/planetary/apod?api_key=catgeWqeBx3hqodnqpsVEaJGUHs2kO38NKWfpqx2"

const val URL_WS_SEARCH =
    "https://images-api.nasa.gov/search?q="

fun wsGetPictureOfDay(): ApodBeans {
    //Créer la requete
    //lancer la requete
    val json = sendGetOkHttpRequest(URL_WS_APOD)
    //parser le json
    //Regarder s'il y a une erreur

    return Gson().fromJson(json, ApodBeans::class.java)
}

fun wsGetPictureSearch(search: String): ArrayList<Item>? {
    //Créer la requete
    //lancer la requete
    val json =
        sendGetOkHttpRequest("$URL_WS_SEARCH$search&media_type=image")
    //parser le json
    val result = Gson().fromJson(json, NasaSearch::class.java)
    //Regarder s'il y a une erreur

    return result.collection.items
}





