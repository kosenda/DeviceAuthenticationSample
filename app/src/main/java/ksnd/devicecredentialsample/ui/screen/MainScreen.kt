package ksnd.devicecredentialsample.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ksnd.devicecredentialsample.ui.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel) {
    MainScreenContent()
}

@Composable
private fun MainScreenContent() {
    Surface(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Column {
            Text(
                text = "DeviceCredentialSample",
                style = MaterialTheme.typography.headlineSmall,
            )
            Text(
                text = "BIOMETRIC_STRONG",
                modifier = Modifier.padding(top = 16.dp, start = 8.dp),
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = "BIOMETRIC_WEAK",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 16.dp, start = 8.dp),
            )
            Text(
                text = "DEVICE_CREDENTIAL",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 16.dp, start = 8.dp),
            )
        }
    }
}

@Preview
@Composable
fun PreviewMainScreenContent() {
    MainScreenContent()
}