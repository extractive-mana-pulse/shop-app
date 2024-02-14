package com.example.effectivemobiletr.model

import com.google.gson.annotations.SerializedName


data class Info (

  @SerializedName("title" ) var title : String? = null,
  @SerializedName("value" ) var value : String? = null

)