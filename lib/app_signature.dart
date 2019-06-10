import 'dart:async';

import 'package:flutter/services.dart';

class AppSignature {
  static const MethodChannel _channel =
      const MethodChannel('app_signature');

  static Future<String> getAppSignature() async {
    final String smsCode = await _channel.invokeMethod('getAppSignature');
    return smsCode;
  }
}
