package dreamcraft.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dreamcraft.main.ui.theme.GMSignInTheme

class MainActivity : ComponentActivity() {
    private val title: String = getString(R.string.main_title)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GMSignInTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    AppBar(title, withSettingsButton = true)
                }
            }
        }
    }
}

@Composable
fun AppBar(title: String, withSettingsButton: Boolean = false) {
    val btnSize = dimensionResource(R.dimen.button_icon_size)
    val rowPadding = dimensionResource(R.dimen.padding_small)
    val borderWidth = dimensionResource(R.dimen.border_width)
    val companyName = stringResource(R.string.company_name)
    val settingsBtnAltText = stringResource(R.string.settings)
    val logo = painterResource(R.drawable.gm_logo)
    val buttonModifier = Modifier
        .width(btnSize)
        .height(btnSize)

    Column() {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(rowPadding)
        ) {
            Row() {
                // Group GM Icon and activity title
                Icon(logo, contentDescription = companyName, tint = Color.Unspecified)

                Text(
                    title,
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier.padding(start = rowPadding)
                )
            }

            if (withSettingsButton) IconButton(modifier = buttonModifier, onClick = {
                Log.d("Main", "Test settings button")
            }) {
                Icon(
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = settingsBtnAltText,
                    tint = MaterialTheme.colors.primary,
                    modifier = buttonModifier
                )
            }
        }

        Divider(color = MaterialTheme.colors.primary, thickness = borderWidth)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GMSignInTheme {
        AppBar(title = stringResource(R.string.main_title), withSettingsButton = true)
    }
}