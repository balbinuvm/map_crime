package com.example.map_crime;

import java.util.Date;

public interface Crime  {

  int getId();
  String getCrimeType();
  Date getDate();
  String getWeapon();
  double getLatitude();
  double getLongitude();
}
