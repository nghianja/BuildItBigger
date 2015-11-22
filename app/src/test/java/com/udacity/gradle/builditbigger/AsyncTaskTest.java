package com.udacity.gradle.builditbigger;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * To test whether the EndpointsAsyncTask successfully retrieves a non-empty string.
 *
 * References:
 * [1] https://discussions.udacity.com/t/writing-tests-for-async-task/25482/16
 * [2] http://stackoverflow.com/questions/28960898/getting-context-in-androidtestcase-or-instrumentationtestcase-in-android-studio
 * [3] http://tools.android.com/tech-docs/unit-testing-support
 */
@RunWith(MockitoJUnitRunner.class)
public class AsyncTaskTest {
    @Mock
    private Context context;

    @Test
    public void testVerifyGetJoke() {
        try {
            EndpointsAsyncTask task = new EndpointsAsyncTask();
            task.execute(context);
            String joke = task.get(30, TimeUnit.SECONDS);
            assertFalse(joke.isEmpty());
        } catch (Exception e) {
            fail("Timed out");
        }
    }
}
