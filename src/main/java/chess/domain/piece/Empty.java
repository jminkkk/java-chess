package chess.domain.piece;

import chess.domain.obstaclerule.NonAttacker;
import chess.domain.position.Position;
import java.util.Map;

public class Empty extends NonAttacker {
    public Empty() {
        super(PieceType.EMPTY, Color.NONE);
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Map<Position, Piece> pieces) {
        return false;
    }
}
