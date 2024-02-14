package com.example.effectivemobiletr.model

import com.google.gson.annotations.SerializedName


data class Price (

  @SerializedName("price"             ) var price             : String? = null,
  @SerializedName("discount"          ) var discount          : Int?    = null,
  @SerializedName("priceWithDiscount" ) var priceWithDiscount : String? = null,
  @SerializedName("unit"              ) var unit              : String? = null

)