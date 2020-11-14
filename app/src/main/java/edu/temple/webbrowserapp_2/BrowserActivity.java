package edu.temple.webbrowserapp_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class BrowserActivity extends AppCompatActivity implements PageListFragment.PageListFragmentListener,
        ViewPagerFragment.ViewPagerFragmentListener,
        PageControlFragment.PageControlFragmentListener,
        BrowserControlFragment.BrowserControlFragmentListener,
        WebViewFragment.WebViewFragmentListener {

    PageControlFragment pcf;
    ViewPagerFragment vpf;
    BrowserControlFragment bcf;
    PageListFragment plf;
    TextView textView;
    FragmentManager fm;
    ArrayList<String> fragmentsList;
    int config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        config = getResources().getConfiguration().orientation;
        this.setTitle("WedBrowser");

        textView = findViewById(R.id.textView);
        textView.setText("Press + button to add a new tab");
        textView.setTextSize(14);

        fm = getSupportFragmentManager();

        pcf = (PageControlFragment) fm.findFragmentById(R.id.container_page_control);
        if(pcf == null)
        {
            pcf = new PageControlFragment();
            fm.beginTransaction().add(R.id.container_page_control, pcf).commitNow();
        }

        bcf = (BrowserControlFragment) fm.findFragmentById((R.id.container_browser_control));
        if (bcf == null) {
            bcf = new BrowserControlFragment();
            fm.beginTransaction().add(R.id.container_browser_control, bcf).commitNow();
        }

        vpf = (ViewPagerFragment) fm.findFragmentById(R.id.container_view_pager);
        if(vpf == null)
        {
            vpf = new ViewPagerFragment();
            fm.beginTransaction().add(R.id.container_view_pager, vpf).commitNow();
        }

        plf = (PageListFragment) fm.findFragmentById(R.id.container_page_list);
        if (plf == null && config == Configuration.ORIENTATION_LANDSCAPE) {
            plf = new PageListFragment();
            fm.beginTransaction().add(R.id.container_page_list, plf).commitNow();
        }

    }

    //BrowserControl to ViewPager and PageList
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


    //ViewPager to PageControl
    @Override
    public void onTabChange(String string, String pageTitle) {
        pcf.updateText(string);
        this.setTitle(pageTitle);
    }


    //PageList to ViewPager
    @Override
    public void onTabClicked(int position) {
        vpf.moveToTab(position);
    }

    //ViewPager to PageList
    @Override
    public void onTitleSend(String pageTitle, int position)
    {

    }
}