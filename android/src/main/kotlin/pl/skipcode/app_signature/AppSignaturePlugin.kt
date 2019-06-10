package pl.skipcode.app_signature

import android.app.Activity
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class AppSignaturePlugin(private val context: Activity): MethodCallHandler {


  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      val channel = MethodChannel(registrar.messenger(), "app_signature")
      channel.setMethodCallHandler(AppSignaturePlugin(registrar.activity()))
    }
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == "getAppSignature") {
      val signature = AppSignatureHelper(this.context).getAppSignatures()[0]
      result.success(signature)
    } else {
      result.notImplemented()
    }
  }
}
