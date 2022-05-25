package dreamcraft.main.models

data class ClientSignInEntry (
    val full_name: String = "",
    val office: String = "",
    val visit_purpose: String = "",
    val drop_off: Boolean = false,
    val pick_up: Boolean = false,
    val comments: String? = null
)
