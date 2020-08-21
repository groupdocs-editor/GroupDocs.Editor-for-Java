/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.editor.examples.quickstart;

import com.groupdocs.editor.examples.Constants;

/**
 * <summary>
 * This example demonstrates how to set license from file.
 * </summary>
 * <remarks>
 * The SetLicense method attempts to set a license from several locations
 * relative to the executable and GroupDocs.Editor.dll. You can also use the
 * additional overload to load a license from a stream, this is useful for
 * instance when the License is stored as an embedded resource.
 * </remarks>
 */
public class SetLicenseFromFile {

    public static void run() {
        try {
            // ExStart:applyLicense
            com.groupdocs.editor.license.License license = new com.groupdocs.editor.license.License();
            // Set license
            license.setLicense(Constants.LicensePath);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }
}
