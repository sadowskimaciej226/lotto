package pl.joble.domain.resultannouncer;

class PlayerAlreadyCheckedException extends RuntimeException{
    public PlayerAlreadyCheckedException(String ticketWasCheckedBefore) {
    }
}
