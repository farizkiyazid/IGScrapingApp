import me.postaddict.instagram.scraper.Instagram;
import me.postaddict.instagram.scraper.model.Media;
import okhttp3.OkHttpClient;
import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;
import service.ScraperService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainScraper {


    public static void main(String[] args) throws IOException, ParseException {
        OkHttpClient httpClient = new OkHttpClient();
        ScraperService scraperService = new ScraperService(new Instagram(httpClient));
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateBase = sdformat.parse("2019-04-01");
//        scraperService.writeToFile("playtopia.id", scraperService.getMediasByUsername("playtopia.id", dateBase));
//        scraperService.writeToFile("playnlearn_id", scraperService.getMediasByUsername("playnlearn_id", dateBase));
//        scraperService.writeToFile("kidzaniajakarta", scraperService.getMediasByUsername("kidzaniajakarta", dateBase));
//        scraperService.writeToFile("miniapolisplayground", scraperService.getMediasByUsername("miniapolisplayground", dateBase));
//        scraperService.writeToFile("yourekakidsfarm", scraperService.getMediasByUsername("yourekakidsfarm", dateBase));
//        scraperService.writeToFile("wonderkidsindonesia", scraperService.getMediasByUsername("wonderkidsindonesia", dateBase));
//        scraperService.writeToFile("kidzilla_id", scraperService.getMediasByUsername("kidzilla_id", dateBase));
//        scraperService.writeToFile("buumiplayscape", scraperService.getMediasByUsername("buumiplayscape", dateBase));

        System.out.println(scraperService.getMediasByUsername("sakinadenia", dateBase));

//        scraperService.writeToCSV("playtopia.id", scraperService.getArrJsonMediasByUsername("playtopia.id", dateBase));
//        scraperService.writeToCSV("playnlearn_id", scraperService.getArrJsonMediasByUsername("playnlearn_id", dateBase));
//        scraperService.writeToCSV("kidzaniajakarta", scraperService.getArrJsonMediasByUsername("kidzaniajakarta", dateBase));
//        scraperService.writeToCSV("miniapolisplayground", scraperService.getArrJsonMediasByUsername("miniapolisplayground", dateBase));
//        scraperService.writeToCSV("yourekakidsfarm", scraperService.getArrJsonMediasByUsername("yourekakidsfarm", dateBase));
//        scraperService.writeToCSV("wonderkidsindonesia", scraperService.getArrJsonMediasByUsername("wonderkidsindonesia", dateBase));
//        scraperService.writeToCSV("kidzilla_id", scraperService.getArrJsonMediasByUsername("kidzilla_id", dateBase));
//        scraperService.writeToCSV("buumiplayscape", scraperService.getArrJsonMediasByUsername("buumiplayscape", dateBase));
    }

}
