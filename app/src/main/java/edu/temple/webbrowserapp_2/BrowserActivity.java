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
    String pageTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(pageTitle != null)
        {
            this.setTitle(pageTitle);
        }

        pcf = new PageControlFragment();
        vpf = new ViewPagerFragment();
        bcf = new BrowserControlFragment();

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.add(R.id.container_view_pager, vpf)
        .add(R.id.container_browser_control, bcf)
        .add(R.id.container_page_control, pcf)
                .commit();
    }




    @Override
    public void addPage() {
        vpf.addNewTab();
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
}