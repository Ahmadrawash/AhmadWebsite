package AhmadWebsite.AhmadWebsite.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="news")
public class newsModel {

    @Id
    @Column(name="newsid", nullable = false)
    int newsid;

    public void setNewsid(int newsid) {
        this.newsid = newsid;
    }

    @Override
    public String toString() {
        return "newsModel{" +
                "newsid=" + newsid +
                ", text='" + text + '\'' +
                '}';
    }

    public int getNewsid() {
        return newsid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name="text", nullable = false)
    String text;

    public newsModel(int newsid, String text) {
        this.newsid = newsid;
        this.text = text;
    }
    public newsModel(String text){
        this.text = text;
    }
    public newsModel()
    {
        this.newsid = 1;
        this.text = "initial text";
    }
}
