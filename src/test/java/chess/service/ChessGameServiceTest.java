package chess.service;

import static chess.domain.pixture.PieceFixture.BLACK_PAWN;
import static chess.domain.pixture.PieceFixture.WHITE_PAWN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.Game;
import chess.domain.board.SavedBoardInitializer;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.repository.piece.FakePieceDao;
import chess.repository.piece.PieceDao;
import chess.repository.piece.PieceDto;
import chess.repository.turn.FakeTurnDao;
import chess.repository.turn.TurnDao;
import chess.repository.turn.TurnDto;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameServiceTest {
    private ChessGameService chessGameService;
    private PieceDao pieceDao;
    private TurnDao turnDao;
    private Game game;

    @BeforeEach
    void init() {
        pieceDao = new FakePieceDao();
        turnDao = new FakeTurnDao();
        chessGameService = new ChessGameService(pieceDao, turnDao);

        game = new Game(new SavedBoardInitializer(
                Map.of(Position.of(1, 1), BLACK_PAWN.getPiece(), Position.of(3, 1), WHITE_PAWN.getPiece())));
    }

    @Test
    @DisplayName("현재 Board를 저장한다.")
    void save() {
        chessGameService.saveGame(game);
        assertAll(
                () -> assertThat(pieceDao.findAll()).containsExactlyInAnyOrder(
                        PieceDto.of(BLACK_PAWN.getPiece(), Position.of(1, 1)),
                        PieceDto.of(WHITE_PAWN.getPiece(), Position.of(3, 1))),
                () -> assertThat(turnDao.findAll()).containsExactly(TurnDto.of(Color.BLACK)));
    }

    @Test
    @DisplayName("저장된 위치별 피스를 조회한다.")
    void findAllPiece() {
        chessGameService.saveGame(game);
        assertThat(chessGameService.findAllPiece()).containsExactlyInAnyOrderEntriesOf(
                Map.of(Position.of(1, 1), BLACK_PAWN.getPiece(),
                        Position.of(3, 1), WHITE_PAWN.getPiece()));
    }

    @Test
    @DisplayName("전체 내역을 삭제한다.")
    void delete() {
        chessGameService.delete();
        assertThat(chessGameService.findAllPiece()).isEmpty();
    }

    @Test
    @DisplayName("저장된 게임이 있는지 확인한다.")
    void existGame() {
        chessGameService.saveGame(game);
        assertThat(chessGameService.existSavedGame()).isTrue();
    }

    @Test
    @DisplayName("저장된 게임이 있는지 확인한다.")
    void notExistGame() {
        chessGameService.delete();
        assertThat(chessGameService.existSavedGame()).isFalse();
    }
}
