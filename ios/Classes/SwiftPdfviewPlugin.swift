import Flutter
import UIKit

public class SwiftPdfviewPlugin: NSObject, FlutterPlugin {
    public static func register(with registrar: FlutterPluginRegistrar) {
        let channel = FlutterMethodChannel(name: "pdfview", binaryMessenger: registrar.messenger())
        let instance = SwiftPdfviewPlugin()
        registrar.addMethodCallDelegate(instance, channel: channel)
    }
    
    public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
        
        let controller = ViewController()
        
        
        switch call.method {
        case "pdfview":
            if let args = call.arguments as? String {
                
                controller.generateAndLoad(file:args);
                controller.showPDF();
                
            } else {
                result(FlutterError.init(code: "BAD_ARGS",
                                         message: "Wrong argument types",
                                         details: nil))
            }
        default:
            result(FlutterMethodNotImplemented)
        }
    }
}
