package com.example.pdf_dl_and_read;

import java.util.List;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btn_dl = (Button) findViewById(R.id.btn_dl);
		Button btn_read = (Button) findViewById(R.id.btn_read);		
		btn_dl.setOnClickListener(new ButtonListener());
        btn_read.setOnClickListener(new ButtonListener());
	}
	
	private class ButtonListener implements OnClickListener{
		@Override
		public void onClick(View v) {			
			Button btn = (Button) v;			
			switch (btn.getId()){
				case R.id.btn_dl:
					DownloadFile("http://www.ex.ua/load/93706495/java.pdf", "java.pdf");
					break;
					
				case R.id.btn_read:	
					openPdf(Environment.DIRECTORY_DOWNLOADS + "java.pdf");
					break;
					
				default:
					break;
			}			
		}		
	}
	
	private void openPdf(String path) {
        try {
            final Intent intent = new Intent(MainActivity.this, PdfViewer.class);
            intent.putExtra(PdfViewer.EXTRA_PDFFILENAME, path);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public static boolean isDownloadManagerAvailable(Context context) {
	    try {
	        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
	            return false;
	        }
	        Intent intent = new Intent(Intent.ACTION_MAIN);
	        intent.addCategory(Intent.CATEGORY_LAUNCHER);
	        intent.setClassName("com.android.providers.downloads.ui", "com.android.providers.downloads.ui.DownloadList");
	        List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent,
	                PackageManager.MATCH_DEFAULT_ONLY);
	        return list.size() > 0;
	    } catch (Exception e) {
	        return false;
	    }
	}
	
	private void DownloadFile(String url, String name)	{
		if(isDownloadManagerAvailable(getApplicationContext()) != false){			
			DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
			request.setDescription(url);
			request.setTitle(name);
			// in order for this if to run, you must use the android 3.2 to compile your app
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			    request.allowScanningByMediaScanner();
			    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
			}
			request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name);
			// get download service and enqueue file
			DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
			manager.enqueue(request);
		}
	}
	
	
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
