package ksnd.devicecredentialsample.biometric

import androidx.biometric.BiometricManager
import javax.inject.Inject

class AppBiometricManager @Inject constructor(
    private val biometricManager: BiometricManager
) {

    fun authenticateDevice() {
        checkAvailableAuthenticate(AuthenticatorType.BIOMETRIC_STRONG)
        // TODO 認証の続きをする
    }

    private fun checkAvailableAuthenticate(type: AuthenticatorType) {
        val result = biometricManager.canAuthenticate(type.value)
        if (result == BiometricManager.BIOMETRIC_SUCCESS) return

        when (result) {
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> throw BiometricError.NoHardware
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> throw BiometricError.NoneEnrolled
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> throw BiometricError.HardwareUnavailable
            BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> throw BiometricError.SecurityUpdateRequired
            BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> throw BiometricError.Unsupported
            else -> throw BiometricError.Unknown
        }
    }
}

enum class AuthenticatorType(val value: Int) {
    BIOMETRIC_STRONG(BiometricManager.Authenticators.BIOMETRIC_STRONG),
    BIOMETRIC_WEAK(BiometricManager.Authenticators.BIOMETRIC_WEAK),
    DEVICE_CREDENTIAL(BiometricManager.Authenticators.DEVICE_CREDENTIAL),
}
