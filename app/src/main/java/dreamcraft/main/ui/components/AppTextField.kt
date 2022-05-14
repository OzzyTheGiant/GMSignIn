@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package dreamcraft.main.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
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
    isInvalid: Boolean = false,
    onChange: (String) -> Unit
) {
    val requiredError = stringResource(R.string.required)
    val errorTextSize = dimensionResource(R.dimen.font_error_size).value.sp
    var modifier = Modifier.padding(padding).fillMaxWidth()

    val colors = TextFieldDefaults.textFieldColors(
        backgroundColor = MaterialTheme.colors.background,
        cursorColor = MaterialTheme.colors.primary,
        focusedIndicatorColor = MaterialTheme.colors.primaryVariant,
        unfocusedIndicatorColor = MaterialTheme.colors.primary,
        errorIndicatorColor = MaterialTheme.colors.error
    )

    if (multiline) modifier = modifier.height(dimensionResource(R.dimen.textbox_size))

    if (multiline) OutlinedTextField(
        value = value,
        modifier = modifier,
        colors = colors,
        onValueChange = onChange,
        label = { Text(label) }
    )

    else {
        Column() {
            TextField(
                value = value,
                modifier = modifier,
                colors = colors,
                readOnly = selectable,
                onValueChange = onChange,
                label = { Text(label) },
                isError = isInvalid,
                trailingIcon = {
                    if (selectable) ExposedDropdownMenuDefaults.TrailingIcon(expanded = isSelected)
                },
            )

            Text(
                text = if (isInvalid) requiredError.replace("{x}", label) else "",
                fontSize = errorTextSize,
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(padding)
            )
        }
    }
}
