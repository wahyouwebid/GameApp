package com.wahyou.app.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

@Parcelize
data class Game(
    val id : Int,
    val name : String?,
    val urlPdf : String?,
    val urlGame : String?,
    val thumbnail : String?,
) : Parcelable
