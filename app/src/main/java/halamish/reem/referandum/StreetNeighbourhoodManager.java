package halamish.reem.referandum;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Re'em on 5/19/2016.
 */
public class StreetNeighbourhoodManager {
    private static final String ID_TO_N_FILE_NAME = "neighbourhood_ids.csv";
    private static final String STREET_TO_N_FILE_NAME = "street_to_neighbourhood.csv";
    private static StreetNeighbourhoodManager instance;

    private Map<String, String> streetToNeighbourhood;
    private List<String> allStreets;
    private Map<String, String> idToNeighbourhood;


    public static void init(AppClass app) {
        instance = new StreetNeighbourhoodManager(app);
    }

    private StreetNeighbourhoodManager(AppClass app) {
        streetToNeighbourhood = new HashMap<>();
        allStreets = new ArrayList<>();
        initStreets(app);

        idToNeighbourhood = new HashMap<>();
        initNeighbourhoodIds(app);

    }

    public static synchronized StreetNeighbourhoodManager getInstance() {
        return instance;
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
                String n_id = parts[0].replace("''", "\"");
                String neighbourhood = parts[1].replace("''", "\"");
                idToNeighbourhood.put(n_id, neighbourhood);
            }
        } catch (IOException e){
            try {//noinspection ConstantConditions
                is.close();} catch (IOException | NullPointerException ignore) {}
        }
    }

    public List<String> getAllStreets() {
        return allStreets;
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
                String neighbourhood = parts[0].replace("''", "\"");
                String street = parts[2].replace("''", "\"");
                if (streetToNeighbourhood.containsKey(street))
                    continue;
                streetToNeighbourhood.put(street, neighbourhood);
                allStreets.add(street);
            }
        } catch (IOException e) {
            try {//noinspection ConstantConditions
                is.close();
            } catch (IOException | NullPointerException ignore) {
            }
        }
    }

    public boolean streetValid(String street) {
        return streetToNeighbourhood.containsKey(street);
    }

}
