@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package dreamcraft.main.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import dreamcraft.main.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppTextField(
    label: String,
    value: String,
    padding: Dp,
    selectable: Boolean = false,
    multiline: Boolean = false,
    isSelected: Boolean = false,
    onChange: (String) -> Unit
) {
    var modifier = Modifier.padding(padding).fillMaxWidth()

    val colors = TextFieldDefaults.textFieldColors(
        backgroundColor = MaterialTheme.colors.background,
        cursorColor = MaterialTheme.colors.primary,
        focusedIndicatorColor = MaterialTheme.colors.primaryVariant,
        unfocusedIndicatorColor = MaterialTheme.colors.primary
    )


    if (multiline) modifier = modifier.height(dimensionResource(R.dimen.textbox_size))

    if (multiline) OutlinedTextField(
        value = value,
        modifier = modifier,
        colors = colors,
        onValueChange = onChange,
        label = { Text(label) }
    )

    else TextField(
        value = value,
        modifier = modifier,
        colors = colors,
        readOnly = selectable,
        onValueChange = onChange,
        label= { Text(label) },
        trailingIcon = {
            if (selectable) ExposedDropdownMenuDefaults.TrailingIcon(expanded = isSelected)
        },
    )
}
