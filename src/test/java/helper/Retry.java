package test.java.helper;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static test.java.helper.GlobalConfig.maxRetryCount;

public class Retry implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int _maxRetryCount = Integer.parseInt(maxRetryCount);

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < _maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}
