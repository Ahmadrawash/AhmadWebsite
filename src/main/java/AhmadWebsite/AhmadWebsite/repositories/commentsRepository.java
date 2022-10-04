package AhmadWebsite.AhmadWebsite.repositories;

import AhmadWebsite.AhmadWebsite.models.commentsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface commentsRepository extends JpaRepository<commentsModel, Integer> {
}
