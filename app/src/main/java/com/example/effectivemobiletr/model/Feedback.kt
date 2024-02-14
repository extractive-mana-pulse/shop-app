package com.example.effectivemobiletr.model

import com.google.gson.annotations.SerializedName


data class Feedback (

  @SerializedName("count"  ) var count  : Int?    = null,
  @SerializedName("rating" ) var rating : Double? = null

)