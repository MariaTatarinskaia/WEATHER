package com.example.weather.controller;

import com.example.weather.model.Root;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

@RestController
public class HomeController {

    @GetMapping("/weather")
    public Object home() throws IOException, NoSuchFieldException, IllegalAccessException {
        StringBuilder json = new StringBuilder();
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?lat=51&lon=57&appid=d0edb2714b11c310fcc847f5ec23e409");

        try (InputStream input = url.openStream()) {
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(isr);
            int c;
            while ((c = reader.read()) != -1) {
                json.append((char) c);
            }
        }

        Gson gson = new Gson();
        Root root = gson.fromJson(json.toString(), Root.class);

        return root.main.temp - 273.15;
    }
}