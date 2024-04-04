package chess.repository.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDaoImplTest {
    private PieceDao pieceDao;

    @BeforeEach
    void init() {
        pieceDao = new FakePieceDao();
    }

    @Test
    void addPiece() {
        PieceDto pieceDto = new PieceDto("BLACK", "PAWN", 1, 2);
        assertThatCode(() -> pieceDao.save(pieceDto));
    }

    @Test
    void findAll() {
        PieceDto pieceDto = new PieceDto("BLACK", "PAWN", 1, 2);
        pieceDao.save(pieceDto);

        assertThat(pieceDao.findAll()).isEqualTo(List.of(pieceDto));
    }

    @Test
    void deleteAll() {
        pieceDao.deleteAll();
        assertThat(pieceDao.findAll()).isEqualTo(List.of());
    }

    @Test
    @DisplayName("Pieces가 DB에 존재하지 않을 경우 거짓을 반환한다.")
    void notExistPieces() {
        assertThat(pieceDao.existPieces()).isFalse();
    }

    @Test
    @DisplayName("Pieces가 DB에 존재할 경우 참을 반환한다.")
    void existPieces() {
        pieceDao.save(new PieceDto("BLACK", "PAWN", 1, 2));
        assertThat(pieceDao.existPieces()).isTrue();
    }
}
