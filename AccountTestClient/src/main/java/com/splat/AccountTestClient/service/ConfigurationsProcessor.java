package com.splat.AccountTestClient.service;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class ConfigurationsProcessor {

    @Getter
    private int rCount;
    @Getter
    private int wCount;
    @Getter
    private int idListL;
    @Getter
    private int idListR;
    @Getter
    private String basicUrlPart;

    ConfigurationsProcessor() throws IOException {
        Properties properties = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("configuration.properties");
            properties.load(input);
            rCount = Integer.parseInt(properties.getProperty("rCount"));
            wCount = Integer.parseInt(properties.getProperty("wCount"));
            idListL= Integer.parseInt(properties.getProperty("idListL"));
            idListR = Integer.parseInt(properties.getProperty("idListR"));
            basicUrlPart = properties.getProperty("serviceURL");

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            if (input != null){
                try {
                    input.close();
                } catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        }

    }
}
