/*
 * Copyright 2016 Andrew Reitz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.andrewreitz.velcro;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.List;

import static com.andrewreitz.velcro.internal.Preconditions.checkNotNull;

/**
 * A simple implementation of a {@link FragmentStatePagerAdapter} that provides fragments
 * and their titles, to use with {@link android.support.v4.view.ViewPager}
 */
public class SimpleFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

  private final Context context;

  private final List<Fragment> fragments = new ArrayList<Fragment>();
  private final List<String> fragmentTitles = new ArrayList<String>();

  /**
   * Constructor.
   *
   * @param context         The application context. Used to get strings resources.
   * @param fragmentManager The fragment manager that is used for displaying fragments.
   */
  public SimpleFragmentStatePagerAdapter(Context context, FragmentManager fragmentManager) {
    super(checkNotNull(fragmentManager, "fragmentManager == null"));
    this.context = checkNotNull(context, "context == null").getApplicationContext();
  }

  /**
   * Adds a fragment to this adapter. Fragments will be displayed in the order they are added.
   *
   * @param fragment The fragment to add.
   * @param titleId  The string resource groupId of the title that will be used for displaying
   *                 the title of the fragment.
   * @throws NullPointerException if fragment is null.
   */
  public void addFragment(Fragment fragment, @StringRes int titleId) {
    checkNotNull(fragment, "fragment == null");
    String title = context.getResources().getString(titleId);

    fragments.add(fragment);
    fragmentTitles.add(title);
  }

  /**
   * Adds a fragment to this adapter. Fragments will be displayed in the order they are added.
   *
   * @param fragment The fragment to add.
   * @param title    The title to be displayed for the fragment.
   * @throws NullPointerException if fragment or title is null.
   */
  public void addFragment(Fragment fragment, String title) {
    checkNotNull(fragment, "fragment == null");
    checkNotNull(title, "title == null");

    fragments.add(fragment);
    fragmentTitles.add(title);
  }

  @Override public Fragment getItem(int position) {
    return fragments.get(position);
  }

  @Override public int getCount() {
    return fragments.size();
  }

  @Override public CharSequence getPageTitle(int position) {
    return fragmentTitles.get(position);
  }
}
