package com.example.profile.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Users(@SerializedName("users") val users: List<UserDetails> = emptyList()) : Parcelable

@Parcelize
data class UserDetails(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("photo") val photo: String? = null,
    @SerializedName("gender") val gender: String? = null,
    @SerializedName("school") val school: String? = null,
    @SerializedName("about") val about: String? = null,
    @SerializedName("hobbies") val hobbies: List<String> = emptyList(),
) : Parcelable