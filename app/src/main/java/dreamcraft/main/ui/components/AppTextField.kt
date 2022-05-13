package dreamcraft.main.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import dreamcraft.main.R

@Composable
fun AppTextField(
    label: String,
    value: String,
    padding: Dp,
    selectable: Boolean = false,
    multiline: Boolean = false,
    onChange: (String) -> Unit
) {
    val dropdownArrowAltText = stringResource(R.string.purpose_options)
    val iconSize = dimensionResource(R.dimen.button_icon_size)
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
        onValueChange = onChange,
        label= { Text(label) },
        trailingIcon = {
            if (selectable) Icon(
                imageVector = Icons.Rounded.ArrowDropDown,
                contentDescription = dropdownArrowAltText,
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.size(iconSize)
            )
        },
    )
}