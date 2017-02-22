package com.simiacryptus.util.lang;

import org.json.JSONException;
import org.json.JSONObject;

public interface JsonFormattable
{
  
  public abstract JSONObject toJson() throws JSONException;
  
}