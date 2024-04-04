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
    @DisplayName("Piece 객체와 Position 객체로부터 Piece 정보를 저장한다.")
    void addPiece() {
        assertThatCode(() -> pieceDao.save(new Pawn(Color.WHITE), Position.of(1, 2)));
    }

    @Test
    @DisplayName("전체 피스와 포지션을 조회한다.")
    void findAll() {
        Piece pawn = new Pawn(Color.WHITE);
        Position position = Position.of(1, 2);
        pieceDao.save(pawn, position);

        assertThat(pieceDao.findAllPiece()).isEqualTo(Map.of(position, pawn));
    }

    @Test
    @DisplayName("전체 데이터를 삭제한다.")
    void deleteAll() {
        pieceDao.deleteAll();
        assertThat(pieceDao.findAllPiece()).isEqualTo(Map.of());
    }

    @Test
    @DisplayName("Pieces가 존재하지 않을 경우 거짓을 반환한다.")
    void notExistPieces() {
        assertThat(pieceDao.existPieces()).isFalse();
    }

    @Test
    @DisplayName("Pieces가 존재할 경우 참을 반환한다.")
    void existPieces() {
        pieceDao.save(new Pawn(Color.WHITE), Position.of(1, 2));
        assertThat(pieceDao.existPieces()).isTrue();
    }
}
