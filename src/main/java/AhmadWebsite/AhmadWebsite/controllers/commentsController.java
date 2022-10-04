package AhmadWebsite.AhmadWebsite.controllers;

import AhmadWebsite.AhmadWebsite.repositories.commentsRepository;
import AhmadWebsite.AhmadWebsite.models.commentsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import AhmadWebsite.AhmadWebsite.repositories.newsRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class commentsController {

    private final commentsRepository commentsRepo;
    private final newsRepository newsRepo;

    @Autowired
    public commentsController(commentsRepository commentsRepo, newsRepository newsRepo) {
        this.commentsRepo = commentsRepo;
        this.newsRepo = newsRepo;
    }

    @ResponseBody
    @RequestMapping(value = "/ping")
    public String ping()
    {
        return "pinged successfully";
    }

    @RequestMapping(value = "/view", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<commentsModel> getAllComments()
    {
        System.out.println("view all comments");
        List<commentsModel> viewli = new ArrayList<commentsModel>();
        List<commentsModel> li = commentsRepo.findAll();
        for (commentsModel c: li) {
            if (c.getDisplay() != false)
                viewli.add(c);
        }
        return viewli;
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String addComment(@RequestParam(value = "id",     defaultValue   = "defaultId") String id,
                             @RequestParam(value = "name",   defaultValue   = "defaultName") String name,
                             @RequestParam(value = "email",  defaultValue  = "defaultEmail") String email,
                             @RequestParam(value = "text",   defaultValue =  "defaultText") String text,
                             @RequestParam(value = "newsid", defaultValue =  "defaultNewsid") String newsid,
                             @RequestParam(value = "display",   defaultValue =  "defaultDisplay") String display)
    {
        try
        {
            System.out.println("attempt at adding comment: [id: " + id + "], [name: " + name + "], [email: " + email + "]," +
                    "[text: " + text + "], [display: " + display + "], [newsid: " + newsid + "]");
            commentsModel cm = new commentsModel(Integer.parseInt(id), name, email, text, Integer.parseInt(newsid),
                    (display.equals("0")==true ? false : true), newsRepo);
            List<commentsModel> li = commentsRepo.findAll();
            for (commentsModel c: li) {
                if (c.getId() == cm.getId())
                    return "FAILED. comment: " + c + " ALREADY EXISTS";
            }
            commentsRepo.save(cm);
            return "SUCCESS. comment " + cm + " has already been added";
        }
        catch(Exception exc)
        {
            System.out.println("Exception: " + exc.getMessage());
            exc.printStackTrace();
            return "FAILED. AN EXCEPTION HAS OCCURRED. " + exc.getMessage();
        }

    }


}
