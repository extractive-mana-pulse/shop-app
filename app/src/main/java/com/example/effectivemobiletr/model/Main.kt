package com.example.effectivemobiletr.model

import com.google.gson.annotations.SerializedName


data class Main (

  @SerializedName("items" ) var items : ArrayList<Items> = arrayListOf()

)