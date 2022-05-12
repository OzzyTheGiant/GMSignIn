package dreamcraft.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dreamcraft.main.models.ClientSignInEntry
import dreamcraft.main.ui.components.AppBar
import dreamcraft.main.ui.components.Form
import dreamcraft.main.ui.theme.GMSignInTheme
import dreamcraft.main.viewmodels.FormViewModel


class MainActivity : ComponentActivity() {
    private lateinit var title: String
    private val viewModel: FormViewModel = FormViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = getString(R.string.main_title)

        setContent {
            GMSignInTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    Column() {
                        AppBar(title, withSettingsButton = true)
                        Form(viewModel, ::submitFormData)
                    }
                }
            }
        }
    }

    private fun submitFormData() {
        Log.i("APP", viewModel.formData.toString())
    }
}
