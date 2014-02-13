package com.example.pdf_dl_and_read;

import net.sf.andpdf.pdfviewer.PdfViewerActivity;
import android.os.Bundle;

public class PdfViewer extends PdfViewerActivity 
{

@Override
public void onCreate(Bundle savedInstanceState) {    
    super.onCreate(savedInstanceState);
}

@Override
public int getNextPageImageResource() {	
	return 0;
}

@Override
public int getPdfPageNumberEditField() {
	return 0;
}

@Override
public int getPdfPageNumberResource() {
	return 0;
}

@Override
public int getPdfPasswordEditField() {
	return 0;
}

@Override
public int getPdfPasswordExitButton() {
	return 0;
}

@Override
public int getPdfPasswordLayoutResource() {
	return 0;
}

@Override
public int getPdfPasswordOkButton() {
	return 0;
}

@Override
public int getPreviousPageImageResource() {
	return 0;
}

@Override
public int getZoomInImageResource() {	
	return 0;
}

@Override
public int getZoomOutImageResource() {
	return 0;
}
}
