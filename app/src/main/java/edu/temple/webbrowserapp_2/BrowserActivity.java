package edu.temple.webbrowserapp_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

public class BrowserActivity extends AppCompatActivity implements ViewPagerFragment.ViewPagerFragmentListener, PageControlFragment.PageControlFragmentListener, BrowserControlFragment.BrowserControlFragmentListener, WebViewFragment.WebViewFragmentListener {

    PageControlFragment pcf;
    ViewPagerFragment vpf;
    BrowserControlFragment bcf;
    PageListFragment plf;
    TextView textView;
    FragmentManager fm;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView.setText("Press + button to add a new tab");
        textView.setTextSize(14);
        pcf = new PageControlFragment();
        vpf = new ViewPagerFragment();
        bcf = new BrowserControlFragment();
        plf = new PageListFragment();

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.add(R.id.container_view_pager, vpf)
                .add(R.id.container_browser_control, bcf)
                .add(R.id.container_page_control, pcf)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        String url = pcf.editText.getText().toString();
        String pageTitle = this.getTitle().toString();
        savedInstanceState.putString("url", url);
        savedInstanceState.putString("pageTitle", pageTitle);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        savedInstanceState.getString("url");
        savedInstanceState.getString("pageTitle");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            fm = getSupportFragmentManager();
            ft = fm.beginTransaction();
            ft.replace(R.id.container_view_pager, vpf)
                    .replace(R.id.container_browser_control, bcf)
                    .replace(R.id.container_page_control, pcf)
                    .replace(R.id.container_page_list, plf)
                    .commit();
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            fm = getSupportFragmentManager();
            ft = fm.beginTransaction();
            ft.replace(R.id.container_view_pager, vpf)
                    .replace(R.id.container_browser_control, bcf)
                    .replace(R.id.container_page_control, pcf)
                    .commit();
        }
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
}