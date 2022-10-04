package AhmadWebsite.AhmadWebsite.repositories;

import AhmadWebsite.AhmadWebsite.models.newsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface newsRepository extends JpaRepository<newsModel, Integer> {
}
