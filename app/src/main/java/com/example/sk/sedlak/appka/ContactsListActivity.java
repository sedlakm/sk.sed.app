/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.sk.sedlak.appka;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.sk.sedlak.appka.BuildConfig;
import com.example.sk.sedlak.appka.R;
import com.example.sk.sedlak.util.Utils;

/**
 * FragmentActivity to hold the main {@link Tab3ContactsFragment}. On larger screen devices which
 * can fit two panes also load {@link ContactDetailFragment}.
 */
public class ContactsListActivity extends FragmentActivity implements
        Tab3ContactsFragment.OnContactsInteractionListener {

    private static final String TAG = "ContactsListActivity";

    private ContactDetailFragment mContactDetailFragment;

    private boolean isTwoPaneLayout;

    private boolean isSearchResultView = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (BuildConfig.DEBUG) {
            Utils.enableStrictMode();
        }
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        isTwoPaneLayout = getResources().getBoolean(R.bool.has_two_panes);

        if (Intent.ACTION_SEARCH.equals(getIntent().getAction())) {
            String searchQuery = getIntent().getStringExtra(SearchManager.QUERY);
            Tab3ContactsFragment mContactsListFragment = (Tab3ContactsFragment)
                    getSupportFragmentManager().findFragmentById(R.id.contact_list);

            isSearchResultView = true;
            mContactsListFragment.setSearchQuery(searchQuery);

            String title = getString(R.string.contacts_list_search_results_title, searchQuery);
            setTitle(title);
        }

        if (isTwoPaneLayout) {
            mContactDetailFragment = (ContactDetailFragment)
                    getSupportFragmentManager().findFragmentById(R.id.contact_detail);
        }
    }

    @Override
    public void onContactSelected(Uri contactUri) {
        if (isTwoPaneLayout && mContactDetailFragment != null) {
            mContactDetailFragment.setContact(contactUri);
        } else {
            Intent intent = new Intent(this, ContactDetailActivity.class);
            intent.setData(contactUri);
            startActivity(intent);
        }
    }
    @Override
    public void onSelectionCleared() {
        if (isTwoPaneLayout && mContactDetailFragment != null) {
            mContactDetailFragment.setContact(null);
        }
    }

    @Override
    public boolean onSearchRequested() {
        return !isSearchResultView && super.onSearchRequested();
    }
}
