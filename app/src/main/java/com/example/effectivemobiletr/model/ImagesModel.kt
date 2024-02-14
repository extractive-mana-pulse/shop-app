package com.example.effectivemobiletr.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ImagesModel(
    @SerializedName("images")
    val images: ArrayList<String>? = ArrayList(),
) : Serializable