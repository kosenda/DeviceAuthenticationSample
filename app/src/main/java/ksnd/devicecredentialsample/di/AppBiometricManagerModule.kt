package ksnd.devicecredentialsample.di

import android.content.Context
import androidx.biometric.BiometricManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ksnd.devicecredentialsample.biometric.AppBiometricManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppBiometricManagerModule {
    @Provides
    @Singleton
    fun provideAppBiometricManager(
        @ApplicationContext context: Context,
    ): AppBiometricManager {
        return AppBiometricManager(biometricManager = BiometricManager.from(context))
    }
}
