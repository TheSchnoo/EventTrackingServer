package web;

import controller.DatabaseController;
import controller.MeetupResponseConverter;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    private static String UBC_ZIP = "V6T+1Z4";

    private static String DEFAULT_TECH_TEXT = "technology%2C+tech";

    private MeetupResponseConverter converter = new MeetupResponseConverter();

    @RequestMapping(value = "/event", method = RequestMethod.GET)
    public
    @ResponseBody
    String searchEvents(@RequestParam(value = "zip", required = false) String zip,
                        @RequestParam(value = "text", required = false) String text) {
        DatabaseController databaseController = new DatabaseController();
        try {
            if (zip == null) {
                zip = UBC_ZIP;
            }
            if (text == null) {
                text = DEFAULT_TECH_TEXT;
            }
            return databaseController.searchEvents(zip, text).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "Error 500";
    }
}