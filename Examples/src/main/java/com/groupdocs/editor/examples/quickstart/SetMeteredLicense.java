/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.editor.examples.quickstart;

import com.groupdocs.editor.license.Metered;

/**
 *
 * @author AlexT
 */
public class SetMeteredLicense {
    public static void run() {
        String publicKey = ""; // Your public license key
        String privateKey = ""; // Your private license key

        Metered metered = new Metered();
        metered.setMeteredKey(publicKey, privateKey);
    }
}
