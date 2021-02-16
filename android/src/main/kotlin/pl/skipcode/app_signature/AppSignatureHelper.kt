package pl.skipcode.app_signature

import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import java.security.MessageDigest
import java.util.*


class AppSignatureHelper(context: Context) : ContextWrapper(context) {

    companion object {
        val TAG = AppSignatureHelper::class.java.simpleName
    }

    fun getAppSignatures(): ArrayList<String> {
        val appCodes = ArrayList<String>()

        return try {
            // Get all package signatures for the current package
            return getApplicationSignature(this.packageName) as ArrayList<String>
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e(TAG, "Unable to find package to obtain signature.", e)
            ArrayList()
        } catch (e: Throwable) {
            Log.e(TAG, "Error getting app signature", e)
            ArrayList()
        }
    }


    /// Formats a signature in the same way as keystore does, ie with colon delimiters
    /// between pairs of hex digits
    fun byte2HexFormatted(arr: ByteArray): String {
        val str = StringBuilder(arr.size * 2)
        for (i in arr.indices) {
            var h = Integer.toHexString(arr[i].toInt())
            val l = h.length
            if (l == 1) h = "0$h"
            if (l > 2) h = h.substring(l - 2, l)
            str.append(h.toUpperCase())
            if (i < arr.size - 1) str.append(':')
        }
        return str.toString()
    }

    /// Based on code posted by Mahti-Malv on Stack Overflow.
    /// See https://stackoverflow.com/a/53407183
    fun getApplicationSignature(packageName: String): List<String> {
        val signatureList: List<String>
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                // New signature
                val sig = this.packageManager
                        .getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES)
                        .signingInfo
                signatureList = if (sig.hasMultipleSigners()) {
                    // Send all with apkContentsSigners
                    sig.apkContentsSigners.map {
                        val digest = MessageDigest.getInstance("SHA")
                        digest.update(it.toByteArray())
                        byte2HexFormatted(digest.digest())
                    }
                } else {
                    // Send one with signingCertificateHistory
                    sig.signingCertificateHistory.map {
                        val digest = MessageDigest.getInstance("SHA")
                        digest.update(it.toByteArray())
                        byte2HexFormatted(digest.digest())
                    }
                }
            } else {
                val sig = this.packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES).signatures
                signatureList = sig.map {
                    val digest = MessageDigest.getInstance("SHA")
                    digest.update(it.toByteArray())
                    byte2HexFormatted(digest.digest())
                }
            }

            return signatureList
        } catch (e: Exception) {
            Log.e(TAG, "Error in getApplicationSignature", e)
        }
        return emptyList()
    }


}