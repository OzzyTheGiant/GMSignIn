package dreamcraft.main.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import dreamcraft.main.R

@Composable
fun AppCheckBox(label: String, checked: Boolean, onChange: (Boolean) -> Unit) {
    val screenPadding = dimensionResource(R.dimen.padding_text_field)
    val checkboxSize = dimensionResource(R.dimen.checkbox_size)

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            modifier = Modifier.size(checkboxSize),
            onCheckedChange = onChange
        )

        Spacer(Modifier.size(screenPadding))
        Text(label)
    }
}
