package chess.domain.direction;

import chess.domain.Position;

public class DownDirection extends StraightDirection {

    DownDirection(final int moveCount) {
        super(moveCount);
    }

    @Override
    Position next(final Position from) {
        if (from.isMinimumRow()) {
            return from;
        }
        return new Position(from.row() - 1, from.column());
    }
}
