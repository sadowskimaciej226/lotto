package pl.joble.domain.resultannouncer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class ResultAnnouncerRepositoryImpl implements ResultAnnouncerRepository {
    Map<String, AnnouncerResponse> db = new ConcurrentHashMap<>();
    @Override
    public AnnouncerResponse save(AnnouncerResponse announcerResponse) {
        db.put(announcerResponse.ticketId(), announcerResponse);
        return announcerResponse;
    }


    @Override
    public boolean existsById(String ticketId) {
        if(db.get(ticketId)==null) return false;
        else return true;
    }
}
