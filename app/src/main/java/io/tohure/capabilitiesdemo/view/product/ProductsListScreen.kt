package io.tohure.capabilitiesdemo.view.product

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProductsListScreen() {

    Text(text = "Mi primera pantalla")

}

@Preview(showBackground = true)
@Composable
fun ProductPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ProductsListScreen()
    }
}