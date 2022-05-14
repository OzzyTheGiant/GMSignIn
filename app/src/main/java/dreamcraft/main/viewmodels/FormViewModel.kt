package dreamcraft.main.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dreamcraft.main.models.ClientSignInEntry

class FormViewModel : ViewModel() {
    var formData by mutableStateOf(ClientSignInEntry())
    var showVisitPurposeOptions by mutableStateOf(false)

    var isFullNameFieldDirty: Boolean = false
    var isVisitPurposeFieldDirty: Boolean = false

    val isFullNameInvalid: Boolean get () = formData.full_name == "" && isFullNameFieldDirty
    val isVisitPurposeInvalid: Boolean get () = formData.visit_purpose == "" && isVisitPurposeFieldDirty

    public fun toggleVisitPurposeOptions() {
        showVisitPurposeOptions = !showVisitPurposeOptions
        if (!showVisitPurposeOptions) isVisitPurposeFieldDirty = true
    }
}
