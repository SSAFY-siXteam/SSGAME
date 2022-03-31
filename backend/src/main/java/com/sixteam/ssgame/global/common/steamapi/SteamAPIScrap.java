package com.sixteam.ssgame.global.common.steamapi;

import com.sixteam.ssgame.global.error.dto.ErrorStatus;
import com.sixteam.ssgame.global.error.exception.CustomException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = getHttpURLConnection(url);

        int responseCode = conn.getResponseCode();
        boolean isSuccess = 200 <= responseCode && responseCode <= 300;
        String response = getResponse(conn, isSuccess);

        if (isSuccess) {
            JSONParser parser = new JSONParser();
            JSONObject totalInfoJson = (JSONObject) ((JSONObject) parser.parse(response)).get("response");
            JSONArray playerInfoJsons = (JSONArray) totalInfoJson.get("players");

            if (playerInfoJsons.size() == 0) {
                throw new CustomException("[Error] invalid steam ID : " + steamID, ErrorStatus.INVALID_STEAMID);
            }
            JSONObject playerInfoJson = (JSONObject) playerInfoJsons.get(0);

            responseData.put("steamNickname", playerInfoJson.get("personaname"));
            responseData.put("avatarUrl", playerInfoJson.get("avatarfull"));
            responseData.put("isPublic", (Long) playerInfoJson.get("communityvisibilitystate") == 3L);
        } else {
            throw new CustomException("[Error] api connection url : " + urlBuilder, ErrorStatus.API_NOT_CONNECTION);
        }

        return responseData;
    }

    public static Map<String, Object> getGameData(String steamID) throws IOException, ParseException {

        Map<String, Object> responseData = new HashMap<>();
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder
                .append(OWNED_GAMES_BASE_URL)
                .append("?").append(encode("key", UTF_8)).append(EQUAL).append(KEY)
                .append("&").append(encode("steamid", UTF_8)).append(EQUAL).append(steamID)
                .append("&").append(encode("format", UTF_8)).append(EQUAL).append(FORMAT);

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = getHttpURLConnection(url);

        int responseCode = conn.getResponseCode();
        boolean isSuccess = 200 <= responseCode && responseCode <= 300;
        String response = getResponse(conn, isSuccess);

        if (isSuccess) {
            JSONParser parser = new JSONParser();
            JSONObject totalInfoJson = (JSONObject) ((JSONObject) parser.parse(response)).get("response");
            if (totalInfoJson.size() == 0) {
                // 구매한 게임이 없는 사용자 존재 -> exception 말고 다른 처리 필요
                throw new CustomException("[Error] invalid steam ID : " + steamID, ErrorStatus.INVALID_STEAMID);
            }

            JSONArray gameInfoJsons = (JSONArray) totalInfoJson.get("games");

            Map<Long, Long> memberGameList = new HashMap<>();
            for (int i = 0; i < gameInfoJsons.size(); i++) {
                JSONObject gameInfoJson = (JSONObject) gameInfoJsons.get(i);
                memberGameList.put((Long) gameInfoJson.get("appid"), (Long) gameInfoJson.get("playtime_forever"));
            }

            responseData.put("gameCount", totalInfoJson.get("game_count"));
            responseData.put("memberGameList", memberGameList);
        } else {
            throw new CustomException("[Error] api connection url : " + urlBuilder, ErrorStatus.API_NOT_CONNECTION);
        }

        return responseData;
    }

    private static String getResponse(HttpURLConnection conn, boolean isSuccess) throws IOException {

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

    private static HttpURLConnection getHttpURLConnection(URL url) throws IOException {
        // 커넥션 객체 생성
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // HTTP 메서드 설정
        conn.setRequestMethod("GET");

        // Content Type 설정
        conn.setRequestProperty("Content-type", "application/json");

        return conn;
    }
}
