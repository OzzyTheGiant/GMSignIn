package dreamcraft.main.models

import java.text.SimpleDateFormat
import java.util.Calendar

data class ClientSignInEntry (
    val full_name: String = "",
    val office: String = "",
    val visit_purpose: String = "",
    val visit_datetime: String = "",
    val drop_off: Boolean = false,
    val pick_up: Boolean = false,
    val comments: String? = null
) {
    companion object {
        @JvmStatic
        public fun updateVisitDateTime(formData: ClientSignInEntry): ClientSignInEntry {
            return formData.copy(visit_datetime = SimpleDateFormat
                .getDateTimeInstance()
                .format(Calendar.getInstance().time)
            )
        }
    }
}