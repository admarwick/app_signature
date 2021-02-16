# app_signature

This plugin gets the app signature on Android.

This fork of the original plugin returns the SHA-1 app signature, formatted in the
same way as it is in a keystore or as it is obtained by running the gradle task <signingReport>.

On IOS, where the app signature is not needed AFAIK, the plugin runs but the
signature returned is just the version of IOS on the device.

To use this plugin, add it as a dependency to your pubspec.yaml file:

Then, import it in your Dart code.

Finally call the plugin to obtain the SHA-1 app signature.

Note: if you need the signature in order to use a restricted Google REST API by adding
the signature to the headers of your https query, be sure to remove the colons from the
signature string returned by this plugin.


