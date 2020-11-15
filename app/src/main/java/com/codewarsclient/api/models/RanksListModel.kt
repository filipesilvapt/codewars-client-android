package com.codewarsclient.api.models

import com.codewarsclient.utils.jsonElementToRankModel
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName


data class RanksListModel(
    @SerializedName("overall")
    val overallRank: RankModel,

    @SerializedName("languages")
    private val _ranksByLanguage: JsonObject?
) {

    /**
     * Parse the ranks for each language in order to get language name inside the rank object
     */
    fun getRanksByLanguage(): List<RankModel> {
        val parsedRanks: MutableList<RankModel> = ArrayList()

        _ranksByLanguage?.let { ranks ->
            val entrySet: Set<Map.Entry<String, JsonElement?>> = ranks.entrySet()
            var currentRank: RankModel?

            for ((key) in entrySet) {
                currentRank = jsonElementToRankModel(ranks.get(key))
                currentRank?.let {
                    it.language = key
                    parsedRanks.add(it)
                }
            }
        }

        return parsedRanks
    }
}