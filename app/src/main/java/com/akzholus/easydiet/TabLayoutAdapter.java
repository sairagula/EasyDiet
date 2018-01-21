package com.akzholus.easydiet;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.akzholus.easydiet.fragments.ChallengesFragment;

/**
 * Class responsible for deciding which page is show in which tab index. More context:
 * http://techlovejump.com/android-tab-layout-swipe-views-tutorial-example/
 */
public class TabLayoutAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[]{"My Goal", "Challenges", "Reports"};

    public TabLayoutAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 0:
                return new Goals();
            case 1:
                return new ChallengesFragment();
            case 2:
                return new Reports();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}