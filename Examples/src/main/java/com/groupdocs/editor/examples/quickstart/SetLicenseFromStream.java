/*
 * This example demonstrates how to set license from stream.
 */
package com.groupdocs.editor.examples.quickstart;

import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.license.License;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author AlexT
 */
public class SetLicenseFromStream {
    public static void run() throws FileNotFoundException, IOException {
        try (InputStream fileStream = new FileInputStream(Constants.LicensePath)) {
            License license = new License();
            license.setLicense(fileStream);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }
}
