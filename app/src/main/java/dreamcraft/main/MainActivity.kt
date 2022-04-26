package dreamcraft.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import dreamcraft.main.ui.components.AppBar
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
