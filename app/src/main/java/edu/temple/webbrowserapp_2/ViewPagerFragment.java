package edu.temple.webbrowserapp_2;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ViewPagerFragment extends Fragment {

    ViewPager2 viewPager;
    ViewPagerFragmentListener listener;
    ArrayList<WebViewFragment> fragments;

    interface ViewPagerFragmentListener
    {
        void onTabChange(String string, String pageTitle);
        void onTitleSend(String pageTitle, int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        fragments = new ArrayList<>();
        viewPager = view.findViewById(R.id.viewPager);

        viewPager.setAdapter(new FragmentStateAdapter(getActivity()) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragments.get(position);
            }

            @Override
            public int getItemCount() {
                return fragments.size();
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                String string = new String();
                String pageTitle = new String();
                int index = viewPager.getCurrentItem();
                string = fragments.get(index).webView.getUrl().toString();
                pageTitle = fragments.get(index).webView.getTitle().toString();
                listener.onTabChange(string, pageTitle);
            }
        });

        return view;
    }

    public void addNewTab()
    {
        fragments.add(new WebViewFragment());
        viewPager.getAdapter().notifyDataSetChanged();
        viewPager.setCurrentItem(fragments.size());
    }

    public void loadNewPage(String string)
    {
        int index = viewPager.getCurrentItem();
        fragments.get(index).loadUrl(string);
    }

    public void goBack()
    {
        int index = viewPager.getCurrentItem();
        fragments.get(index).goBack();
    }

    public void goForward()
    {
        int index = viewPager.getCurrentItem();
        fragments.get(index).goForward();
    }

    public void moveToTab(int position)
    {
        viewPager.setCurrentItem(position);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ViewPagerFragmentListener) {
            listener = (ViewPagerFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}