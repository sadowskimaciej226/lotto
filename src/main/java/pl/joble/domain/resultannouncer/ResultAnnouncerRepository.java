package pl.joble.domain.resultannouncer;

public interface ResultAnnouncerRepository {
    boolean existsById(String ticketId);

    AnnouncerResponse save(AnnouncerResponse announcerResponse);
}
