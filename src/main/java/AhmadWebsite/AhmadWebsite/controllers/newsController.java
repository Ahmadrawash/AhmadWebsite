package AhmadWebsite.AhmadWebsite.controllers;

import AhmadWebsite.AhmadWebsite.repositories.newsRepository;
import AhmadWebsite.AhmadWebsite.models.newsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class newsController {

    @Autowired
    public newsController(newsRepository newsRepo) {
        this.newsRepo = newsRepo;
    }

    private final newsRepository newsRepo;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/ping")
    public String ping()
    {
        return "pinged successfully";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/view", method= RequestMethod.GET)
    public List<newsModel> viewNews()
    {
        System.out.println("view all news");
        return newsRepo.findAll();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/add", method=RequestMethod.POST)
    public String addNews(@RequestParam(value = "newsid", defaultValue = "defaultnewsid") String newsid,
                          @RequestParam(value = "text", defaultValue =  "defaultText") String text)
    {
        try
        {
            System.out.println("newsid: " + newsid + ", " + text);
            newsModel nm = new newsModel(Integer.parseInt(newsid), text);
            List<newsModel> li = newsRepo.findAll();
            for (newsModel n: li) {
                if (n.getNewsid() == Integer.parseInt(newsid))
                    return "FAILED: Instructor " + nm + " ALREADY EXIST";
            }
            newsRepo.save(nm);
            return "Success. news: " + nm + " has been ADDED";
        }
        catch(Exception exc)
        {
            System.out.println("Exception: " + exc.getMessage());
            exc.printStackTrace();
            return "FAILED. AN EXCEPTION HAS OCCURRED. " + exc.getMessage();
        }
    }
}
