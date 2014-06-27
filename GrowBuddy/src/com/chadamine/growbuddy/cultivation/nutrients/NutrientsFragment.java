package com.chadamine.growbuddy.cultivation.nutrients;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.chadamine.growbuddy.BlankFragment;
import com.chadamine.growbuddy.R;

public class NutrientsFragment extends Fragment {
	
	FragmentActivity activity;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		activity = getActivity();
		
		View view = inflater.inflate(R.layout.fragment_nutrient_details, container, false);

		EditText mfctr = (EditText) view.findViewById(R.id.editNutrientManufacturer);
		EditText pdct = (EditText) view.findViewById(R.id.editNutrientProduct);
		Spinner type = (Spinner) view.findViewById(R.id.spinnerNutrientType);
		EditText mol = (EditText) view.findViewById(R.id.editNutrientMolWeight);
		EditText density = (EditText) view.findViewById(R.id.editNutrientDensity);
		ImageView calc = (ImageView) view.findViewById(R.id.imageDensityCalc);
		Spinner phase;
		EditText molFormula = (EditText) view.findViewById(R.id.editNutrientMolFormula);
		
		Button save = (Button) view.findViewById(R.id.buttonNutrientSubmit);
		save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//saveState();
				
				getActivity()
				.getSupportFragmentManager()
				//.beginTransaction()
				//.replace(R.id.frameDetails, new BlankFragment())
				//.addToBackStack("blankFragment")
				//.commit();
				.popBackStack("nutrientDetails", FragmentManager.POP_BACK_STACK_INCLUSIVE);
			}
		});
		return view;
	}
}
