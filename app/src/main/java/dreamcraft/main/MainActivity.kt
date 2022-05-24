package dreamcraft.main

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
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

        viewModel.populateOfficeProperty()
        getLocationData()

        setContent {
            GMSignInTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.surface) {
                    Column() {
                        AppBar(title, withSettingsButton = true)
                        Form(viewModel, ::submitFormData)
                    }
                }
            }
        }
    }

    private fun getLocationData(ready: Boolean = false) {
        val permission: String = Manifest.permission.ACCESS_COARSE_LOCATION
        val isGranted: Int = if (ready) 1 else PackageManager.PERMISSION_GRANTED

        // Check if location permission already granted before
        if (ContextCompat.checkSelfPermission(this, permission) == isGranted) {
            locationClient.lastLocation.addOnSuccessListener(::onFetchLocationSuccess)
        } else {
            permissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }

    private fun onFetchLocationSuccess(location: Location) {
        viewModel.formData = viewModel.formData.copy(office = Offices.find(location))
    }

    private fun submitFormData() {
        viewModel.generateTimestamp()
        Log.i("APP", viewModel.formData.toString())
    }
}
