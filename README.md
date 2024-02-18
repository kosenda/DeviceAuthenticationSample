# Android端末でのデバイス認証の学習用のサンプルレポジトリ

## 前提
- Android 9 以上を対象
- UIはJetpack Compose


## 参考資料
- [Android Magazine - 便利な指紋認証、Android スマホで使うメリットや注意点をご紹介](https://www.android.com/intl/ja_jp/articles/52/)
  - Android で使える指紋認証以外の認証方法と特徴
    - パターンによる認証
    - PIN コード による認証
    - パスワードによる認証
    - 顔認証

- [生体認証ダイアログを表示する](https://developer.android.com/training/sign-in/biometric-auth)
  - アプリがサポートする認証の種類
  　　-　BIOMETRIC_STRONG
     - Android CDD で定義されているクラス 3 (以前はStrong)の要件を満たす、または超えるデバイス上の生体認証 (指紋、虹彩、顔など) 。
   - BIOMETRIC_WEAK
     - Android CDD で定義されているクラス 2 (以前のWeak)の要件を満たす、または超えるデバイス上の生体認証 (指紋、虹彩、顔など) 。
   - DEVICE_CREDENTIAL
     - デバイスを保護するために使用される非生体認証資格情報 (つまり、PIN、パターン、またはパスワード)。
