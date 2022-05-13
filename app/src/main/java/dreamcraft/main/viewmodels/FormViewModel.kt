package dreamcraft.main.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dreamcraft.main.models.ClientSignInEntry

class FormViewModel : ViewModel() {
    public var formData by mutableStateOf(ClientSignInEntry())
    public var showVisitPurposeOptions by mutableStateOf(false)

    public fun toggleVisitPurposeOptions() {
        showVisitPurposeOptions = !showVisitPurposeOptions
    }
}
