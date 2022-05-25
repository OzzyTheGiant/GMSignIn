package dreamcraft.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dreamcraft.main.models.Alert
import dreamcraft.main.models.Offices
import dreamcraft.main.ui.components.AppBar
import dreamcraft.main.ui.components.Form
import dreamcraft.main.ui.theme.GMSignInTheme
import dreamcraft.main.viewmodels.FormViewModel


class MainActivity : ComponentActivity() {
    private lateinit var title: String
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private lateinit var locationClient: FusedLocationProviderClient
    private val viewModel: FormViewModel = FormViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = getString(R.string.main_title)
        permissionLauncher = registerForActivityResult(RequestPermission(), ::getLocationData)
        locationClient = LocationServices.getFusedLocationProviderClient(this)

        getLocationData(initialCall = true)

        setContent {
            GMSignInTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.surface) {
                    Column() {
                        AppBar(title, withSettingsButton = true)
                        Form(viewModel, ::submitFormData)
                    }

                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        SnackbarHost(hostState = viewModel.snackbarHostState) {
                            Snackbar(snackbarData = it, backgroundColor = viewModel.alertType.color)
                        }
                    }
                }
            }
        }
    }

    /**
     * Get Location data from FusedLocationProviderClient if user grants permission
     * @param ready The result of the permission dialog box
     * @param initialCall Whether this function is called on app start up to prompt for permissions
     */
    private fun getLocationData(ready: Boolean = false, initialCall: Boolean = false) {
        val permission: String = Manifest.permission.ACCESS_COARSE_LOCATION
        val permissionCheck = if (ready) 0 else ContextCompat.checkSelfPermission(this, permission)

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            locationClient.lastLocation.addOnSuccessListener(viewModel::populateOfficeProperty)
        } else if (initialCall) {
            permissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        } else {
            /* TODO: always set Office manually, unless setting is changed to use location, then
             *  prompt for permission again */
            viewModel.populateOfficeProperty(Offices.WESLACO)
            viewModel.alert(Alert.INFO, applicationContext.getString(R.string.manual_office_set))
        }
    }

    private fun submitFormData() {
        viewModel.generateTimestamp()
        viewModel.alert(Alert.SUCCESS, applicationContext.getString(R.string.sign_in_success))
    }
}
