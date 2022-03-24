package com.sixteam.ssgame.global.common.steamapi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static java.net.URLEncoder.encode;
import static java.nio.charset.StandardCharsets.UTF_8;


public class SteamAPIScrap {

    static final String PLAYER_SUMMARIES_BASE_URL = "https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/";
    static final String OWNED_GAMES_BASE_URL = "http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/";
    static final String KEY = "03EE18CF1F7A3B8D4883510C8E5E27B5";
    static final String EQUAL = "=";
    static final String FORMAT = "json";

    public static Map<String, Object> getMemberData(String steamID) throws IOException, ParseException {

        Map<String, Object> responseData = new HashMap<>();
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder
                .append(PLAYER_SUMMARIES_BASE_URL)
                .append("?").append(encode("key", UTF_8)).append(EQUAL).append(KEY)
                .append("&").append(encode("steamids", UTF_8)).append(EQUAL).append(steamID);
//                .append("&").append(encode("format", UTF_8)).append(EQUAL).append(FORMAT);

        URL url = new URL(urlBuilder.toString());
        HttpsURLConnection conn = getHttpURLConnection(url);

        int responseCode = conn.getResponseCode();
        boolean isSuccess = 200 <= responseCode && responseCode <= 300;
        String response = getResponse(conn, isSuccess);

        if (isSuccess) {
            JSONParser parser = new JSONParser();
            JSONObject totalInfoJson = (JSONObject) ((JSONObject) parser.parse(response)).get("response");
            JSONArray playerInfoJsons = (JSONArray) totalInfoJson.get("players");

            if (playerInfoJsons.size() == 0) {
                throw new InvalidSteamIDException("invalid steam ID : " + steamID);
            }
            JSONObject playerInfoJson = (JSONObject) playerInfoJsons.get(0);

            responseData.put("steamNickname", playerInfoJson.get("personaname"));
            responseData.put("avatarUrl", playerInfoJson.get("avatarfull"));
            responseData.put("isPublic", (int) playerInfoJson.get("communityvisibilitystate") == 3);
        } else {
            throw new APIConnectionException("[Error] api connection url : " + urlBuilder.toString());
        }

        return responseData;
    }

    private static String getResponse(HttpsURLConnection conn, boolean isSuccess) throws IOException {

        BufferedReader br;
        if (isSuccess) {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        conn.disconnect();
        br.close();

        return sb.toString();
    }

    private static HttpsURLConnection getHttpURLConnection(URL url) throws IOException {
        // 커넥션 객체 생성
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

        // HTTP 메서드 설정
        conn.setRequestMethod("GET");

        // Content Type 설정
        conn.setRequestProperty("Content-type", "application/json");

        return conn;
    }
}
