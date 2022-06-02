package dreamcraft.main.ui.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import dreamcraft.main.R
import dreamcraft.main.models.ClientSignInEntry
import dreamcraft.main.models.VisitPurpose
import dreamcraft.main.ui.theme.GMSignInTheme
import dreamcraft.main.viewmodels.FormViewModel


@Composable
fun Form(vm: FormViewModel = viewModel(), onSubmit: () -> Unit) {
    val focusManager = LocalFocusManager.current
    val fieldPadding = dimensionResource(R.dimen.padding_text_field)
    val screenPadding = dimensionResource(R.dimen.padding_small)
    val buttonWidth = dimensionResource(R.dimen.max_button_width)
    val visitPurposeLabel = stringResource(R.string.visit_purpose)
    val dropOffLabel = stringResource(R.string.drop_off)
    val pickUpLabel = stringResource(R.string.pick_up)
    val commentsLabel = stringResource(R.string.comments)
    val buttonLabel = stringResource(R.string.submit)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(screenPadding).focusRequester(vm.focusRequester)
    ) {
        AppTextField(
            label = "Full Name",
            value = vm.formData.full_name,
            isInvalid = vm.isFullNameInvalid
        ) {
            vm.formData = vm.formData.copy(full_name = it)
            vm.isFullNameFieldDirty = true
        }

        SelectField(
            label = visitPurposeLabel,
            options = VisitPurpose.values(),
            value = vm.formData.visit_purpose,
            expanded = vm.showVisitPurposeOptions,
            isInvalid = vm.isVisitPurposeInvalid,
            onToggle = vm::toggleVisitPurposeOptions,
            onChange = {
                vm.formData = vm.formData.copy(visit_purpose = it)
                vm.isVisitPurposeFieldDirty = true
            },
        )

        Row(Modifier.fillMaxWidth()) {
            AppCheckBox(dropOffLabel, vm.formData.drop_off) {
                vm.formData = vm.formData.copy(drop_off = it)
            }

            Spacer(Modifier.size(fieldPadding * 2))

            AppCheckBox(pickUpLabel, vm.formData.pick_up) {
                vm.formData = vm.formData.copy(pick_up = it)
            }
        }

        AppTextField(
            label = commentsLabel,
            value = vm.formData.comments ?: "" ,
            multiline = true
        ) {
            vm.formData = vm.formData.copy(comments = it)
        }

        Button(
            enabled = vm.isFormValid,
            modifier = Modifier.width(buttonWidth),
            onClick = {
                focusManager.clearFocus()
                onSubmit()
            }
        ) {
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
