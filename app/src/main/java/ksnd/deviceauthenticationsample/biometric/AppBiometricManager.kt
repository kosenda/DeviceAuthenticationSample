package ksnd.deviceauthenticationsample.biometric

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import timber.log.Timber
import javax.inject.Inject

/**
 * デバイス認証を行う
 *
 * @param biometricManager BiometricManager
 */
class AppBiometricManager @Inject constructor(
    private val biometricManager: BiometricManager,
) {
    /**
     * デバイス認証を行う
     *
     * @param fragmentActivity FragmentActivity
     * @param onSucceeded 認証成功時の処理
     */
    fun authenticateDevice(
        fragmentActivity: FragmentActivity,
        onSucceeded: () -> Unit,
    ) {
        checkAvailableAuthenticate()

        showAuthenticationDialog(
            fragmentActivity = fragmentActivity,
            onSucceeded = onSucceeded,
        )
    }

    /**
     * デバイス認証が利用可能かチェックする
     */
    private fun checkAvailableAuthenticate() {
        val result = biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
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

    /**
     * 認証ダイアログを表示し認証を行う
     *
     * @param fragmentActivity FragmentActivity
     * @param onSucceeded 認証成功時の処理
     */
    private fun showAuthenticationDialog(
        fragmentActivity: FragmentActivity,
        onSucceeded: () -> Unit,
    ) {
        val biometricPrompt = BiometricPrompt(
            fragmentActivity,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    Timber.d("onAuthenticationError errorCode: $errorCode, errString: $errString")
                    super.onAuthenticationError(errorCode, errString)
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    Timber.d("onAuthenticationSucceeded")
                    onSucceeded()
                    super.onAuthenticationSucceeded(result)
                }

                override fun onAuthenticationFailed() {
                    Timber.d("onAuthenticationFailed")
                    super.onAuthenticationFailed()
                }
            },
        )

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("デバイス認証")
            .setAllowedAuthenticators(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
            .build()

        biometricPrompt.authenticate(promptInfo)
    }
}
