package edu.temple.webbrowserapp_2;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewFragment extends Fragment {

    WebView webView;
    String string;
    String pageTitle;
    WebViewFragmentListener listener;

    interface WebViewFragmentListener
    {
        void onDataSend(String string, String pageTitle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);
        webView = view.findViewById(R.id.webView);
        Bundle bundle = this.getArguments();

        if(bundle != null)
        {
            string = bundle.getString("url");
        }
        else
        {
            string = "https://www.google.com";
        }

        webView.loadUrl(string);


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            public void onPageFinished(WebView view, String url)
            {
                super.onPageFinished(view, url);
                string = webView.getUrl();
                pageTitle = webView.getTitle();
                listener.onDataSend(string, pageTitle);
            }
        });
        return view;
    }

    public void loadUrl(String string)
    {
        webView.loadUrl(string);
    }

    public void goBack()
    {
        webView.goBack();
    }

    public void goForward()
    {
        webView.goForward();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof WebViewFragmentListener) {
            listener = (WebViewFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentListener");
        }

    }
}