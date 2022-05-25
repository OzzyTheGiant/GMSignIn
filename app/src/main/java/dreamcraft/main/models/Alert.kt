package dreamcraft.main.models

import androidx.compose.ui.graphics.Color
import dreamcraft.main.ui.theme.Blue
import dreamcraft.main.ui.theme.Green

enum class Alert {
    INFO {
        override val color: Color get() = Blue
    },

    SUCCESS {
        override val color: Color get() = Green
    },

    ERROR {
        override val color: Color get() = Color.Red
    };

    abstract val color: Color
}
