package com.example.myfirstproject.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Avengers(
    @StringRes val nameRes:Int,
    @DrawableRes val imageRes:Int,
    @StringRes val descriptionRes:Int
)
