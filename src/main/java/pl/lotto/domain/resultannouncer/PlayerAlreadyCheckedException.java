package pl.lotto.domain.resultannouncer;

class PlayerAlreadyCheckedException extends RuntimeException{
    public PlayerAlreadyCheckedException(String ticketWasCheckedBefore) {
    }
}
