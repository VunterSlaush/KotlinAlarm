package mota.dev.internetalarm.utils

/**
 * Created by Slaush on 18/11/2017.
 */
import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import mota.dev.internetalarm.constants.Consts


import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.security.GeneralSecurityException
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec



class SecurePreferences @Throws(SecurePreferencesException::class)
constructor(context: Context, param2: String) {

    private val writer: Cipher
    private val reader: Cipher
    private val keyWriter: Cipher
    private val preferences: SharedPreferences

    protected val iv: IvParameterSpec
        get() {
            val iv = ByteArray(writer.blockSize)
            System.arraycopy("fldsjfodasjifudslfjdsaofshaufihadsf".toByteArray(), 0, iv, 0, writer.blockSize)
            return IvParameterSpec(iv)
        }

    class SecurePreferencesException(e: Throwable) : RuntimeException(e)

    init {
        try {
            this.writer = Cipher.getInstance(TRANSFORMATION)
            this.reader = Cipher.getInstance(TRANSFORMATION)
            this.keyWriter = Cipher.getInstance(KEY_TRANSFORMATION)

            initCiphers(param2)

            this.preferences = context.getSharedPreferences(Consts.PREFERENCES_NAMES, MODE)
        } catch (e: GeneralSecurityException) {
            throw SecurePreferencesException(e)
        } catch (e: UnsupportedEncodingException) {
            throw SecurePreferencesException(e)
        }

    }

    @Throws(UnsupportedEncodingException::class, NoSuchAlgorithmException::class, InvalidKeyException::class, InvalidAlgorithmParameterException::class)
    protected fun initCiphers(helperParam: String) {
        val ivSpec = iv
        val secretKey = getSecretKey(helperParam)

        writer.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec)
        reader.init(Cipher.DECRYPT_MODE, secretKey, ivSpec)
        keyWriter.init(Cipher.ENCRYPT_MODE, secretKey)
    }

    @Throws(UnsupportedEncodingException::class, NoSuchAlgorithmException::class)
    protected fun getSecretKey(key: String): SecretKeySpec {
        val keyBytes = createKeyBytes(key)
        return SecretKeySpec(keyBytes, TRANSFORMATION)
    }

    @Throws(UnsupportedEncodingException::class, NoSuchAlgorithmException::class)
    protected fun createKeyBytes(key: String): ByteArray {
        val md = MessageDigest.getInstance(SECRET_KEY_HASH_TRANSFORMATION)
        md.reset()
        return md.digest(key.toByteArray(charset(CHARSET)))
    }

    fun putString(key: String, value: String?) {
        if (value != null) {
            val secureValueEncoded = encrypt(value, writer)
            preferences.edit().putString(toKey(key), secureValueEncoded).commit()
        }
    }


    fun putDouble(key: String, value: Double?) {
        putString(key, java.lang.Double.toString(value!!))
    }

    fun putInt(key: String, value: Int) {
        putString(key, Integer.toString(value))
    }

    fun putBoolean(key: String, value: Boolean) {
        putString(key, java.lang.Boolean.toString(value))
    }


    fun remove(key: String) {
        preferences.edit().remove(toKey(key)).commit()
    }

    @Throws(SecurePreferencesException::class)
    fun getString(key: String, defValue: String): String {
        if (preferences.contains(toKey(key))) {
            val securedEncodedValue = preferences.getString(toKey(key), defValue)
            return decrypt(securedEncodedValue)
        }
        return defValue
    }

    @Throws(SecurePreferencesException::class)
    fun getInt(key: String, defValue: Int): Int {
        if (preferences.contains(toKey(key))) {
            val strValue = getString(key, "")
            return if (strValue == "")
                defValue
            else
                Integer.valueOf(strValue)!!
        }
        return defValue
    }

    @Throws(SecurePreferencesException::class)
    fun getBoolean(key: String, defValue: Boolean): Boolean {
        if (preferences.contains(toKey(key))) {
            val strValue = getString(key, "")
            return if (strValue == "")
                defValue
            else
                java.lang.Boolean.valueOf(strValue)!!
        }
        return defValue
    }

    fun getDouble(key: String, defValue: Double?): Double? {
        if (preferences.contains(toKey(key))) {
            val strValue = getString(key, "")
            return if (strValue == "")
                defValue
            else
                java.lang.Double.valueOf(strValue)
        }
        return defValue
    }

    private fun toKey(key: String): String {
        return encrypt(key, keyWriter)
    }

    @Throws(SecurePreferencesException::class)
    protected fun encrypt(value: String, writer: Cipher): String {
        val secureValue: ByteArray
        try {
            secureValue = convert(writer, value.toByteArray(charset(CHARSET)))
        } catch (e: UnsupportedEncodingException) {
            throw SecurePreferencesException(e)
        }

        return Base64.encodeToString(secureValue, Base64.NO_WRAP)
    }


    protected fun decrypt(securedEncodedValue: String): String {
        val securedValue = Base64.decode(securedEncodedValue, Base64.NO_WRAP)
        val value = convert(reader, securedValue)
        try {
            return String(value, Charset.forName(CHARSET))
        } catch (e: UnsupportedEncodingException) {
            throw SecurePreferencesException(e)
        }

    }

    companion object {

        private val TRANSFORMATION = "AES/CBC/PKCS5Padding"
        private val KEY_TRANSFORMATION = "AES/ECB/PKCS5Padding"
        private val SECRET_KEY_HASH_TRANSFORMATION = "SHA-256"
        private val CHARSET = "UTF-8"
        val MODE = Context.MODE_PRIVATE

        @Throws(SecurePreferencesException::class)
        private fun convert(cipher: Cipher, bs: ByteArray): ByteArray {
            try {
                return cipher.doFinal(bs)
            } catch (e: Exception) {
                throw SecurePreferencesException(e)
            }

        }
    }

}