/*
 * Copyright (C) 2014 The Android Open Source Project
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

package com.example.android.activityscenetransitionbasic;


import com.example.android.activityscenetransitionbasic.adapter.GridAdapter;
import com.example.android.activityscenetransitionbasic.model.Item;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;



/**
 * Our main Activity in this sample. Displays a grid of items which an image and title. When the
 * user clicks on an item, {@link DetailActivity} is launched, using the Activity Scene Transitions
 * framework to animatedly do so.
 */
public class MainActivity extends Activity {//implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private GridAdapter mAdapter;
    final SwipeDetector swipeDetector = new SwipeDetector();
    SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS itemsDb (name TEXT, author TEXT, file TEXT, id INTEGER)");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid);

        // Setup the GridView and set the adapter
        mListView = (ListView) findViewById(R.id.mListView);
       // mGridView.setOnItemClickListener(this);
        mAdapter = new GridAdapter();
        mListView.setAdapter(mAdapter);
        mListView.setOnTouchListener(swipeDetector);

        GetItemsFromBd();

        SetupGridViewListener();
    }

    private void AddItemToBd(Item item)
    {
        String name =  item.getName();
        String author = item.getAuthor();
        String fileName = item.getPhotoUrl();
        int id = item.getId();
        db.execSQL("INSERT INTO itemsDb VALUES ('" + name + "','" + author + "','" + fileName + "'," + id + ");");
        //db.execSQL("INSERT INTO users VALUES (@name, @author, @fileName,@id);");
    }

    private void GetItemsFromBd()
    {
        Cursor query = db.rawQuery("SELECT * FROM itemsDb;", null);
        String name = "";
        String author = "";
        String fileName = "";

        if(query.moveToFirst()){
            do {
                name = query.getString(0);
                author = query.getString(1);
                fileName = query.getString(2);
                //int id = query.getInt(3);
                Item item = new Item(name, author, fileName);
                Item.items.add(item);
                mAdapter.notifyDataSetChanged();
            }
            while(query.moveToNext());
            query.close();
           // db.close();
        }
    }

    public void onAddItem(View v) {
       EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
       String itemText = etNewItem.getText().toString();
       etNewItem.setText("");

       Item newItem = new Item(itemText,"God","caterpillar.jpg");

       /*Add to Item list*/
       Item.items.add(newItem);
       mAdapter.notifyDataSetChanged();

       /*Add to BD*/
       AddItemToBd(newItem);

    }

    private Animation getDeleteAnimation(float fromX, float toX, int position)
    {
        Animation animation = new TranslateAnimation(fromX, toX, 0, 0);
        animation.setStartOffset(100);
        animation.setDuration(800);
        animation.setAnimationListener(new DeleteAnimation(position));
        animation.setInterpolator(AnimationUtils.loadInterpolator(this,
                android.R.anim.anticipate_overshoot_interpolator));
        return animation;
    }

    /**
     * Called when an item in the {@link android.widget.GridView} is clicked. Here will launch the
     * {@link DetailActivity}, using the Scene Transition animation functionality.
     */

    public void SetupGridViewListener () {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                    long id) {

                if (swipeDetector.swipeDetected()){
                    if (swipeDetector.getAction() == SwipeDetector.Action.LEFT_TO_RIGHT ||
                            swipeDetector.getAction() == SwipeDetector.Action.RIGHT_TO_LEFT)
                    {
                        int toPos = position - 1;
                        int toX = swipeDetector.getAction() == SwipeDetector.Action.LEFT_TO_RIGHT ? 1 : 0;
                       // msg.obj = view;
                       // View view = (View)msg.obj;
                        view.startAnimation(getDeleteAnimation(0, (toX == 0) ? -view.getWidth() : 2 * view.getWidth(), toPos));
                        Item.items.remove(Item.items.get(position));
                        mAdapter.notifyDataSetChanged();
                    }
                }
                else {

                    Item item = (Item) adapterView.getItemAtPosition(position);
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra(DetailActivity.EXTRA_PARAM_ID, item.getId());
                   /* * Now create an {@link android.app.ActivityOptions} instance using the
                     * {@link ActivityOptionsCompat#makeSceneTransitionAnimation(Activity,
                     * Pair[])} factory* method.*/

            ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    MainActivity.this,
                    new Pair<View, String>(view.findViewById(R.id.imageview_item),
                            DetailActivity.VIEW_NAME_HEADER_IMAGE),
                    new Pair<View, String>(view.findViewById(R.id.textview_name),
                            DetailActivity.VIEW_NAME_HEADER_TITLE));

            // Now we can start the Activity, providing the activity options as a bundle
            ActivityCompat.startActivity(MainActivity.this, intent, activityOptions.toBundle());
                }

            }

        });

    }

}
