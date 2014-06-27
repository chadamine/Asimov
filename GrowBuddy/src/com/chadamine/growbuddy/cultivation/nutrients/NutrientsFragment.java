package com.chadamine.growbuddy.cultivation.nutrients;

import android.app.FragmentManager;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.chadamine.growbuddy.R;
import com.chadamine.growbuddy.database.DatabaseContract.NutrientFormulas;
import com.chadamine.growbuddy.database.DatabaseContract.NutrientSolubilities;
import com.chadamine.growbuddy.database.DatabaseContract.Nutrients;

public class NutrientsFragment extends Fragment {
	
	FragmentActivity activity;
	View view;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		activity = getActivity();
		
		view = inflater.inflate(R.layout.fragment_nutrient_details, container, false);
		
		ImageView calc = (ImageView) view.findViewById(R.id.imageDensityCalc);
		Spinner phase;
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
	
	@Override
	public void onPause() {
		super.onPause();
		saveState();
	}
	
	private void saveState() {
		Uri nutrientsUri = Nutrients.CONTENT_URI;
		Uri nutrientSolubilityUri = NutrientSolubilities.CONTENT_URI;
		Uri nutrientFormulasUri = NutrientFormulas.CONTENT_URI;
		
		String mfctr =  view.findViewById(R.id.editNutrientManufacturer).toString();
		
		String pdct = view.findViewById(R.id.editNutrientProduct).toString();
		String type = view.findViewById(R.id.spinnerNutrientType).toString();
		String mol = view.findViewById(R.id.editNutrientMolWeight).toString();
		String density = view.findViewById(R.id.editNutrientDensity).toString();
		
		String molFormula = view.findViewById(R.id.editNutrientMolFormula).toString();
		String solubility = "solubility";
		
		ContentValues nutrientValues = new ContentValues();
		ContentValues formulaValues = new ContentValues();
		ContentValues solubilityValues = new ContentValues();
		
		if(pdct.length() > 0 ) 
			nutrientValues.put(Nutrients.COL_PRODUCT, pdct);
		if(mfctr.length() > 0) 
			nutrientValues.put(Nutrients.COL_MANUFACTURER, mfctr);
		if(type.length() > 0)
			nutrientValues.put(Nutrients.COL_TYPE, type);
		if(mol.length() > 0) 
			nutrientValues.put(Nutrients.COL_MOL_WEIGHT, mol);
		if(density.length() > 0)
			nutrientValues.put(Nutrients.COL_DENSITY, density);
		
		
	
		
		if(solubility.length() > 0) {
			int sol = -1;
			
			try {
				sol =  Integer.parseInt(solubility);
			} catch (Exception e) {
				Toast.makeText(activity, "Failure to find solubility temperature", Toast.LENGTH_SHORT);
			}
			
			if(sol != -1) 
				solubilityValues.put(NutrientSolubilities.COL_0, sol);
		
			
		}
			//nutrientsUri = 
		if(nutrientValues != null) 
			getActivity()
				.getContentResolver()
				.insert(nutrientsUri, nutrientValues);
		
		if(formulaValues != null) 
			getActivity()
				.getContentResolver()
				.insert(nutrientFormulasUri, formulaValues);
		
		if(solubilityValues != null)
			getActivity()
				.getContentResolver()
				.insert(nutrientSolubilityUri, solubilityValues);
		
		Toast.makeText(getActivity(), "Nutrient Added to Database: " + nutrientValues.toString() + "; " + formulaValues.toString() + "; " + solubilityValues.toString(), Toast.LENGTH_SHORT).show();
		
		
	}
}