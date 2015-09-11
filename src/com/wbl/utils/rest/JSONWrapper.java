package com.wbl.utils.rest;

import com.wbl.utils.web.PageDriver;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by Shilpi on 8/31/2015.
 */
public class JSONWrapper {

    private Logger _logger;
    private JSONArray jsonArray;
    private JSONObject jsonObj;
    //private JSONObject childJson;
    //private List<JSONObject> childJsonList = new ArrayList<JSONObject>();

    public JSONWrapper()
    {
        _logger = Logger.getLogger(PageDriver.class);
    }

    public JSONWrapper(JSONArray array)
    {
        this.jsonArray = array;
    }
    public JSONWrapper(JSONObject obj)
    {
         this.jsonObj = obj;
    }


    public boolean isKeyAvailable(String mKey)
    {
        boolean available = false;
        Iterator<String> jsonKeys = jsonObj.keys();
        while (jsonKeys.hasNext()) {
            String key = jsonKeys.next();
            if (key.equals(mKey)) {
                available = true;
                break;
            }

        }
        return available;

    }

    public boolean isValueNotNull(String mKey) {
        try {
            boolean isNull = jsonObj.get(mKey).toString() != null ? true : false;
            return isNull;
        } catch (JSONException e) {
            _logger.error(e);
        }
        return true;
    }

    public String getJsonValue(String mKey)
    {
        String value = null;
        try {
            value = jsonObj.get(mKey).toString();
        } catch (JSONException e) {
            _logger.error(e);
        }
        return value;
    }

    public int getJsonIntValue(String mKey) {
        int intValue = 0;
        String value = null;
        try {
            value = jsonObj.get(mKey).toString();
            if (!StringUtils.isNumeric(value)) {
                _logger.info("Passed key doesn't contain a valid integer value");
                return 0;
            } else {
                intValue = Integer.parseInt(value);
            }
        } catch (JSONException e) {
            _logger.error(e);
        }
        return intValue;
    }

    public boolean getJsonBooleanVal(String mKey) {
        boolean boolValue = true;
        String value = null;
        try {
            value = jsonObj.get(mKey).toString();
            boolValue = Boolean.valueOf(value);
        } catch (JSONException e) {
            _logger.error(e);
        }
        return boolValue;
    }


    public JSONObject getJsonObject(String mKey)
    {
        Object json;
        JSONArray array = null;
        try {
            json = jsonObj.get(mKey);
            if(json != null && (json instanceof JSONObject))
            {
                return (JSONObject)json;
            }
        } catch (JSONException e) {
            _logger.error(e);
        }

        return null;
    }

    public JSONArray getJsonArray(String mKey)
    {
        Object json;
        try {
            json = jsonObj.get(mKey);
            if(json != null && (json instanceof JSONArray))
            {
               return (JSONArray)json;
            }
        } catch (JSONException e) {
            _logger.error(e);
        }

        return null;
    }

    public void getJsonArrayObject(int index)
    {
        try {
            this.jsonObj = (JSONObject)jsonArray.get(index);
        } catch (JSONException e) {
            _logger.error(e);
        }
    }

    public boolean isPropertyArray(String mKey)
    {
        boolean isArray = false;
        Object obj = null;
        try {
            obj = jsonObj.get(mKey);
            if(obj != null && obj instanceof JSONArray)
            {
                isArray = true;
                this.jsonArray = (JSONArray)obj;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return isArray;
    }

    public String getJsonArrayElement(String mKey,int index)
    {
        String value = null;
        Object obj = null;
        JSONArray array;
        try {
            obj = jsonObj.get(mKey);
            if(obj == null || !(obj instanceof JSONArray))
            {
                _logger.info("Passed value is not a valid json array");
                return null;
            }
            else
            {
                array = (JSONArray)obj;
                value = getElement(array,index);
               // value = (String)array.get(index);
            }
        } catch (JSONException e) {
            _logger.error(e);
        }
        return value;
    }

    public void getArrayChildObject(int index)
    {
        Object obj = null;
        try {
            if (jsonArray != null && jsonArray.length() > 0) {
                obj = jsonArray.get(index);
                if (obj instanceof JSONObject)
                {
                    this.jsonObj = (JSONObject)obj;
                }
            }
        }
        catch (JSONException e)
        {
            _logger.error(e);
        }
    }
    public String getElement(JSONArray array , int index)
    {
        try {
            return (String)array.get(index);
        } catch (JSONException e) {
            _logger.error(e);
        }

        return "";
    }

    /*

    public void setChildJson()
    {
        Iterator<String> keys = jsonObj.keys();
        try {
        while (keys.hasNext())
        {

                Object obj = jsonObj.get(keys.next());
               if(obj != null && obj instanceof JSONObject)
               {
                   this.childJsonList.add((JSONObject) obj);
               }
        }
        } catch (JSONException e) {
            _logger.error(e);
        }
    }

    public String getChildValue(String mKey)
    {
        try {
            return childJson.get(mKey).toString();
        } catch (JSONException e) {
            _logger.error(e);
        }
        return "";
    }*/

    public int getPropertyCount()
    {
       return jsonObj.length();
    }
    public int getArrayCount() {
        return jsonArray.length();
    }
}
