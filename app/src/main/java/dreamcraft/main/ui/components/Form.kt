package dreamcraft.main.ui.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dreamcraft.main.R
import dreamcraft.main.models.ClientSignInEntry
import dreamcraft.main.models.VisitPurpose
import dreamcraft.main.ui.theme.GMSignInTheme
import dreamcraft.main.viewmodels.FormViewModel


@Composable
fun Form(formVM: FormViewModel = viewModel(), onSubmit: () -> Unit) {
    val screenPadding = dimensionResource(R.dimen.padding_text_field)
    val buttonWidth = dimensionResource(R.dimen.max_button_width)
    val dropOffLabel = stringResource(R.string.drop_off)
    val pickUpLabel = stringResource(R.string.pick_up)
    val commentsLabel = stringResource(R.string.comments)
    val buttonLabel = stringResource(R.string.submit)
    var showVisitPurposeOptions by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(screenPadding)
    ) {
        AppTextField(
            label = "Full Name",
            value = formVM.formData.full_name,
            padding = screenPadding
        ) {
            formVM.formData.full_name = it
            formVM.updateFormData()
        }

        AppTextField(
            label="Visit Purpose",
            value = formVM.formData.visit_purpose,
            padding = screenPadding,
            selectable = true
        ) {
            formVM.formData.visit_purpose = it
            formVM.updateFormData()
        }

        DropdownMenu(
            expanded = showVisitPurposeOptions,
            onDismissRequest = { showVisitPurposeOptions = false }
        ) {
            for (value in VisitPurpose.values()) {
                DropdownMenuItem(onClick = {
                    formVM.formData.visit_purpose = value.toString()
                    showVisitPurposeOptions = false
                }) {
                    Text(value.toString())
                }
            }
        }

        Row(Modifier.fillMaxWidth()) {
            AppCheckBox(dropOffLabel) {
                formVM.formData.drop_off = it
                formVM.updateFormData()
            }

            Spacer(Modifier.size(screenPadding * 2))

            AppCheckBox(pickUpLabel) {
                formVM.formData.pick_up = it
                formVM.updateFormData()
            }
        }

        AppTextField(label = commentsLabel, value = "", multiline = true, padding = screenPadding) {
            formVM.formData.comments = it
            formVM.updateFormData()
        }

        Button(modifier = Modifier.width(buttonWidth), onClick = onSubmit) {
            Text(buttonLabel)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FormPreview(viewModel: FormViewModel = FormViewModel()) {
    val formData = remember { mutableStateOf(ClientSignInEntry()) }

    GMSignInTheme() {
        Form(viewModel) {
            Log.i("APP", formData.toString())
        }
    }
}
