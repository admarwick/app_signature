import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:app_signature/app_signature.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _appSignature = 'Unknown';

  @override
  void initState() {
    super.initState();
    initAppSignature();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initAppSignature() async {
    String appSignature;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      appSignature = await AppSignature.getAppSignature();
    } on PlatformException {
      appSignature = 'Failed to get app signature';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _appSignature = appSignature;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin get app signature'),
        ),
        body: Center(
          child: Text('App signature: $_appSignature\n'),
        ),
      ),
    );
  }
}
