package chess.service;

import chess.domain.board.Board;
import chess.domain.board.Turn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.repository.piece.PieceDaoImpl;
import chess.repository.piece.PieceDto;
import chess.repository.turn.TurnDao;
import chess.repository.turn.TurnDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessGameService {

    private final PieceDaoImpl pieceDaoImpl;
    private final TurnDao turnDao;

    public ChessGameService(final PieceDaoImpl pieceDaoImpl, final TurnDao turnDao) {
        this.pieceDaoImpl = pieceDaoImpl;
        this.turnDao = turnDao;
    }

    public void save(final Board board) {
        saveAllPiece(board.getBoard());
        saveTurn(board.getTurn());
    }

    private void saveAllPiece(final Map<Position, Piece> pieces) {
        pieces.entrySet().stream()
                .map(entry -> PieceDto.of(entry.getValue(), entry.getKey()))
                .forEach(pieceDaoImpl::save);
    }

    private void saveTurn(final Turn turn) {
        turnDao.save(TurnDto.of(turn));
    }

    public Map<Position, Piece> findAllPiece() {
        List<PieceDto> piecesWithPosition = pieceDaoImpl.findAll();
        return piecesWithPosition.stream()
                .collect(Collectors.toMap(PieceDto::getPositionFrom, PieceDto::getPieceFrom));
    }

    public Turn findTurn() {
        TurnDto turnDto = turnDao.findAll().get(0);
        return turnDto.from();
    }

    public void deleteAll() {
        pieceDaoImpl.deleteAll();
        turnDao.deleteAll();
    }

    public boolean existGame() {
        return findAllPiece().size() != 0;
    }
}
