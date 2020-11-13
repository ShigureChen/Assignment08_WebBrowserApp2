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


        return view;
    }

    public void addNewTab()
    {
        fragments.add(new WebViewFragment());
        viewPager.getAdapter().notifyDataSetChanged();
    }

    public void loadNewPage(String string)
    {


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
}