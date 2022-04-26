package dreamcraft.main.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dreamcraft.main.R

// We need to check for SDK version as custom fonts are not supported til version 26
val SDK_VERSION = android.os.Build.VERSION.SDK_INT
val Montserrat = FontFamily(Font(R.font.montserrat))

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = if (SDK_VERSION >= 26) Montserrat else FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )
)
