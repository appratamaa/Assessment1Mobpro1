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
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.d3if3156.nakos.R
import org.d3if3156.nakos.ui.theme.NAKOSTheme
import java.time.Month

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold (
        topBar = {
            TopAppBar(

                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.ExtraBold
                    )
        },
                colors =  TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.scrim,
                    titleContentColor = MaterialTheme.colorScheme.inverseOnSurface
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
    var date by remember { mutableStateOf("") }
    var dateError by remember { mutableStateOf(false) }
    var month by remember { mutableStateOf("") }
    var monthError by remember { mutableStateOf(false) }

    var isChecked by remember { mutableStateOf(false) }
    var zodiak by remember { mutableIntStateOf(0) }

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
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = stringResource(R.string.name)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(value = date,
            onValueChange = { date = it},
            label = { Text(text = stringResource(R.string.date))},
            supportingText = { ErrorHint(dateError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = month,
            onValueChange = { month = it },
            label = { Text(text = stringResource(R.string.month)) },
            supportingText = { ErrorHint(monthError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
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
                                     dateError = (date == "" || date == "A")
                                     monthError = (month == "" || month == "A")
                                     if (dateError || monthError) return@Button},
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
private fun getZodiak(date: Int, month: Int, isMale: Boolean): Int {
    return if (isMale) {
        when {
            date < 20 -> R.string.virgo
            month < 1 -> R.string.virgo
            else -> R.string.name

        }
    } else {
        when {
            date > 20 -> R.string.taurus
            month > 20 -> R.string.taurus
            else -> R.string.name
        }
    }
}
//@Composable
//fun IconPicker(isError: Boolean, unit: String) {
//    if (isError) {
//        Icon(Image)
//    }
//}
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
        MainScreen()
    }
}