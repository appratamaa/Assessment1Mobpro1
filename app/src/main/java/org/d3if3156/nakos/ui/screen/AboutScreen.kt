package org.d3if3156.nakos.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.d3if3156.nakos.R
import org.d3if3156.nakos.ui.theme.NAKOSTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen() {
    Scaffold (
        topBar = {
            TopAppBar(

                title = {
                    Text(
                        text = stringResource(id = R.string.about),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Black
                    )
                },
                colors =  TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.scrim,
                    titleContentColor = MaterialTheme.colorScheme.inverseOnSurface
                ),
            )
        }
    ) { padding ->
       Text(text = stringResource(R.string.apps),
           modifier = Modifier
               .padding(padding)
               .padding(16.dp),
           textAlign = TextAlign.Center,

           )
//        Text(text = stringResource(R.string.copyright),
//            textAlign = TextAlign.Center,
//            modifier = Modifier
//                .padding(padding).padding(16.dp),
//
//            fontWeight = FontWeight.ExtraLight
//        )


    }
}
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun AboutScreenPreview() {
    NAKOSTheme {
        AboutScreen()
    }
}