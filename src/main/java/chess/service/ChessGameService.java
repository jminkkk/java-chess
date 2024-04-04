package chess.service;

import chess.domain.game.Game;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.repository.piece.PieceDao;
import chess.repository.turn.TurnDao;
import java.util.Map;
import java.util.Optional;

public class ChessGameService {

    private final PieceDao pieceDao;
    private final TurnDao turnDao;

    public ChessGameService(final PieceDao pieceDao, final TurnDao turnDao) {
        this.pieceDao = pieceDao;
        this.turnDao = turnDao;
    }

    public Map<Position, Piece> findAllPiece() {
        return pieceDao.findAllPiece();
    }

    public Optional<Color> findTurn() {
        return turnDao.findAny();
    }

    public void saveGame(final Game game) {
        delete();
        saveAllPiece(game.getBoard());
        saveTurn(game.getTurn());
    }

    private void saveAllPiece(final Map<Position, Piece> pieces) {
        pieces.entrySet()
                .forEach(entry -> pieceDao.save(entry.getValue(), entry.getKey()));
    }

    private void saveTurn(final Color color) {
        turnDao.save(color.name());
    }

    public void delete() {
        pieceDao.deleteAll();
        turnDao.deleteAll();
    }

    public boolean existSavedGame() {
        return pieceDao.existPieces();
    }
}
