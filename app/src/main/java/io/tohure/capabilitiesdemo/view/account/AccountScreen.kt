package io.tohure.capabilitiesdemo.view.account

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.tohure.capabilitiesdemo.util.ShortcutsUtil

@Composable
fun AccountScreen(context: Context = LocalContext.current) {
    var toastMsg = "Tu sistema no soporta Shortcuts"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            ShortcutsUtil.setAssistantShortcut(context)

            if (Build.VERSION.SDK_INT >= 25) {
                ShortcutsUtil.setDynamicShortcut(context)
                toastMsg = "Dynamic Shortcut creado!"
            }
            Toast.makeText(context, toastMsg, Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Website del proyecto")
        }
        Button(onClick = {
            if (Build.VERSION.SDK_INT >= 28) {
                ShortcutsUtil.setPinnedShortcut(context)
                toastMsg = "Pinned Shortcut creado!"
            }
            Toast.makeText(context, toastMsg, Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Agregar Acceso Directo")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AccountPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        AccountScreen()
    }
}