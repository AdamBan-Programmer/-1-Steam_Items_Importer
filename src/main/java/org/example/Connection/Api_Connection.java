package org.example.Connection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.example.Settings.AppSettings;
import org.example.Utils.Item;
import org.example.Utils.JsonReader;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;

public class Api_Connection {
    static JsonReader jsonReader = new JsonReader();

    public Api_Connection()
    {
    }

    public int getSteamItemsAmount() throws JsonProcessingException {
        String url = "https://steamcommunity.com/market/search/render/?query=&start=20430&count=10&search_descriptions=0&sort_column=popular&sort_dir=desc&appid=730&category_730_ItemSet%5B%5D=any&category_730_ProPlayer%5B%5D=any&category_730_StickerCapsule%5B%5D=any&category_730_TournamentTeam%5B%5D=any&category_730_Weapon%5B%5D=any&norender=1";
        JsonNode rootNode = jsonReader.getJsonNode(url);
        return rootNode.get("total_count").asInt();
    }

    public ArrayList<Item> importSteamItems(int startValue, int ItemsAmount) throws JsonProcessingException {
        final String url = "https://steamcommunity.com/market/search/render/?query=&start=" + startValue + "&count=100&search_descriptions=0&sort_column=popular&sort_dir=desc&appid=730&norender=1";
        JsonNode rootNode = jsonReader.getJsonNode(url);
        JsonNode resultsNode = rootNode.get("results");
        ArrayList<Item> steamItems = new ArrayList<>();
        if (resultsNode.isArray()) {
            for (int i = 0; i < resultsNode.size(); i++) {
                if (steamItems.size() < ItemsAmount) {
                    JsonNode node = resultsNode.get(i).path("asset_description");
                    steamItems.add(new Item(null,node.path("market_name").asText(),node.path("icon_url").asText()));
                }
            }
        }
        return steamItems;
    }
}

