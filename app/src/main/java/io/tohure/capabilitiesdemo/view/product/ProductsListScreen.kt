package io.tohure.capabilitiesdemo.view.product

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import io.tohure.capabilitiesdemo.model.Product
import org.koin.androidx.compose.getViewModel

@Composable
fun ProductsListScreen() {

    getExtraData(LocalContext.current)

    val viewModel = getViewModel<ProductViewModel>()
    viewModel.getProducts()

    val products = viewModel.productList.observeAsState().value ?: emptyList()

    LazyColumn {
        items(products) {
            ProductItem(
                it,
                modifier = Modifier.padding(8.dp)
            )
        }
    }

}

@Composable
fun ProductItem(product: Product, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                modifier = modifier
                    .size(100.dp)
                    .clip(MaterialTheme.shapes.small),
                model = product.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            ProductInfo(product.title, product.price)
        }
    }
}

@Composable
fun ProductInfo(title: String, price: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.displayMedium,
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = price,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

fun getExtraData(context: Context) {
    val activity = context.findActivity()
    val intent = activity?.intent

    val assistantExtra = intent?.extras
    val queryParameter = assistantExtra?.getString("featureParam") ?: ""

    //Log extras
    if (assistantExtra != null) {
        for (extraKey in assistantExtra.keySet()) {
            Log.v("-thr", "Extra: " + extraKey + ": " + assistantExtra.getString(extraKey))
        }

        Toast.makeText(context, queryParameter, Toast.LENGTH_SHORT).show()
    }
}

fun Context.findActivity(): Activity? =
    when (this) {
        is Activity -> this
        is ContextWrapper -> baseContext.findActivity()
        else -> null
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