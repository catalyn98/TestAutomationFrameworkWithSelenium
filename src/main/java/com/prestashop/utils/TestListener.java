package com.prestashop.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    Logger log;
    String testName;
    String testMethodName;

    @Override
    public void onTestStart(ITestResult iTestResult) {
        this.testMethodName = iTestResult.getMethod().getMethodName();
        log.info("[Starting " + testMethodName + "]");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info("[Test " + testMethodName + " passed]");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.info("[Test " + testMethodName + " failed]");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        this.testName = iTestContext.getCurrentXmlTest().getName();
        this.log = LogManager.getLogger(testName);
        log.info("[TEST " + testName + " STARTED]");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        log.info("[ALL " + testName + " FINISHED]");
    }
}