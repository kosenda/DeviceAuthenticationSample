package ksnd.devicecredentialsample.ui.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import ksnd.devicecredentialsample.biometric.AppBiometricManager
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appBiometricManager: AppBiometricManager,
) : ViewModel() {
    private val authenticateDeviceState = MutableStateFlow<AuthenticateDeviceState>(AuthenticateDeviceState.None)

    val uiState = combine(
        authenticateDeviceState,
    ) { (authenticateDeviceState) ->
        MainUiState(
            authenticateDeviceState = authenticateDeviceState,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = MainUiState(),
    )

    fun authenticateDevice(fragmentActivity: FragmentActivity) {
        runCatching {
            appBiometricManager.authenticateDevice(
                fragmentActivity = fragmentActivity,
                onSucceeded = { authenticateDeviceState.value = AuthenticateDeviceState.Success },
            )
        }.onFailure {
            Timber.e("authenticateDevice onFailure: $it")
            authenticateDeviceState.value = AuthenticateDeviceState.Failure(it)
        }
    }

    fun resetAuthenticateDeviceState() {
        authenticateDeviceState.value = AuthenticateDeviceState.None
    }
}

data class MainUiState(
    val authenticateDeviceState: AuthenticateDeviceState = AuthenticateDeviceState.None,
)

sealed class AuthenticateDeviceState {
    data object None : AuthenticateDeviceState()
    data object Success : AuthenticateDeviceState()
    data class Failure(val error: Throwable) : AuthenticateDeviceState()
}