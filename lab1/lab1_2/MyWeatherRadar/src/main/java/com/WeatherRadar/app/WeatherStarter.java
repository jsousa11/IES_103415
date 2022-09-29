package com.WeatherRadar.app;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.WeatherRadar.app.IpmaCityForecast; //may need to adapt package name
import com.WeatherRadar.app.IpmaService;

/**
 * demonstrates the use of the IPMA API for weather forecast
 */
public class WeatherStarter {

    // private static final int CITY_ID_AVEIRO = 1010500;
    /*
    loggers provide a better alternative to System.out.println
    https://rules.sonarsource.com/java/tag/bad-practice/RSPEC-106
     */
    private static final Logger logger = Logger.getLogger(WeatherStarter.class.getName());

    public static void  main(String[] args ) {

        /*
        get a retrofit instance, loaded with the GSon lib to convert JSON into objects
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Map<String, Integer> cities = new HashMap<>();
        IpmaService service = retrofit.create(IpmaService.class);
        Call<IpmaCityForecast> callSync = service.getCityID();

        String CITY_ID = args[0].toLowerCase();

        //IpmaService service = retrofit.create(IpmaService.class);
        //Call<IpmaCityForecast> callSync = service.getForecastForACity(Integer.parseInt(CITY_ID));

        try {
            Response<IpmaCityForecast> apiResponse = callSync.execute();
            IpmaCityForecast forecast = apiResponse.body();

            for (CityForecast c : forecast.getData()) {
                String local = c.getLocal().toLowerCase();
                int id = c.getGlobalIdLocal();
                cities.put(local, id);
            }

            if (cities.containsKey(CITY_ID)) {
                retrofit = new Retrofit.Builder()
                        .baseUrl("http://api.ipma.pt/open-data/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                service = retrofit.create(IpmaService.class);
                CITY_ID = args[0].toLowerCase();
                callSync = service.getForecastForACity(cities.get(CITY_ID));


                try {
                    apiResponse = callSync.execute();
                    forecast = apiResponse.body();
                    if (forecast != null) {
                        logger.info( "max temp for today: " + forecast.getData().
                                listIterator().next().getTMax());
                    } else {
                        logger.info( "No results!");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            else {
                logger.info( "No results!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
