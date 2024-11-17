package pl.lotto.domain.resultannouncer;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResultAnnouncerRepository extends MongoRepository<AnnouncerResponse, String> {
   // boolean existsById(String ticketId);


}
