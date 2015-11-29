package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;

/**
 * References:
 * [1] http://stackoverflow.com/questions/10010013/android-how-to-start-activity-defined-in-library-project
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private PublisherInterstitialAd mPublisherInterstitialAd;

    public MainActivityFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragment = (MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);

        mPublisherInterstitialAd = new PublisherInterstitialAd(this);
        mPublisherInterstitialAd.setAdUnitId("AD_UNIT_ID");

        // Set an AdListener.
        mPublisherInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                getJoke();
            }
        });

        requestNewInterstitial();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){
        if (mPublisherInterstitialAd.isLoaded()) {
            mPublisherInterstitialAd.show();
        } else {
            getJoke();
        }
    }

    private void requestNewInterstitial() {
        // Create an ad request.
        PublisherAdRequest.Builder publisherAdRequestBuilder = new PublisherAdRequest.Builder();

        // Optionally populate the ad request builder.
        publisherAdRequestBuilder.addTestDevice(PublisherAdRequest.DEVICE_ID_EMULATOR);

        mPublisherInterstitialAd.loadAd(publisherAdRequestBuilder.build());
        Log.d(TAG, "loading interstitial ad...");
    }

    private void getJoke() {
        mFragment.setVisibility(true);
        new EndpointsAsyncTask().execute(this);
    }

}
