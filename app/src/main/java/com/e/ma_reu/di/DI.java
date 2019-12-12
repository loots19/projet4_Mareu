package com.e.ma_reu.di;

import com.e.ma_reu.service.DummyMeetingApiService;
import com.e.ma_reu.service.MeetingApiService;

public class DI {
    private static MeetingApiService service = new DummyMeetingApiService();

    /**
     * Get an instance on @{@link MeetingApiService}
     * @return
     */
    public static MeetingApiService getMeetingApiService() {
        return service;
    }

    /**
     * Get always a new instance on @{@link MeetingApiService}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static MeetingApiService getNewInstanceApiService() {
        return new DummyMeetingApiService();
    }
}
