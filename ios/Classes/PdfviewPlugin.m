#import "PdfviewPlugin.h"

#if __has_include("pdfview-Swift.h")
    #import "pdfview-Swift.h"
#else
    #import <pdfview/pdfview-Swift.h>
#endif

@implementation PdfviewPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftPdfviewPlugin registerWithRegistrar:registrar];
}
@end
