package com.udea.bookclub.services.facade;

import com.udea.bookclub.dtos.EventRequest;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface ICalendarService {

    String createEvent(EventRequest eventRequest) throws IOException, GeneralSecurityException;
}
