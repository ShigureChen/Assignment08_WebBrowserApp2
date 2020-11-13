package edu.temple.webbrowserapp_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class BrowserActivity extends AppCompatActivity implements ViewPagerFragment.ViewPagerFragmentListener, PageControlFragment.PageControlFragmentListener, BrowserControlFragment.BrowserControlFragmentListener, WebViewFragment.WebViewFragmentListener {

    PageControlFragment pcf;
    ViewPagerFragment vpf;
    BrowserControlFragment bcf;
    PageListFragment plf;
    TextView textView;
    FragmentManager fm;
    ArrayList<WebViewFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int config = getResources().getConfiguration().orientation;

        textView = findViewById(R.id.textView);
        textView.setText("Press + button to add a new tab");
        textView.setTextSize(14);

        fm = getSupportFragmentManager();

        pcf = (PageControlFragment) fm.findFragmentById(R.id.container_page_control);
        if(pcf == null)
        {
            pcf = new PageControlFragment();
            fm.beginTransaction().add(R.id.container_page_control, pcf).commit();
        }

        bcf = (BrowserControlFragment) fm.findFragmentById((R.id.container_browser_control));
        if (bcf == null) {
            bcf = new BrowserControlFragment();
            fm.beginTransaction().add(R.id.container_browser_control, bcf).commit();
        }

        vpf = (ViewPagerFragment) fm.findFragmentById(R.id.container_page_list);
        if(vpf == null)
        {
            vpf = new ViewPagerFragment();
            fm.beginTransaction().add(R.id.container_view_pager, vpf).commit();
        }

        plf = (PageListFragment) fm.findFragmentById(R.id.container_page_list);
        if (plf == null && config == Configuration.ORIENTATION_LANDSCAPE) {
            plf = new PageListFragment();
            fm.beginTransaction().add(R.id.container_page_list, plf).commit();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void addPage() {
        vpf.addNewTab();
        this.textView.setText("");
    }

    //WebView to PageControl
    @Override
    public void onDataSend(String string, String pageTitle) {
        this.setTitle(pageTitle);
        pcf.updateText(string);
    }

    //PageControl to ViewPager
    @Override
    public void onURLSend(String string) {

        final String head = "https://";

        if(!string.substring(0,head.length()).equals(head))
        {
            string = head.concat(string);
        }
        vpf.loadNewPage(string);
    }

    @Override
    public void onNextButtonClick() {
        vpf.goForward();
    }

    @Override
    public void onBackButtonClick() {
        vpf.goBack();
    }

    @Override
    public void onTabChange(String string, String pageTitle) {
        pcf.updateText(string);
        this.setTitle(pageTitle);
    }
}