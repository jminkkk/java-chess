package chess.domain.board;

import chess.domain.board.turnstrategy.TurnStrategy;
import chess.domain.piece.Color;

public class Turn {

    private final Color turnColor;

    public Turn() {
        this.turnColor = Color.BLACK;
    }

    public Turn(final TurnStrategy turnStrategy) {
        this.turnColor = turnStrategy.get();
    }

    public Turn next() {
        if (turnColor == Color.BLACK) {
            return new Turn(() -> Color.WHITE);
        }

        if (turnColor == Color.WHITE) {
            return new Turn(() -> Color.BLACK);
        }

        throw new IllegalStateException("게임 순서가 올바르지 못합니다.");
    }

    public boolean isTurn(final Color color) {
        return turnColor == color;
    }
}
