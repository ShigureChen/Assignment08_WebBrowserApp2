package edu.temple.webbrowserapp_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class BrowserActivity extends AppCompatActivity implements ViewPagerFragment.ViewPagerFragmentListener, PageControlFragment.PageControlFragmentListener, BrowserControlFragment.BrowserControlFragmentListener, WebViewFragment.WebViewFragmentListener {

    PageControlFragment pcf;
    ViewPagerFragment vpf;
    BrowserControlFragment bcf;
    WebViewFragment wbf;
    FragmentManager fm;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void addPage() {
        vpf.addPage();
    }

    //WebView to PageControl
    @Override
    public void onDataSend(String string) {
        pcf.updateText(string);
    }

    //PageControl to WebView
    @Override
    public void onURLSend(String string) {

    }

    @Override
    public void onNextButtonClick() {

    }

    @Override
    public void onBackButtonClick() {

    }
}