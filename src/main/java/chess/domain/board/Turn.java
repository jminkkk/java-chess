package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.Objects;

public final class Turn {
    private final Color turnColor;

    public Turn() {
        this.turnColor = Color.BLACK;
    }

    public Turn(final Color color) {
        this.turnColor = color;
    }

    public Turn next() {
        if (turnColor == Color.BLACK) {
            return new Turn(Color.WHITE);
        }

        if (turnColor == Color.WHITE) {
            return new Turn(Color.BLACK);
        }

        throw new IllegalStateException("게임 순서가 올바르지 못합니다.");
    }

    public boolean isTurn(final Piece piece) {
        return piece.isSameTeamColor(turnColor);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Turn turn = (Turn) o;
        return turnColor == turn.turnColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(turnColor);
    }

    public Color getColor() {
        return turnColor;
    }
}
