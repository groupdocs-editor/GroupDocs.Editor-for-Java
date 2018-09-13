package com.groupdocs.editor.examples;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

import com.groupdocs.editor.EditorHandler;
import com.groupdocs.editor.InputHtmlDocument;
import com.groupdocs.editor.OutputHtmlDocument;
import com.groupdocs.editor.htmlcss.resources.fonts.FontResourceBase;
import com.groupdocs.editor.htmlcss.resources.images.IImageResource;
import com.groupdocs.editor.htmlcss.resources.images.raster.RasterImageResourceBase;
import com.groupdocs.editor.htmlcss.resources.textual.CssText;
import com.groupdocs.editor.words.htmltowords.WordFormats;
import com.groupdocs.editor.words.htmltowords.WordsSaveOptions;

public class HTMLDOMEditor {
	public static void getHTMLContents(String fileName) throws Throwable{
		//ExStart:getHTMLContents
		InputStream inputStream = new FileInputStream(CommonUtilities.getStoragePath(fileName));
		try {
		    InputHtmlDocument htmlDoc = EditorHandler.toHtml(inputStream);
		    String bodyContent = htmlDoc.getContent();
		    System.out.println(bodyContent);
		} catch (Exception ex){
		     ex.getMessage();
		}
		//ExEnd:getHTMLContents
	}
	
	public static void getHTMLContentsWithExternalResources(String fileName) throws Throwable{
		//ExStart:getHTMLContentsWithExternalResources
		InputStream inputStream = new FileInputStream(CommonUtilities.getStoragePath(fileName));
		try {
		    InputHtmlDocument htmlDoc = EditorHandler.toHtml(inputStream);
		    String externalResourcePrefix = "GetResource?htmlDocumentFolderName=" + CommonUtilities.resourceFolder + "&resourceFilename=Picture 3.png&";
		    // Obtain HTML document content
		    String bodyContent = htmlDoc.getContent(externalResourcePrefix);
		    System.out.println(bodyContent);
		} catch (Exception ex){
		     ex.getMessage();
		}
		//ExEnd:getHTMLContentsWithExternalResources
	}
	
	public static void getHTMLContentsWithEmbeddedResources(String fileName) throws Throwable{
		//ExStart:getHTMLContentsWithEmbeddedResources
		InputStream inputStream = new FileInputStream(CommonUtilities.getStoragePath(fileName));
		try {
		    InputHtmlDocument htmlDoc = EditorHandler.toHtml(inputStream);
		   // Obtain HTML document content
		    String cssContent = htmlDoc.getEmbeddedHtml();
		    System.out.println(cssContent);
		} catch (Exception ex){
		     ex.getMessage();
		}
		//ExEnd:getHTMLContentsWithEmbeddedResources
	}
	
	public static void getHTMLBodyTagContents(String fileName) throws Throwable{
		//ExStart:getHTMLBodyTagContents
		InputStream inputStream = new FileInputStream(CommonUtilities.getStoragePath(fileName));
		try {
		    InputHtmlDocument htmlDoc = EditorHandler.toHtml(inputStream);
		 // Obtain HTML document  body content
		    String bodyContent = htmlDoc.getBodyContent();
		    System.out.println(bodyContent);
		} catch (Exception ex){
		     ex.getMessage();
		}
		//ExEnd:getHTMLBodyTagContents
	}
	
	public static void getHTMLBodyTagContentsWithExternalResources(String fileName) throws Throwable{
		//ExStart:getHTMLBodyTagContentsWithExternalResources
		InputStream inputStream = new FileInputStream(CommonUtilities.getStoragePath(fileName));
		try {
		    InputHtmlDocument htmlDoc = EditorHandler.toHtml(inputStream);
		    String externalResourcePrefix = "GetResource?htmlDocumentFolderName=" + CommonUtilities.resourceFolder + "&resourceFilename=Picture 3.png";
		    // Obtain HTML document  body content
		    String bodyContent = htmlDoc.getBodyContent(externalResourcePrefix);
		    System.out.println(bodyContent);
		} catch (Exception ex){
		     ex.getMessage();
		}
		//ExEnd:getHTMLBodyTagContentsWithExternalResources
	}	
	
	public static void getHTMLExternalCSSContents(String fileName) throws Throwable{
		//ExStart:getHTMLExternalCSSContents
		InputStream inputStream = new FileInputStream(CommonUtilities.getStoragePath(fileName));
		try {
		    InputHtmlDocument htmlDoc = EditorHandler.toHtml(inputStream);
		    // Obtain CSS  content
		    for (String cssContent : htmlDoc.getCssContent())
		    {
		    	System.out.println(cssContent);
		    }
		} catch (Exception ex){
		     ex.getMessage();
		}
		//ExEnd:getHTMLExternalCSSContents
	}
	
