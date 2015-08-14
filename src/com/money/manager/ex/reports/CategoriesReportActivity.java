/*
 * Copyright (C) 2012-2015 The Android Money Manager Ex Project Team
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.money.manager.ex.reports;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.money.manager.ex.R;
import com.money.manager.ex.common.BaseFragmentActivity;

public class CategoriesReportActivity
        extends BaseFragmentActivity {
    
    public static final String REPORT_FILTERS = "CategoriesReportActivity:Filter";
    public static final String REPORT_TITLE = "CategoriesReportActivity:Title";
    public boolean mIsDualPanel = false;
    public String mFilter = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent() != null) {
            if (!TextUtils.isEmpty(getIntent().getStringExtra(REPORT_FILTERS))) {
                mFilter = getIntent().getStringExtra(REPORT_FILTERS);
            }
            if (!TextUtils.isEmpty(getIntent().getStringExtra(REPORT_TITLE))) {
                setTitle(getIntent().getStringExtra(REPORT_TITLE));
            }
        }

        setContentView(R.layout.report_chart_fragments_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            // set actionbar
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //check if is dual panel
        mIsDualPanel = findViewById(R.id.fragmentChart) != null;

        //create a fragment
        CategoriesReportFragment fragment = new CategoriesReportFragment();
        FragmentManager fm = getSupportFragmentManager();
        //insert fragment
        if (fm.findFragmentById(R.id.fragmentContent) == null) {
            fm.beginTransaction()
                    .add(R.id.fragmentContent, fragment, CategoriesReportFragment.class.getSimpleName())
                    .commit();
        }
    }
}