@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package dreamcraft.main.ui.components

import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import dreamcraft.main.models.VisitPurpose

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectField(
    label: String,
    options: Array<VisitPurpose>,
    value: String,
    expanded: Boolean,
    isInvalid: Boolean = true,
    onChange: (String) -> Unit,
    onToggle: () -> Unit
) {
    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { onToggle() }) {
        AppTextField(
            label = label,
            value = value.replace("_", " ").lowercase(),
            selectable = true,
            isSelected = expanded,
            isInvalid = isInvalid,
            onChange = {}
        )

        ExposedDropdownMenu(expanded = expanded, onDismissRequest = onToggle) {
            for (option in options) {
                val optionLabel = option.toString().replace("_", " ").lowercase()

                DropdownMenuItem(onClick = {
                    onChange(option.toString())
                    onToggle()
                }) {
                    Text(optionLabel)
                }
            }
        }
    }
}
