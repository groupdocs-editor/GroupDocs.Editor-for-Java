package com.groupdocs.examples.editor;

import com.groupdocs.editor.license.License;
import com.groupdocs.examples.editor.utils.FailureRegister;
import com.groupdocs.examples.editor.utils.LicenseUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Locale;

public class TestsSetUp {
    @BeforeSuite
    public void setUp() throws IOException {
        Locale.setDefault(Locale.US);
        FailureRegister.getInstance().setThrowExceptionsImmediately(true);

        try (final InputStream licenseStream = LicenseUtils.createLicenseStream()) {
            if (licenseStream == null) {
                throw new RuntimeException("License was NOT set!");
            }
            final License license = new License();
            license.setLicense(licenseStream);
            System.out.println("License was set successfully!");
        }
    }

    @AfterSuite
    public void tearDown() {
        System.out.println("============================================");
        System.out.println("Test report path " + Paths.get("target/surefire-reports/index.html").toAbsolutePath());
        System.out.println("============================================");
    }
}
