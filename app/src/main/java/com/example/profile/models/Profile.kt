package com.example.profile.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Profile(
    @SerializedName("profile") val profile: ArrayList<String>,
) : Parcelable