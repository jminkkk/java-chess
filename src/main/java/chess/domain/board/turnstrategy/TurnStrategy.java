package chess.domain.board.turnstrategy;

import chess.domain.piece.Color;
import java.util.function.Supplier;

public interface TurnStrategy extends Supplier<Color> {
}
