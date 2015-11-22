package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import java.util.concurrent.TimeUnit;

/**
 * To test whether the EndpointsAsyncTask successfully retrieves a non-empty string.
 *
 * References:
 * [1] https://discussions.udacity.com/t/writing-tests-for-async-task/25482/16
 * [2] http://stackoverflow.com/questions/28960898/getting-context-in-androidtestcase-or-instrumentationtestcase-in-android-studio
 * [3] http://marksunghunpark.blogspot.ru/2015/05/how-to-test-asynctask-in-android.html
 */
public class AsyncTaskTest extends AndroidTestCase {
    public void testVerifyGetJoke() {
        try {
            EndpointsAsyncTask task = new EndpointsAsyncTask();
            task.execute(getContext());
            String joke = task.get(30, TimeUnit.SECONDS);
            assertFalse(joke.isEmpty());
        } catch (Exception e) {
            fail("Timed out");
        }
    }
}
