package com.groupdocs.editor.examples;

import com.groupdocs.editor.examples.advancedusage.*;
import com.groupdocs.editor.examples.advancedusage.editabledocumentexamples.*;
import com.groupdocs.editor.examples.advancedusage.formfieldmanagerexamples.EditFormFieldCollection;
import com.groupdocs.editor.examples.advancedusage.formfieldmanagerexamples.FixInvalidFormFieldCollectionAndSave;
import com.groupdocs.editor.examples.advancedusage.formfieldmanagerexamples.LegacyFormFieldCollection;
import com.groupdocs.editor.examples.advancedusage.formfieldmanagerexamples.RemoveFormFieldCollection;
import com.groupdocs.editor.examples.basicusage.*;
import com.groupdocs.editor.examples.quickstart.HelloWorld;
import com.groupdocs.editor.examples.quickstart.SetLicenseFromFile;
import com.groupdocs.editor.examples.quickstart.SetLicenseFromStream;
import com.groupdocs.editor.examples.quickstart.SetMeteredLicense;

public class MainClass {

    public static void main(String[] args) throws Throwable {

        System.out.print("Using GroupDocs.Editor for Java version " + com.groupdocs.editor.Editor.class.getPackage().getSpecificationVersion() );
        System.out.print("Open Program.cs. \nIn main() method uncomment the example that you want to run.");
        System.out.print("Output folder is '"+Constants.OutputPath+"'" );
        System.out.print("=====================================================");
            //region Quick Start

        SetLicenseFromFile.run();
        //QuickStart.SetLicenseFromStream.run();
        //QuickStart.SetMeteredLicense.run();
        //HelloWorld.run();

            //endregion

        ////// *** Documents Editor Examples (Un-Comment to run each example demo methods) ***

            //region Here are basic examples

        //Introduction.run();

        //LoadDocument.run();

        //EditDocument.run();

        //SaveDocument.run();

        //CreateDocument.run();

            //endregion

            //region Advanced usage

        //WorkingWithWordProcessing.run();

        //WorkingWithSpreadsheetPasswordProtected.run();

        //WorkingWithSpreadsheetMultiTab.run();

        //WorkingWithDsv.run();

        //WorkingWithPresentations.run();

        //WorkingWithPlainTextDocuments.run();

        //WorkingWithXml.run();

        //ExtractingDocumentInfo.run();

        //SavingEditedDocumentToAllFormats.run();

        //WorkingWithFormats.run();

            //endregion

            //region Working with EditableDocument

        //CreateEditableDocumentFromHtmlFile.run();

        //CreateEditableDocumentFromInnerBody.run();

        //GetHtmlContent.run();

        //GetHtmlContentWithPrefix.run();

        //GetHtmlBodyContent.run();

        //GetHtmlBodyContentWithPrefix.run();

        //GetAllEmbeddedHtmlContent.run();

        //GetExternalCssContent.run();

        //GetExternalCssContentWithPrefix.run();

        //SaveHtmlToFolder.run();

        //SaveHtmlResourcesToFolder.run();

        //WorkingWithResources.run();

        //EditableDocumentAdvancedUsage.run();

            //endregion

        //region Working with FormFieldManager

        //LegacyFormFieldCollection.run();
        //FixInvalidFormFieldCollectionAndSave.run();
        //EditFormFieldCollection.run();
        //RemoveFormFieldCollection.run();

            //endregion

        System.out.print("\r\n\r\n__________________________\r\nAll done. Press any key to exit.");

    }
}
