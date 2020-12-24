package xyz.chengzi.aeroplanechess.listener;

import com.sun.media.jfxmedia.events.PlayerStateEvent;
import xyz.chengzi.aeroplanechess.model.ChessBoard;
import xyz.chengzi.aeroplanechess.model.ChessBoardLocation;
import xyz.chengzi.aeroplanechess.model.ChessLocation;
import xyz.chengzi.aeroplanechess.model.ChessPiece;
import xyz.chengzi.aeroplanechess.view.DiceSelectorComponent;

import java.util.List;


public interface MethodsForPlaying {
    boolean CheckForGoOut(int numberOfDiceOne, int numberOfDiceTwo);

    int[] NumberOfMove(int numberOfDiceOne, int numberOfDiceTwo);

    boolean EatOthersPiece(ChessPiece piece1 , ChessPiece piece2 ,ChessBoard board ,ChessBoardLocation location1 , ChessBoardLocation location2);

    boolean CheckAnyPlayer(ChessPiece piece ,ChessBoard board ,ChessBoardLocation location);

    boolean BonusLocation(ChessBoardLocation location , ChessPiece chessPiece);

    boolean anotherRoll(int numberOfDiceTwo,int numberOfDiceOne);

    //TODO:next:implement methods


    boolean TooLuckyTooUnlucky(int player ,int NumberOfDiceOne , int NumberOfDiceTwo);

    int ChooseToStack(ChessPiece chessPiece1 , ChessPiece chessPiece2 ,ChessBoard board , ChessBoardLocation location2);

    void CompeteForEatingPiece(int DiceOne , int DiceTwo);


}
