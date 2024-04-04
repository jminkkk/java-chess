package chess.repository.piece;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public interface PieceDao {
    void save(final Piece piece, final Position position);

    Map<Position, Piece> findAllPiece();

    void deleteAll();

    boolean existPieces();
}
