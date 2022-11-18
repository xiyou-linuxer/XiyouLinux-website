package com.xiyoulinux.idao;

import com.xiyoulinux.model.Events;
import java.util.ArrayList;

public interface Ievents {
  boolean insert(Events var1);

  boolean delete(int var1);

  boolean update(Events var1);

  Events getEventsByID(int var1);

  ArrayList<Events> getEventsByTitle(String var1);

  ArrayList<Events> getEventsByPage(int var1, String var2);

  ArrayList<Events> getEventsByPage(int var1, String var2, int var3);

  ArrayList getEventsByNumber(int var1);

  boolean alterEventsStatus(int var1);

  boolean addEventsRead(int var1);
}
