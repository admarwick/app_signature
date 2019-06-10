import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:app_signature/app_signature.dart';

void main() {
  const MethodChannel channel = MethodChannel('app_signature');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '123456';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getAppSignature', () async {
    expect(await AppSignature.getAppSignature(), '123456');
  });
}