	public static void getHTMLExternalCSSContentsWithExternalResources(String fileName) throws Throwable{
		//ExStart:getHTMLExternalCSSContentsWithExternalResources
		InputStream inputStream = new FileInputStream(CommonUtilities.getStoragePath(fileName));
		try {
		    InputHtmlDocument htmlDoc = EditorHandler.toHtml(inputStream);
		    String externalResourcePrefix = "GetResource?htmlDocumentFolderName=" + CommonUtilities.resourceFolder + "&resourceFilename=Picture 3.png";
		    // Obtain CSS document content		    
		    for (String bodyContent : htmlDoc.getCssContent(externalResourcePrefix))
		    {
		    	System.out.println(bodyContent);
		    }
		} catch (Exception ex){
		     ex.getMessage();
		}
		//ExEnd:getHTMLExternalCSSContentsWithExternalResources
	}
	
	public static void saveHTMLDocument(String fileName) throws Throwable{
		//ExStart:saveHTMLDocument
		InputStream inputStream = new FileInputStream(CommonUtilities.getStoragePath(fileName));
		try {
		    InputHtmlDocument htmlDoc = EditorHandler.toHtml(inputStream);
		    // Obtain HTML document content
		    htmlDoc.save(CommonUtilities.getOutputPath("output.html"));
		} catch (Exception ex){
		     ex.getMessage();
		}
		//ExEnd:saveHTMLDocument
	}
	
	public static void saveHTMLDocumentWithResourcesFolder(String fileName) throws Throwable{
		//ExStart:saveHTMLDocumentWithResourcesFolder
		// For complete examples and data files, please go to https://github.com/groupdocs-editor/GroupDocs.Editor-for-Java
		InputStream inputStream = new FileInputStream(CommonUtilities.getStoragePath(fileName));
		try {
		    InputHtmlDocument htmlDoc = EditorHandler.toHtml(inputStream);
		    // Obtain HTML document content
		    htmlDoc.save(CommonUtilities.getOutputPath("output.html"), CommonUtilities.getOutputPathWithResource(""));
		} catch (Exception ex){
		     ex.getMessage();
		}
		//ExEnd:saveHTMLDocumentWithResourcesFolder
	}
	
	public static void workingWithHTMLResourcesAndCSS(String fileName) throws Throwable{
		//ExStart:workingWithHTMLResourcesAndCSS
		InputStream inputStream = new FileInputStream(CommonUtilities.getStoragePath(fileName));
		try {
		    InputHtmlDocument htmlDoc = EditorHandler.toHtml(inputStream);
		 
		    for (FontResourceBase fontResource : htmlDoc.getFontResources()){
		        System.out.println(fontResource.getFilenameWithExtension());
		        System.out.println(fontResource.getName());
		        System.out.println(fontResource.getByteContent()); //returns a java.io.InputStream instance with actual content of the image as a byte stream
		        System.out.println(fontResource.getTextContent()); //returns a java.lang.String instance with actual content of the font as a text in base-64 encoding
		 
		        String pathToResource =  CommonUtilities.getOutputPathWithResource(fontResource.getFilenameWithExtension());
		        fontResource.save(pathToResource);
		    }
		    for (IImageResource imageResource : htmlDoc.getImageResources())
		    {
		        System.out.println(imageResource.getFilenameWithExtension());
		        System.out.println(imageResource.getByteContent()); //returns a java.io.InputStream instance with actual content of the image as a byte stream
		        System.out.println(imageResource.getName());
		        System.out.println(imageResource.getTextContent()); //returns a java.lang.String instance with actual content of the image as a text in base-64 encoding
		        System.out.println(imageResource.getLinearDimensions().getHeight());
		        System.out.println(imageResource.getLinearDimensions().getWidth());
		        System.out.println(imageResource.getLinearDimensions().isSquare());
		 
		        String pathToResource =  CommonUtilities.getOutputPathWithResource(imageResource.getFilenameWithExtension());
		        imageResource.save(pathToResource);
		    }
		 
		    for(CssText css : htmlDoc.getCss()){
		    	System.out.println(css.getFilenameWithExtension());
			    System.out.println(css.getByteContent()); //returns a java.io.InputStream instance with actual content of the stylesheet as a byte stream (UTF-8 is a default encoding)
			    System.out.println(css.getName());
			    System.out.println(css.getTextContent()); //returns a java.lang.String instance with actual content of the stylesheet as a simple text
			    System.out.println(css.getEncoding());
			 
			    String pathToCss = CommonUtilities.getOutputPathWithResource(css.getFilenameWithExtension());
			    css.save(pathToCss);
		    }		 
		    // saving output html file.
		    htmlDoc.save(CommonUtilities.getOutputPath("output.html"),CommonUtilities.getOutputPathWithResource(""));
		 
		} catch (Exception ex){
		    ex.getMessage();
		}
		//ExEnd:workingWithHTMLResourcesAndCSS
	}
	
