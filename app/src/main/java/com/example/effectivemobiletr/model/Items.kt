package com.example.effectivemobiletr.model

import com.google.gson.annotations.SerializedName


data class Items (

  @SerializedName("id"          ) var id          : String?           = null,
  @SerializedName("title"       ) var title       : String?           = null,
  @SerializedName("subtitle"    ) var subtitle    : String?           = null,
  @SerializedName("price"       ) var price       : Price?            = Price(),
  @SerializedName("feedback"    ) var feedback    : Feedback?         = Feedback(),
  @SerializedName("tags"        ) var tags        : ArrayList<String> = arrayListOf(),
  @SerializedName("available"   ) var available   : Int?              = null,
  @SerializedName("description" ) var description : String?           = null,
  @SerializedName("info"        ) var info        : ArrayList<Info>   = arrayListOf(),
  @SerializedName("ingredients" ) var ingredients : String?           = null

)