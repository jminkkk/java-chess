package chess.domain.movement.direction;


import chess.domain.position.Position;

public class UpRightDirection extends StraightDirection {

    public UpRightDirection(final int moveCount) {
        super(moveCount);
    }

    @Override
    Position next(final Position position) {
        if (position.isMaximumFile() || position.isMaximumRank()) {
            return position;
        }
        return position.right().up();
    }
}
