package com.mycompany.httpbin;

import org.json.JSONObject;

import java.util.Map;

public abstract class HttpBinGeneral {

    protected final String convertArgsToString(final Map<String, String> args) {
        StringBuffer sb = new StringBuffer("?");

        Object[] values = args.values().toArray();
        Object[] keys = args.keySet().toArray();


        for (int i = 0; i < values.length; i++) {
            sb.append(keys[i]);
            sb.append("=");
            sb.append(values[i]);
            if (i != values.length - 1) {
                sb.append("&");
            }
        }
        return sb.toString();
    }

    protected final JSONObject getJSONObjectHeaders(final JSONObject jsonObject) {
        JSONObject responseJsonHeaders;
        if (((JSONObject) jsonObject.get("headers")).length() == 0) {
            responseJsonHeaders = null;
        } else {
            responseJsonHeaders = (JSONObject) jsonObject.get("headers");
        }
        return responseJsonHeaders;
    }

    protected final JSONObject getJSONObjectArgs(final JSONObject jsonObject) {
        JSONObject responseJsonArgs;
        if (((JSONObject) jsonObject.get("args")).length() == 0) {
            responseJsonArgs = null;
        } else {
            responseJsonArgs = (JSONObject) jsonObject.get("args");
        }
        return responseJsonArgs;
    }

    protected final JSONObject getJSONObjectJson(final JSONObject jsonObject) {
        JSONObject responseJsonJson;

        if ((jsonObject.get("json").toString().equals("null")) || (((JSONObject) jsonObject.get("json")).length() == 0)) {
            responseJsonJson = null;
        } else {
            responseJsonJson = (JSONObject) jsonObject.get("json");
        }
        return responseJsonJson;
    }

    protected final JSONObject getJSONObjectForm(final JSONObject jsonObject) {
        JSONObject responseJsonForm;
        if (((JSONObject) jsonObject.get("form")).length() == 0) {
            responseJsonForm = null;
        } else {
            responseJsonForm = (JSONObject) jsonObject.get("form");
        }
        return responseJsonForm;
    }

    protected final JSONObject getJSONObjectFiles(final JSONObject jsonObject) {
        JSONObject responseJsonFiles;
        if (((JSONObject) jsonObject.get("files")).length() == 0) {
            responseJsonFiles = null;
        } else {
            responseJsonFiles = (JSONObject) jsonObject.get("files");
        }
        return responseJsonFiles;
    }
}
