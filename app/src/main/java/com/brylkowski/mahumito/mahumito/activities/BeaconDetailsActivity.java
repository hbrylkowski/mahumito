package com.brylkowski.mahumito.mahumito.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.brylkowski.mahumito.mahumito.R;
import com.brylkowski.mahumito.mahumito.commands.ClaimBeacon;
import com.brylkowski.mahumito.mahumito.commands.GiveBeacon;
import com.brylkowski.mahumito.mahumito.commands.utilities.AsyncCommandWrapper;
import com.brylkowski.mahumito.mahumito.commands.utilities.CommandCallbackInterface;
import com.brylkowski.mahumito.mahumito.models.Beacon;
import com.squareup.picasso.Picasso;

public class BeaconDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView imageView = (ImageView) findViewById(R.id.image_toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Congratulations! You've completed the quest.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            }
        });

        Button asdbutton = (Button) findViewById(R.id.button_target);
        asdbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Congratulations! You've completed the quest.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            }
        });


        Intent intent = getIntent();
        final Beacon beacon = (Beacon) intent.getSerializableExtra("BEACON");
        Picasso.with(this).load(beacon.missionImage()).into(imageView);
        setTitle(beacon.name());

        Button button = (Button) findViewById(R.id.button_give);
        if(beacon.pendingClaimUser() == null){
            button.setVisibility(View.GONE);
        } else {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AsyncCommandWrapper wrapper = new AsyncCommandWrapper(new CommandCallbackInterface() {
                        @Override
                        public void success(Object objectResponse) {
                            finish();
                        }

                        @Override
                        public void failure(Throwable reason) {

                        }
                    });
                    wrapper.doInBackground(new GiveBeacon(beacon.beaconUniqueId(), beacon.pendingClaimUser()));

                }
            });
        }

        TextView nextGoal = (TextView) findViewById(R.id.nextGoal);
        nextGoal.setText(beacon.currentTask());
        if(beacon.currentTask().length() == 0){
            nextGoal.setVisibility(View.GONE);
        }
        TextView furtherGoal = (TextView) findViewById(R.id.furtherGoal);
        furtherGoal.setText(beacon.nextTask());
        if(beacon.nextTask().length() == 0){
            furtherGoal.setVisibility(View.GONE);
        }
        TextView finalGoal = (TextView) findViewById(R.id.finalGoal);
        finalGoal.setText(beacon.finalTask());
        if(beacon.finalTask().length() == 0){
            finalGoal.setVisibility(View.GONE);
        }
        ((TextView) findViewById(R.id.description)).setText(beacon.description());
    }
}
