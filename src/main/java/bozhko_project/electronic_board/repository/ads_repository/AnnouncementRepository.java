package bozhko_project.electronic_board.repository.ads_repository;

import bozhko_project.electronic_board.entities.ads.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    Announcement findAnnouncementByUserId(Long id);

    List<Announcement> findAnnouncementByProductId(Long id);

    List<Announcement> findAnnouncementByTitle(String title);
}