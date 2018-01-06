package com.akzholus.easydiet.wizard;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class WizardPageAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 4;
    private ViewPager viewPager;

    public WizardPageAdapter(FragmentManager fragmentManager, ViewPager vpPager) {
        super(fragmentManager);
        viewPager = vpPager;
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show Welcome fragment
                return WizardWelcomeFragment.newInstance(viewPager);
            case 1: // Fragment # 1 - This will show step one.
                return WizardStep1Fragment.newInstance(viewPager);
            case 2: // Fragment # 2 - This will show step two.
                return WizardStep2Fragment.newInstance(viewPager);
            case 3: // Fragment # 3 - This will show step three.
                return WizardStep3Fragment.newInstance(viewPager);
            default:
                return null;
        }
    }
}