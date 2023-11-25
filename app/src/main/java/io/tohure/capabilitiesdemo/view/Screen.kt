package io.tohure.capabilitiesdemo.view

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import io.tohure.capabilitiesdemo.R

sealed class Screen(val route: String, @StringRes val resourceId: Int, val image: ImageVector) {
    object Products : Screen("products", R.string.products, Icons.Filled.List)
    object Order : Screen("order", R.string.order, Icons.Filled.ShoppingCart)
    object Account : Screen("account", R.string.account, Icons.Filled.Face)
}