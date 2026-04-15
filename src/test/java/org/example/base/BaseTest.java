package org.example.base;



import org.example.utils.ConfigReader;
import org.example.utils.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverManager.initDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(result.getName());
        }
        DriverManager.quitDriver();
    }

    public String takeScreenshot(String testName) {
        String filePath = ConfigReader.get("screenshotsPath") + testName + "_"  + System.currentTimeMillis() + ".png";

        try {
            Files.createDirectories(Paths.get(ConfigReader.get("screenshotsPath")));
            File src = ((TakesScreenshot) DriverManager.getDriver())
                    .getScreenshotAs(OutputType.FILE);
            Files.copy(src.toPath(), Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException("screenshot failed: " + filePath, e);
        }

        return filePath;
    }
}