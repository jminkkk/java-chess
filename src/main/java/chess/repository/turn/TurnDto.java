package chess.repository.turn;

import chess.domain.piece.Color;

public record TurnDto(String turnColor) {
    public static TurnDto of(final Color color) {
        return new TurnDto(color.name());
    }

    public Color from() {
        return Color.valueOf(turnColor);
    }
}
