package dreamcraft.main.viewmodels

import android.location.Location
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dreamcraft.main.models.ClientSignInEntry
import dreamcraft.main.models.Offices
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FormViewModel : ViewModel() {
    var formData by mutableStateOf(ClientSignInEntry())
    var showVisitPurposeOptions by mutableStateOf(false)
    var snackbarHostState = SnackbarHostState()

    var isFullNameFieldDirty: Boolean = false
    var isVisitPurposeFieldDirty: Boolean = false

    val isFormValid: Boolean get () = formData.full_name.isNotEmpty() && formData.visit_purpose.isNotEmpty()
    val isFullNameInvalid: Boolean get () = formData.full_name == "" && isFullNameFieldDirty
    val isVisitPurposeInvalid: Boolean get () = formData.visit_purpose == "" && isVisitPurposeFieldDirty

    fun toggleVisitPurposeOptions() {
        showVisitPurposeOptions = !showVisitPurposeOptions
        if (!showVisitPurposeOptions) isVisitPurposeFieldDirty = true
    }

    fun generateTimestamp() {
        formData = formData.copy(
            visit_datetime = SimpleDateFormat.getDateTimeInstance().format(Calendar.getInstance().time)
        )
    }

    fun populateOfficeProperty(location: Location?) {
        location?.let {
            formData = formData.copy(office = Offices.find(it))
            return@populateOfficeProperty
        }

        populateOfficeProperty(Offices.WESLACO)
        alert("Location turned off. Office set manually, you can change this in Settings.")
    }

    fun populateOfficeProperty(office: Offices) {
        formData = formData.copy(office = office.value)
    }

    fun alert(message: String) {
        viewModelScope.launch {
            snackbarHostState.showSnackbar(message = message, duration = SnackbarDuration.Long)
        }
    }
}
