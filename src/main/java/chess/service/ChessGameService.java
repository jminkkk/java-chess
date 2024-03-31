package chess.service;

import chess.domain.board.Board;
import chess.domain.board.Turn;
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

    public void save(final Board board) {
        saveAllPiece(board.getBoard());
        saveTurn(board.getTurn());
    }

    private void saveAllPiece(final Map<Position, Piece> pieces) {
        pieces.entrySet().stream()
                .map(entry -> PieceDto.of(entry.getValue(), entry.getKey()))
                .forEach(pieceDao::save);
    }

    private void saveTurn(final Turn turn) {
        turnDao.save(TurnDto.of(turn));
    }

    public Map<Position, Piece> findAllPiece() {
        List<PieceDto> piecesWithPosition = pieceDao.findAll();
        return piecesWithPosition.stream()
                .collect(Collectors.toMap(PieceDto::getPositionFrom, PieceDto::getPieceFrom));
    }

    public Turn findTurn() {
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
