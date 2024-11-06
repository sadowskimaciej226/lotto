package pl.lotto.domain.resultannouncer;

public interface ResultAnnouncerRepository {
    boolean existsById(String ticketId);

    AnnouncerResponse save(AnnouncerResponse announcerResponse);
}
