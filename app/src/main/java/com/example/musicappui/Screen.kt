package com.example.musicappui

import androidx.annotation.DrawableRes

open class Screen(
    val title: String,
    val route: String
) {
    sealed class DrawerScreen(val dTitle: String, val dRoute: String, @DrawableRes val icon: Int)
        : Screen(dTitle, dRoute){
            object Account: DrawerScreen(
                "Account",
                "account",
                R.drawable.ic_account
            )
        object Subcription: DrawerScreen(
            "Subcription",
            "subcribe",
            R.drawable.ic_subcribe
        )
        object AddAccount: DrawerScreen(
            "Add_Account",
            "add_account",
            R.drawable.baseline_person_add_alt_1_24
        )
        }
}
val screensInDrawer = listOf(
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.Subcription,
    Screen.DrawerScreen.AddAccount
)