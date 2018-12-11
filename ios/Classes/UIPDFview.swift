//
//  UIPDFview.swift
//  pdfview
//
//  Created by Ricardo on 02/07/2018.
//

class UIPDFview: UIView {
    
    class func instanceFromNib() -> UIView {
        return UINib(nibName: "nib file name", bundle: nil).instantiate(withOwner: nil, options: nil)[0] as! UIView
    }
    
}
