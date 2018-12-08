import Flutter
import UIKit

public class SwiftPdfviewPlugin: NSObject, FlutterPlugin {
    
    
    var window: UIWindow?
    var controller : ViewController!
    
    public static func register(with registrar: FlutterPluginRegistrar) {
        let channel = FlutterMethodChannel(name: "pdfview", binaryMessenger: registrar.messenger())
        let instance = SwiftPdfviewPlugin()
        registrar.addMethodCallDelegate(instance, channel: channel)
    }
    
    
    func showWindow(){
        
        
        // Create a new window for the window property that
        // comes standard on the AppDelegate class. The UIWindow
        // is where all view controllers and views appear.
        window = UIWindow(frame: UIScreen.main.bounds)
        //
        // Create a new instance of ViewController
        controller =  ViewController()
        let mainViewController = controller
        //
        // Set the initial View Controller to our instance of ViewController
        window?.rootViewController = mainViewController
        //
        // Present the window
        window?.makeKeyAndVisible()
        
        window?.sizeToFit()
        window?.bringSubview(toFront: controller.wkWebView)
    }
    
    
    public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
        
        
       showWindow()
        
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
