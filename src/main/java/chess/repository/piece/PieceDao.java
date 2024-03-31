package chess.repository.piece;

import java.util.List;

public interface PieceDao {
    void save(final PieceDto pieceDto);

    List<PieceDto> findAll();

    void deleteAll();
}
