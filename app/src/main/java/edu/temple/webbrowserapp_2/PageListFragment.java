package edu.temple.webbrowserapp_2;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class PageListFragment extends Fragment {

    PageListFragmentListener listener;
    ListView listView;
    ArrayAdapter adapter;
    ArrayList<String> fragments;

    interface PageListFragmentListener
    {
        void onTabClicked(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_list, container, false);



        return view;
    }

    public void onTabAdded(String string)
    {

    }

    public void updatePageTitle(String pageTitle, int position)
    {
        fragments.set(position, pageTitle);
    }


    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PageListFragmentListener) {
            listener = (PageListFragmentListener) context;
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