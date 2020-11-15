package com.codewarsclient.utils

import com.codewarsclient.api.models.RankModel
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken

fun jsonElementToRankModel(jsonElement: JsonElement): RankModel? {
    val gson = Gson()
    val type = object : TypeToken<RankModel>() {}.type
    return gson.fromJson(jsonElement, type)
}