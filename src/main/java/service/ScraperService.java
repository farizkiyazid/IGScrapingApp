package service;

import me.postaddict.instagram.scraper.Instagram;
import me.postaddict.instagram.scraper.model.Media;
import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author Farizki Yazid (yazidfarizki@gmail.com)
 * @version $Id: ScraperService.java, v 0.1 2020‐10‐06 11.20 Farizki Yazid Exp $$
 */
public class ScraperService {
    Instagram instagram;

    public ScraperService(Instagram instagram){
        this.instagram = instagram;
    }

    public String getMediasByUsername(String username, Date dateAfter) throws IOException {
        List<Media> medias = instagram.getMedias(username, 3).getNodes();
        int followers = instagram.getAccountByUsername(username).getFollowedBy();
        String output = "";
        int counter = 0;
        int likeCounter = 0;
        int commentCounter = 0;
        for (Media media : medias) {
            if (media.getCreated().compareTo(dateAfter) >= 0) {
                counter++;
                int likes = media.getLikeCount();
                likeCounter += likes;
                int comments = media.getCommentCount();
                commentCounter += comments;
                double engrate = ((double) likes + (double) comments) / (double) followers * (double) 100;
                output += ("\n=====================================================================================" +
                        "\n[*] Url : https://www.instagram.com/p/" + media.getShortcode() +
                        "\n[*] Date : " + media.getCreated() +
                        "\n[*] Image : " + media.getDisplayUrl() +
                        "\n[*] Video : " + media.getVideoUrl() +
                        "\n[*] Likes : " + likes +
                        "\n[*] Comments : " + comments +
                        "\n[*] Engagement rate : " + engrate + "%" +
                        "\n[*] Caption : " + media.getCaption());
            }
        }
        double engRateTot = ((double) likeCounter + (double) commentCounter) / (double) followers * (double) 100 / (double) counter;
        return "Username : " + username + " ; " + counter + " posts after the date " + dateAfter +
                "\nTotal Likes : " + likeCounter + " ; Total Comments : " + commentCounter + " Followers : " + followers +
                "\nEngagement rate total : " + engRateTot + "%" + output;
    }

    public JSONArray getArrJsonMediasByUsername(String username, Date dateAfter) throws IOException {
        List<Media> medias = instagram.getMedias(username, 3).getNodes();
        int followers = instagram.getAccountByUsername(username).getFollowedBy();
        JSONArray arrMedias = new JSONArray();
        for (Media media : medias) {
            if (media.getCreated().compareTo(dateAfter) >= 0) {
                int likes = media.getLikeCount();
                int comments = media.getCommentCount();
                double engrate = ((double) likes + (double) comments) / (double) followers * (double) 100;
                JSONObject mediaJson = new JSONObject();
                mediaJson.put("Url", "https://www.instagram.com/p/" + media.getShortcode());
                mediaJson.put("Date", media.getCreated().toString());
                mediaJson.put("Image", media.getDisplayUrl());
                mediaJson.put("Video", media.getVideoUrl());
                mediaJson.put("Likes", likes);
                mediaJson.put("Comments", comments);
                mediaJson.put("Engagement rate", engrate + "%");
                mediaJson.put("Caption", media.getCaption());
                arrMedias.put(mediaJson);
            }
        }
        return arrMedias;
    }

    public void writeToCSV(String filename, JSONArray jarray) throws IOException {
        File file = new File("hasilCSV/" + filename + ".csv");
        String csv = CDL.toString(jarray);
        FileUtils.writeStringToFile(file, csv);
    }

    public void writeToFile(String filename, String content) {
        try {
            FileWriter myWriter = new FileWriter("hasilScrape/" + filename + ".txt");
            myWriter.write(content);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
