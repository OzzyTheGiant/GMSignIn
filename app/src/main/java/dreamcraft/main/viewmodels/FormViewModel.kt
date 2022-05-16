package dreamcraft.main.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dreamcraft.main.models.ClientSignInEntry
import java.text.SimpleDateFormat
import java.util.*

class FormViewModel : ViewModel() {
    var formData by mutableStateOf(ClientSignInEntry())
    var showVisitPurposeOptions by mutableStateOf(false)

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

    fun populateOfficeProperty() {

    }
}
