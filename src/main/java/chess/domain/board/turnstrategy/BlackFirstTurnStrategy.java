package chess.domain.board.turnstrategy;

import chess.domain.piece.Color;

public class BlackFirstTurnStrategy implements TurnStrategy {

    @Override
    public Color get() {
        return Color.BLACK;
    }
}
