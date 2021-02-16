import 'dart:async';

import 'package:app_signature/app_signature.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

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
    try {
      appSignature = await AppSignature.getAppSignature();
    } on PlatformException {
      appSignature = 'Failed to get app signature';
    }
    if (mounted)
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
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text('App signature: $_appSignature'),
            ],
          ),
        ),
      ),
    );
  }
}
