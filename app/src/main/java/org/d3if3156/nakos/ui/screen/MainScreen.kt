package org.d3if3156.nakos.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3156.nakos.R
import org.d3if3156.nakos.navigation.Screen
import org.d3if3156.nakos.ui.theme.NAKOSTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold (
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {

                    }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Menu,
                            contentDescription = stringResource(R.string.menu),
                            tint = MaterialTheme.colorScheme.inverseOnSurface
                        )
                    }
                    },

                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Black
                    )
        },
                colors =  TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.scrim,
                    titleContentColor = MaterialTheme.colorScheme.inverseOnSurface
                ),
                        actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.About.route)
                    }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.about),
                            tint = MaterialTheme.colorScheme.inverseOnSurface
                        )
                    }
                }
            )
        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding))

    }
}
@Composable
fun ScreenContent(modifier: Modifier) {
    var name by rememberSaveable { mutableStateOf("") }
    var nameError by rememberSaveable { mutableStateOf(false) }
    var zodiac by rememberSaveable { mutableStateOf("") }
    var zodiacError by rememberSaveable { mutableStateOf(false) }


    var isChecked by rememberSaveable { mutableStateOf(false) }
    var zodiak by rememberSaveable { mutableIntStateOf(0) }

    Column (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text(
            text = stringResource(id = R.string.intro),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.fillMaxWidth(),
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = stringResource(R.string.name)) },
            trailingIcon = { IconPicker(nameError, "")},
            supportingText = { ErrorHint(nameError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(value = zodiac,
            onValueChange = { zodiac = it},
            label = { Text(text = stringResource(R.string.zodiak))},
            trailingIcon = { IconPicker(zodiacError, "")},
            supportingText = { ErrorHint(zodiacError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier.fillMaxWidth()
        )
        Row (

            modifier = Modifier.padding(top = 6.dp)
        ) {
            Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
                Switch(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colorScheme.primary,
                        checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                        uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                    )

                )
                Text(
                    text = if (isChecked) stringResource(R.string.wanita) else stringResource(R.string.pria),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Black
                )
                    Button(onClick = {
                         nameError = (name.isBlank() || name.any { it.isDigit() })
                         zodiacError = (zodiac.isBlank() || zodiac.any { it.isDigit() })
                         return@Button
                                     },
                        modifier = Modifier.padding(top = 80.dp),
                        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 8.dp)
                    ) {
                        Text(text = stringResource(R.string.ramal),
                            fontWeight = FontWeight.ExtraBold
                        )

                    }

            }
        }
    }
}
//private fun getZodiak(zodiac: String, isMale: Boolean): Int {
//    return if (isMale) {
//        when {
//            date < 20 -> R.string.virgo
//            else -> R.string.name
//
//        }
//    } else {
//        when {
//            date > 20 -> R.string.taurus
//            else -> R.string.name
//        }
//    }
//}
@Composable
fun IconPicker(isError: Boolean, unit: String) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else {
        Text(text = unit)
    }
}
@Composable
fun ErrorHint(isError: Boolean) {
    if (isError) {
        Text(text = stringResource(R.string.invalid))
    }
}
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPreview() {
    NAKOSTheme {
        MainScreen(rememberNavController())
    }
}