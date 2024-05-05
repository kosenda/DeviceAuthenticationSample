package ksnd.deviceauthenticationsample.biometric

sealed class BiometricError : Error() {
    data object NoHardware : BiometricError()
    data object HardwareUnavailable : BiometricError()
    data object NoneEnrolled : BiometricError()
    data object SecurityUpdateRequired : BiometricError()
    data object Unsupported : BiometricError()
    data object Unknown : BiometricError()
}