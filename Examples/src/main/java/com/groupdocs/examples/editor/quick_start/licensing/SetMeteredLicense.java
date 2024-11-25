package com.groupdocs.examples.editor.quick_start.licensing;

import com.groupdocs.editor.license.Metered;
import com.groupdocs.examples.editor.utils.FailureRegister;

/**
 * <p>
 * This example demonstrates how to set Metered license. Learn more about
 * Metered license at <a href="https://purchase.groupdocs.com/faqs/licensing/metered">documentation</a>.
 * </p>
 */
public class SetMeteredLicense {

    public static void run() {
        String publicKey = "*****";
        String privateKey = "*****";
        try {
            Metered metered = new Metered();
            metered.setMeteredKey(publicKey, privateKey);
            System.out.println("License set successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
            System.err.println("License was NOT set.");
        }
    }
}
