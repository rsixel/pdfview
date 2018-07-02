import XCTest

import iosTests

var tests = [XCTestCaseEntry]()
tests += iosTests.allTests()
XCTMain(tests)