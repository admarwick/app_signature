#import "AppSignaturePlugin.h"
#import <app_signature/app_signature-Swift.h>

@implementation AppSignaturePlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftAppSignaturePlugin registerWithRegistrar:registrar];
}
@end