	public static void saveToWordsDocument(String fileName) throws Throwable{
		//ExStart:saveToWordsDocument
		InputStream inputStream = new FileInputStream(CommonUtilities.storagePath + File.separator + fileName);
		try {
		    InputHtmlDocument htmlDoc = EditorHandler.toHtml(inputStream);
		    String htmlContent = htmlDoc.getContent();
		    OutputHtmlDocument editedHtmlDoc = OutputHtmlDocument.fromMarkup(htmlContent, CommonUtilities.getStoragePathWithResource(""));
		    WordsSaveOptions saveOptions = new WordsSaveOptions();
		    OutputStream out = new FileOutputStream(CommonUtilities.getOutputPath("output.docx"));
		    EditorHandler.toDocument(editedHtmlDoc, out, saveOptions);
		} catch (Exception ex){
		     ex.getMessage();
		}
		//ExEnd:saveToWordsDocument
	}
	
	public static void saveHTMLDOMFromFileToWordsDocument(String fileName) throws Throwable{
		//ExStart:saveHTMLDOMFromFileToWordsDocument
		try {
		    OutputHtmlDocument editedHtmlDoc = OutputHtmlDocument.fromFile(CommonUtilities.getStoragePath(fileName), CommonUtilities.getStoragePathWithResource(""));
		    WordsSaveOptions saveOptions = new WordsSaveOptions();
		    OutputStream out = new FileOutputStream(CommonUtilities.getOutputPath("output.docx"));
		    EditorHandler.toDocument(editedHtmlDoc, out, saveOptions);
		} catch (Exception ex){
		     ex.getMessage();
		}
		//ExEnd:saveHTMLDOMFromFileToWordsDocument
	}
	
	public static void saveHTMLDOMToRTFDocument(String fileName) throws Throwable{
		//ExStart:saveHTMLDOMToRTFDocument
		InputStream inputStream = new FileInputStream(CommonUtilities.getStoragePath(fileName));
		 
		try {
		    InputHtmlDocument htmlDoc = EditorHandler.toHtml(inputStream);
		    // Initialize with HTML markup of the edited document
		    String htmlContent = htmlDoc.getContent();
		    OutputHtmlDocument editedHtmlDoc = OutputHtmlDocument.fromMarkup(htmlContent, CommonUtilities.getStoragePathWithResource(""));
		    WordsSaveOptions saveOptions = new WordsSaveOptions();
		    saveOptions.setOutputFormat(WordFormats.Rtf);
		    OutputStream out = new FileOutputStream(CommonUtilities.getOutputPath("output.rtf"));
		    EditorHandler.toDocument(editedHtmlDoc, out, saveOptions);    
		} catch (Exception ex){
		    ex.getMessage();
		}
		//ExEnd:saveHTMLDOMToRTFDocument
	}
	
	public static void saveToWordsDocumentWithOptions(String fileName) throws Throwable{
		//ExStart:saveToWordsDocumentWithOptions
		InputStream inputStream = new FileInputStream(CommonUtilities.getStoragePath(fileName));
		 
		try {
		    InputHtmlDocument htmlDoc = EditorHandler.toHtml(inputStream);
		    // Initialize with HTML markup of the edited document
		    String htmlContent = htmlDoc.getContent();
		    OutputHtmlDocument editedHtmlDoc = OutputHtmlDocument.fromMarkup(htmlContent, CommonUtilities.getStoragePathWithResource(""));
		    WordsSaveOptions saveOptions = new WordsSaveOptions(WordFormats.Docx, "12345678");
		    OutputStream out = new FileOutputStream(CommonUtilities.getOutputPath("output.docx"));
		    saveOptions.setLocale(Locale.getDefault());
		    saveOptions.setLocaleBi(Locale.getDefault());
		    saveOptions.setLocaleFarEast(Locale.getDefault());
		    EditorHandler.toDocument(editedHtmlDoc, out, saveOptions);   
		} catch (Exception ex){
		    ex.getMessage();
		}
		//ExEnd:saveToWordsDocumentWithOptions
	}
	
}
