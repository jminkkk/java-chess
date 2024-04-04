package chess.repository.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JdbcPieceDaoTest {
    private PieceDao pieceDao;

    @BeforeEach
    void init() {
        pieceDao = new FakePieceDao();
    }

    @Test
    void addPiece() {
        assertThatCode(() -> pieceDao.save(new Pawn(Color.WHITE), Position.of(1, 2)));
    }

    @Test
    void findAll() {
        Piece pawn = new Pawn(Color.WHITE);
        Position position = Position.of(1, 2);
        pieceDao.save(pawn, position);

        assertThat(pieceDao.findAllPiece()).isEqualTo(Map.of(position, pawn));
    }

    @Test
    void deleteAll() {
        pieceDao.deleteAll();
        assertThat(pieceDao.findAllPiece()).isEqualTo(Map.of());
    }

    @Test
    @DisplayName("Pieces가 DB에 존재하지 않을 경우 거짓을 반환한다.")
    void notExistPieces() {
        assertThat(pieceDao.existPieces()).isFalse();
    }

    @Test
    @DisplayName("Pieces가 DB에 존재할 경우 참을 반환한다.")
    void existPieces() {
        pieceDao.save(new Pawn(Color.WHITE), Position.of(1, 2));
        assertThat(pieceDao.existPieces()).isTrue();
    }
}
