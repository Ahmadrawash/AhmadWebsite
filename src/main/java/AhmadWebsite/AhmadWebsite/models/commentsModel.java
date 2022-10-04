package AhmadWebsite.AhmadWebsite.models;

import AhmadWebsite.AhmadWebsite.repositories.newsRepository;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="comments")
public class commentsModel {

    @Id
    @Column(name="id", nullable = false)
    int id;

    @Column(name="name", nullable = false)
    String name;

    @Column(name="email", nullable = false)
    String email;

    @Column(name="text", nullable = false)
    String text;

    @JoinColumn(name = "newsid")
    @ManyToOne
    newsModel news;

    @Column(name = "display", nullable = false)
    boolean display;


    public boolean getDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }


    public commentsModel(int id, String name, String email, String text, int newsid, boolean display, newsRepository newsRepo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.text = text;
        this.display = display;

        List<newsModel> li = newsRepo.findAll();
        for (newsModel dm: li) {
            if (dm.getNewsid() == newsid)
                this.news = new newsModel(dm.getNewsid(), dm.getText());
        }
        if (news == null)
            this.news = new newsModel(1, "no news is good news");
        System.out.println(this.toString());
    }

    public commentsModel()
    {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public newsModel getNews() {
        return news;
    }

    public void setNews(newsModel news) {
        this.news = news;
    }

    @Override
    public String toString() {
        return "commentsModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", text='" + text + '\'' +
                ", news=" + news +
                ", display=" + display +
                '}';
    }
}
