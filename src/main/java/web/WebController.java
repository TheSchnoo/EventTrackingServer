package web;

import controller.DatabaseController;
import controller.MeetupResponseConverter;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    private MeetupResponseConverter converter = new MeetupResponseConverter();

    @RequestMapping(value = "/event", method = RequestMethod.GET)
    public
    @ResponseBody
    String getEvent() {
        DatabaseController databaseController = new DatabaseController();
        try {
            return databaseController.selectEvents().toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "Error 500";
    }
}