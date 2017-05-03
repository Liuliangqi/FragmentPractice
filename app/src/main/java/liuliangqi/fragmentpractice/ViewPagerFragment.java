package liuliangqi.fragmentpractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by liuliangqi on 2017/5/2.
 */

public class ViewPagerFragment extends Fragment {
    public static final String RECIPES_INDEX = "recipes_index";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        int index = getArguments().getInt(RECIPES_INDEX);
        getActivity().setTitle(Recipes.names[index]);
        View view = inflater.inflate(R.layout.view_pager_fragment, container, false);
        final Ingredients ingredients = new Ingredients();
        Bundle bundle = new Bundle();
        bundle.putInt(RECIPES_INDEX, index);
        ingredients.setArguments(bundle);
        final Directions directions = new Directions();

        Bundle direcBundle = new Bundle();
        bundle.putInt(RECIPES_INDEX, index);
        ingredients.setArguments(direcBundle);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return position == 0 ? ingredients : directions;
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return position == 0 ? "INGREDIENTS" : "DIRECTIONS";
            }
        });


        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.view_tab_layout);
        tabLayout.setupWithViewPager(viewPager);


        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().setTitle(getResources().getString(R.string.app_name));
    }
}
