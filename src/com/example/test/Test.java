package com.example.test;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Test extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);

		getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);

		getActivity().getActionBar().setHomeButtonEnabled(true);

		View view = inflater.inflate(R.layout.test, container,
				false);

		return view;
	}	
}