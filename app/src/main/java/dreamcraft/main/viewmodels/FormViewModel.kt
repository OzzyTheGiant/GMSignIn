package dreamcraft.main.viewmodels

import android.location.Location
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusRequester
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dreamcraft.main.models.Alert
import dreamcraft.main.models.ClientSignInEntry
import dreamcraft.main.models.Offices
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FormViewModel : ViewModel() {
    private val URL = "signin.gm-cpas.test"

    // Form state
    var formData by mutableStateOf(ClientSignInEntry())
    var showVisitPurposeOptions by mutableStateOf(false)
    var isFullNameFieldDirty: Boolean = false
    var isVisitPurposeFieldDirty: Boolean = false
    val focusRequester = FocusRequester()

    // Snackbar state
    var snackbarHostState = SnackbarHostState()
    var alertType: Alert = Alert.INFO
    private val noLocationMessage = "Location turned off. Office set manually, you can change this in Settings."

    // Form computed properties
    val isFormValid: Boolean get () = formData.full_name.isNotEmpty() && formData.visit_purpose.isNotEmpty()
    val isFullNameInvalid: Boolean get () = formData.full_name == "" && isFullNameFieldDirty
    val isVisitPurposeInvalid: Boolean get () = formData.visit_purpose == "" && isVisitPurposeFieldDirty

    private val http: HttpClient = HttpClient(CIO)

    fun toggleVisitPurposeOptions() {
        showVisitPurposeOptions = !showVisitPurposeOptions
        if (!showVisitPurposeOptions) isVisitPurposeFieldDirty = true
    }

    fun populateOfficeProperty(location: Location?) {
        location?.let {
            formData = formData.copy(office = Offices.find(it))
            return@populateOfficeProperty
        }

        populateOfficeProperty(Offices.WESLACO)
        alert(Alert.INFO, noLocationMessage)
    }

    fun populateOfficeProperty(office: Offices) {
        formData = formData.copy(office = office.value)
    }

    fun alert(alertType: Alert, message: String) {
        this.alertType = alertType
        viewModelScope.launch(Dispatchers.Main) {
            snackbarHostState.showSnackbar(message = message, duration = SnackbarDuration.Long)
        }
    }

    fun submitClientData() {
        viewModelScope.launch(Dispatchers.IO) {
            val response: HttpResponse = http.post(URL) {
                contentType(ContentType.Application.Json)
                setBody(formData)
            }

            alert(if (response.status.value == 200) Alert.SUCCESS else Alert.ERROR, response.body())
        }
    }
}
