package org.d3if3156.nakos.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.d3if3156.nakos.R
import org.d3if3156.nakos.ui.theme.NAKOSTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold (
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(id = R.string.app_name))
            },
                colors =  TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding))

    }
}
@Composable
fun ScreenContent(modifier: Modifier) {
    var name by remember { mutableStateOf("") }
    var alat by remember { mutableStateOf("") }
    var bahan by remember { mutableStateOf("") }

    val radioOptions = listOf(
        stringResource(id = R.string.makanan),
        stringResource(id = R.string.minuman)
    )
    var kategori by remember { mutableStateOf(radioOptions[0]) }

    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
        Text(text = stringResource(id = R.string.intro),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(value = name,
        onValueChange = { name = it},
        label = { Text(text = stringResource(R.string.name))},
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(value = alat,
            onValueChange = { alat = it},
            label = { Text(text = stringResource(R.string.alat))},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(value = bahan,
            onValueChange = { bahan = it},
            label = { Text(text = stringResource(R.string.bahan))},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Row (
            modifier = Modifier.padding(top = 6.dp).border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ) {
            radioOptions.forEach { text ->
                KategoriOption(
                    label = text,
                    isSelected = kategori == text,
                    modifier = Modifier.selectable(
                        selected = kategori == text,
                        onClick = { kategori = text },
                        role = Role.RadioButton
                    )
                        .weight(1f).padding(16.dp)
                )

            }
        }
    }
}
@Composable
fun KategoriOption(label: String, isSelected: Boolean, modifier: Modifier) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = isSelected, onClick = null)
        Text(text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
//@Composable
//fun Greeting(name: String) {
//    MainScreen { modifier ->
//
//        Text(
//            text = "Hallo $name!" +
//                    " Mau makan apa hari ini ?",
//            modifier = modifier
//        )
//    }
//}
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPreview() {
    NAKOSTheme {
        MainScreen()
    }
}