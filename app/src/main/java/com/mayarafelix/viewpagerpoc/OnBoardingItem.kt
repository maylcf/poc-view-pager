package com.mayarafelix.viewpagerpoc

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class OnBoardingItem(
    @StringRes var title: Int,
    @StringRes var description: Int,
    @DrawableRes var image: Int
)