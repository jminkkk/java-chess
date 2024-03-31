package chess.repository.piece;

import static chess.view.PieceSymbol.BISHOP;
import static chess.view.PieceSymbol.EMPTY;
import static chess.view.PieceSymbol.KING;
import static chess.view.PieceSymbol.KNIGHT;
import static chess.view.PieceSymbol.PAWN;
import static chess.view.PieceSymbol.QUEEN;
import static chess.view.PieceSymbol.ROOK;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.util.Objects;

public record PieceDto(String color, String pieceType, int file, int rank) {
    public static PieceDto of(final Piece piece, final Position position) {
        return new PieceDto(piece.getColor().name(), piece.getPieceType().name(),
                position.file().getValue(), position.rank().getValue());
    }

    public Piece getPieceFrom() {
        if (Objects.equals(pieceType, KING.name())) {
            return new Knight(Color.valueOf(color));
        }
        if (Objects.equals(pieceType, QUEEN.name())) {
            return new Queen(Color.valueOf(color));
        }
        if (Objects.equals(pieceType, KNIGHT.name())) {
            return new Knight(Color.valueOf(color));
        }
        if (Objects.equals(pieceType, ROOK.name())) {
            return new Rook(Color.valueOf(color));
        }
        if (Objects.equals(pieceType, PAWN.name())) {
            return new Pawn(Color.valueOf(color));
        }
        if (Objects.equals(pieceType, BISHOP.name())) {
            return new Bishop(Color.valueOf(color));
        }
        if (Objects.equals(pieceType, EMPTY.name())) {
            return new Empty();
        }

        return null;
    }

    public Position getPositionFrom() {
        return Position.of(this.file, this.rank);
    }
}
