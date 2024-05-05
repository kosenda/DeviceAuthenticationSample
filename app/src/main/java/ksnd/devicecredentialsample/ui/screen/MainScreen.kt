package ksnd.devicecredentialsample.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ksnd.devicecredentialsample.biometric.BiometricError
import ksnd.devicecredentialsample.ui.viewmodel.AuthenticateDeviceState
import ksnd.devicecredentialsample.ui.viewmodel.MainUiState
import ksnd.devicecredentialsample.ui.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    MainScreenContent(
        state = state,
        authenticateDevice = viewModel::authenticateDevice,
        resetAuthenticateDeviceState = viewModel::resetAuthenticateDeviceState,
    )
}

@Composable
private fun MainScreenContent(
    state: MainUiState,
    authenticateDevice: (FragmentActivity) -> Unit,
    resetAuthenticateDeviceState: () -> Unit,
) {
    val fragmentActivity = LocalContext.current as FragmentActivity

    Surface(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Column {
            Text(
                text = "Device Credential Sample",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )

            Row {
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(all = 16.dp),
                    onClick = { authenticateDevice(fragmentActivity) },
                ) {
                    Text(
                        text = "デバイス認証",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
                OutlinedButton(
                    modifier = Modifier
                        .weight(1f)
                        .padding(all = 16.dp),
                    onClick = resetAuthenticateDeviceState,
                ) {
                    Text(
                        text = "リセット",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }

            AnimatedVisibility(
                visible = state.authenticateDeviceState is AuthenticateDeviceState.Success,
                enter = scaleIn(animationSpec = spring()),
                exit = fadeOut(),
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "デバイス認証成功",
                        style = MaterialTheme.typography.headlineSmall,
                    )
                }
            }
        }
    }

    if (state.authenticateDeviceState is AuthenticateDeviceState.Failure) {
        AlertDialog(
            onDismissRequest = resetAuthenticateDeviceState,
            confirmButton = {
                Text(
                    text = "Close",
                    modifier = Modifier.clickable(onClick = resetAuthenticateDeviceState)
                )
            },
            text = {
                Text(
                    text = "認証に失敗しました\n ( ${state.authenticateDeviceState.error.errorMessage()} ) ",
                    style = MaterialTheme.typography.bodySmall,
                )
            },
        )
    }
}

private fun Throwable.errorMessage(): String {
    return when (this) {
        BiometricError.NoHardware -> "デバイスに生体認証機能がありません"
        BiometricError.NoneEnrolled -> "デバイス認証が登録されていません"
        BiometricError.HardwareUnavailable -> "生体認証機能が利用できません"
        BiometricError.SecurityUpdateRequired -> "生体認証機能のセキュリティ更新が必要です"
        BiometricError.Unsupported -> "生体認証機能がサポートされていません"
        BiometricError.Unknown -> "生体認証機能のエラーが発生しました"
        else -> "不明なエラーです"
    }
}

@Preview
@Composable
fun PreviewMainScreenContent() {
    MainScreenContent(
        state = MainUiState(),
        authenticateDevice = {},
        resetAuthenticateDeviceState = {},
    )
}
