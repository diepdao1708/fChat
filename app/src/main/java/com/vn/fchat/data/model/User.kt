package com.vn.fchat.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize
import java.io.Serializable


data class User (
    var UserName: String,
    var PhoneNum: String,
    var Password: String
) : Serializable