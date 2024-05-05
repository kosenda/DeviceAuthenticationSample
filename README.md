# Android端末でのデバイス認証の学習用のサンプルレポジトリ

## 前提
- Android 9 以上を対象
- 言語はKotlin
- UIはJetpack Compose

## 概要
`BiometricManager`でデバイス認証ができるか確認し、`BiometricPrompt`でデバイス認証する


## イメージ
|デバイス認証前|デバイス認証後|
|---|---|
|<img src="https://github.com/kosenda/DeviceCredentialSample/assets/60963155/6f98f8e4-258d-400f-b89d-c53ee81e1fd3" width="250">|<img src="https://github.com/kosenda/DeviceCredentialSample/assets/60963155/75a1f045-497f-4dee-8765-a5537f961703" width="250">|


## 参考資料
- [Android Magazine - 便利な指紋認証、Android スマホで使うメリットや注意点をご紹介](https://www.android.com/intl/ja_jp/articles/52/)
  - Android で使える指紋認証以外の認証方法と特徴
    - パターンによる認証
    - PIN コード による認証
    - パスワードによる認証
    - 顔認証

- [Android developers - 生体認証ダイアログを表示する](https://developer.android.com/training/sign-in/biometric-auth)
  - 生体認証ライブラリ
    - [androidx.biometric](https://developer.android.com/reference/kotlin/androidx/biometric/package-summary?_gl=1*1uy0r0b*_up*MQ..*_ga*MzkxMjMwNjk4LjE3MDgyNDEyNDQ.*_ga_6HH9YJMN9M*MTcwODI0MTI0NC4xLjAuMTcwODI0MTI5MS4wLjAuMA..)
  - アプリがサポートする認証の種類
    - `BIOMETRIC_STRONG`
      - Android CDD で定義されているクラス3の要件を満たす、または超えるデバイス上の生体認証 (指紋、虹彩、顔など) 
    - `BIOMETRIC_WEAK`
      - Android CDD で定義されているクラス2の要件を満たす、または超えるデバイス上の生体認証 (指紋、虹彩、顔など) 
    - `DEVICE_CREDENTIAL`
      - デバイスを保護するために使用される非生体認証資格情報 (PIN、パターン、またはパスワード)
    
- [Android developers - Android 11 のロック画面と認証の改善](https://android-developers-jp.googleblog.com/search/label/Biometrics)
  - クラス２、クラス３とかの説明もある

## メモ
- [BiometricPrompt](https://developer.android.com/reference/android/hardware/biometrics/BiometricPrompt?_gl=1*1x9cp68*_up*MQ..*_ga*MzkxMjMwNjk4LjE3MDgyNDEyNDQ.*_ga_6HH9YJMN9M*MTcwODI0MTI0NC4xLjAuMTcwODI0MTI5MS4wLjAuMA..)
  - Android の生体認証が提供するセキュリティ保証のメリットを活用してもらい、生体認証をアプリに簡単に統合してユーザーの機密データの保護を強化できるようにする
  - アプリのデベロッパーが方式を意識せずに、さまざまな Android デバイスで生体認証を利用できる
  - FingerprintManager が非推奨

- [BiometricManager](https://developer.android.com/reference/androidx/biometric/BiometricManager?_gl=1*1x9cp68*_up*MQ..*_ga*MzkxMjMwNjk4LjE3MDgyNDEyNDQ.*_ga_6HH9YJMN9M*MTcwODI0MTI0NC4xLjAuMTcwODI0MTI5MS4wLjAuMA..)
  - Android10から
  - デベロッパーはこれを使って生体認証の利用可否を照会できる
