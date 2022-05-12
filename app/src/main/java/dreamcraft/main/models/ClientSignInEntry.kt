package dreamcraft.main.models

import java.text.SimpleDateFormat
import java.util.Calendar

data class ClientSignInEntry (
    var full_name: String = "",
    var office: String = "",
    var visit_purpose: String = "",
    var drop_off: Boolean = false,
    var pick_up: Boolean = false,
    var comments: String? = null
) {
    lateinit var visit_datetime: String

    public fun updateVisitDateTime() {
        this.visit_datetime = SimpleDateFormat.getDateTimeInstance().format(Calendar.getInstance().time)
    }
}