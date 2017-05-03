package liuliangqi.fragmentpractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

/**
 * Created by liuliangqi on 2017/5/2.
 */

public class Ingredients extends Fragment {
    private static final String KEY_CHECKEDBOXES = "KEY_CHECKEDBOXES";
    private CheckBox[] mCheckBoxes;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ingredient_fragment, container, false);
        int index = getArguments().getInt(ViewPagerFragment.RECIPES_INDEX);


        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ingredientsLayout);
        String[] ingredients = Recipes.ingredients[index].split("`");
        mCheckBoxes = new CheckBox[ingredients.length];
        boolean[] checkedBoxes = new boolean[mCheckBoxes.length];
        if(savedInstanceState != null && savedInstanceState.getBooleanArray(KEY_CHECKEDBOXES) != null){
            checkedBoxes = savedInstanceState.getBooleanArray(KEY_CHECKEDBOXES);
        }
        setupCheckboxes(ingredients, linearLayout, checkedBoxes);
        return view;
    }


    private void setupCheckboxes(String[] ingredients, ViewGroup container, boolean[] checkBoxes){
        int i = 0;
        for(String str : ingredients){
            mCheckBoxes[i] = new CheckBox(getActivity());
            mCheckBoxes[i].setPadding(8, 16, 8, 16);
            mCheckBoxes[i].setTextSize(20f);
            mCheckBoxes[i].setText(str);
            if(checkBoxes[i]){
                mCheckBoxes[i].toggle();
            }
            container.addView(mCheckBoxes[i]);
            i++;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        boolean[] stateOfCheckBoxes = new boolean[mCheckBoxes.length];
        int i = 0;
        for(CheckBox checkBox : mCheckBoxes){
            stateOfCheckBoxes[i] = checkBox.isChecked();
            i++;
        }
        outState.putBooleanArray(KEY_CHECKEDBOXES, stateOfCheckBoxes);
        super.onSaveInstanceState(outState);
    }
}
