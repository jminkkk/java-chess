package chess.repository.turn;

import chess.domain.board.Turn;
import chess.domain.piece.Color;

public record TurnDto(String turnColor) {
    public static TurnDto of(final Turn turn) {
        return new TurnDto(turn.getColor().name());
    }

    public Turn from() {
        return new Turn(Color.valueOf(turnColor));
    }
}
