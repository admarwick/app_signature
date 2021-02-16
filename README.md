# app_signature

This Flutter plugin gets the app signature on Android.

This fork of the [original plugin](https://github.com/Michax94/app_signature) returns the SHA-1 app signature, formatted in the
same way as it is in a keystore or as it is obtained by running the gradle task ```signingReport```.

On IOS, where the app signature is not needed AFAIK, the plugin runs but the
signature returned is just a string denoting the version of IOS on the device.

To use this plugin, add it as a dependency to your pubspec.yaml file.
```
dependencies:
  app_signature:
    git:
      url: https://github.com/admarwick/app_signature.git
```

Then, import it in your Dart code.
```
import 'package:app_signature/app_signature.dart';
```

Finally call the plugin asynchronously to obtain the SHA-1 app signature as a Future.
```
String signature = await AppSignature.getAppSignature();
```

Note: if you need the signature in order to use a restricted API key in your Google REST API by adding
the signature to the headers of your https query, be sure to remove the colons from the
signature string returned by this plugin.
