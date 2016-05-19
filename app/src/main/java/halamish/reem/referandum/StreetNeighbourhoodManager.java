package halamish.reem.referandum;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Re'em on 5/19/2016.
 */
public class StreetNeighbourhoodManager {
    private static final String ID_TO_N_FILE_NAME = "neighbourhood_ids.csv";
    private static final String STREET_TO_N_FILE_NAME = "street_to_neighbourhood.csv";
    private static StreetNeighbourhoodManager instance;

    private Map<String, String> streetToNeighbourhood;
    private Map<String, String> idToNeighbourhood;


    public static void init(AppClass app) {
        instance = new StreetNeighbourhoodManager(app);
    }

    private StreetNeighbourhoodManager(AppClass app) {
        streetToNeighbourhood = new HashMap<>();
        initStreets(app);

        idToNeighbourhood = new HashMap<>();
        initNeighbourhoodIds(app);

    }

    private void initNeighbourhoodIds(AppClass app) {
        InputStream is = null;
        try {
            is = app.getAssets().open(ID_TO_N_FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String str;
            str = reader.readLine(); // ignore first line, it's all headings
            while ((str = reader.readLine()) != null) {
                String[] parts = str.split(",");
                if (parts.length != 2)
                    continue;
                String n_id = parts[0];
                String neighbourhood = parts[1];
                idToNeighbourhood.put(n_id, neighbourhood);
            }
        } catch (IOException e){
            try {//noinspection ConstantConditions
                is.close();} catch (IOException | NullPointerException ignore) {}
        }
    }

    private void initStreets(AppClass app) {
        InputStream is = null;
        try {
            is = app.getAssets().open(STREET_TO_N_FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String str;
            str = reader.readLine(); // ignore first line, it's all headings
            while ((str = reader.readLine()) != null) {
                String[] parts = str.split(",");
                if (parts.length != 4)
                    continue;
                String neighbourhood = parts[0];
                String street = parts[2];
                streetToNeighbourhood.put(street, neighbourhood);
            }
        } catch (IOException e){
            try {//noinspection ConstantConditions
                is.close();} catch (IOException | NullPointerException ignore) {}
        }

    }
}
