package chess.service;

import chess.domain.Game;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.repository.piece.PieceDao;
import chess.repository.piece.PieceDto;
import chess.repository.turn.TurnDao;
import chess.repository.turn.TurnDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessGameService {

    private final PieceDao pieceDao;
    private final TurnDao turnDao;

    public ChessGameService(final PieceDao pieceDao, final TurnDao turnDao) {
        this.pieceDao = pieceDao;
        this.turnDao = turnDao;
    }

    public void save(final Game game) {
        saveAllPiece(game.getBoard());
        saveTurn(game.getTurn());
    }

    private void saveAllPiece(final Map<Position, Piece> pieces) {
        pieces.entrySet().stream()
                .map(entry -> PieceDto.of(entry.getValue(), entry.getKey()))
                .forEach(pieceDao::save);
    }

    private void saveTurn(final Color color) {
        turnDao.save(TurnDto.of(color));
    }

    public Map<Position, Piece> findAllPiece() {
        List<PieceDto> piecesWithPosition = pieceDao.findAll();
        return piecesWithPosition.stream()
                .collect(Collectors.toMap(PieceDto::getPositionFrom, PieceDto::getPieceFrom));
    }

    public Color findTurn() {
        TurnDto turnDto = turnDao.findAll().get(0);
        return turnDto.from();
    }

    public void delete() {
        pieceDao.deleteAll();
        turnDao.deleteAll();
    }

    public boolean existGame() {
        return findAllPiece().size() != 0;
    }
}
